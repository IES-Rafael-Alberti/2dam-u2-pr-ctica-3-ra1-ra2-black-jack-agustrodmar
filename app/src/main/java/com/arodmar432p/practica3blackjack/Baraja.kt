package com.arodmar432p.practica3blackjack

import android.annotation.SuppressLint
import android.content.Context


/**
 * Clase Baraja que representa una baraja de cartas.
 */
object Baraja {
    // Lista para almacenar las cartas de la baraja
    private val listaCartas = ArrayList<Carta>()

    /**
     * Función para crear una baraja de cartas.
     *
     * @param context Contexto de la aplicación.
     */
    @SuppressLint("DiscouragedApi")
    fun crearBaraja(context: Context) {
        // Itera sobre todos los palos
        for (palo in Palo.values()) {
            // Itera sobre todos los naipes
            for (naipe in Naipe.values()) {
                // Determina los puntos mínimos y máximos de la carta
                val puntosMin = if (naipe == Naipe.AS) 1 else naipe.valor
                val puntosMax = if (naipe == Naipe.AS) 11 else naipe.valor

                // Determina el nombre del recurso de la carta
                val nombreRecurso = when (naipe) {
                    Naipe.AS -> "${palo.toString().lowercase()}a"
                    Naipe.DOS -> "${palo.toString().lowercase()}2"
                    Naipe.TRES -> "${palo.toString().lowercase()}3"
                    Naipe.CUATRO -> "${palo.toString().lowercase()}4"
                    Naipe.CINCO -> "${palo.toString().lowercase()}5"
                    Naipe.SEIS -> "${palo.toString().lowercase()}6"
                    Naipe.SIETE -> "${palo.toString().lowercase()}7"
                    Naipe.OCHO -> "${palo.toString().lowercase()}8"
                    Naipe.NUEVE -> "${palo.toString().lowercase()}9"
                    Naipe.DIEZ -> "${palo.toString().lowercase()}10"
                    Naipe.VALET -> "${palo.toString().lowercase()}j"
                    Naipe.DAME -> "${palo.toString().lowercase()}q"
                    Naipe.ROI -> "${palo.toString().lowercase()}k"
                }

                // Obtiene el identificador del recurso drawable
                val idDrawable = context.resources.getIdentifier(nombreRecurso, "drawable", context.packageName)

                // Añade la carta a la lista de cartas
                listaCartas.add(Carta(naipe, palo, puntosMin, puntosMax, idDrawable))
            }
        }
    }

    /**
     * Función para barajar las cartas.
     */
    fun barajar() {
        listaCartas.shuffle()
    }

    /**
     * Función para obtener una carta de la baraja.
     *
     * @return Carta La carta obtenida de la baraja.
     */
    fun dameCarta(): Carta {
        return listaCartas.removeAt(listaCartas.size - 1)
    }
}
