package com.arodmar432p.practica3blackjack

class Partida(val jugador: Jugador, val crupier: Jugador, private val baraja: Baraja) {
    var crupierCartasBocaArriba = false

    init {
        // Distribución inicial de las cartas
        for (i in 1..2) {
            jugador.añadirCarta(baraja.dameCarta())
            crupier.añadirCarta(baraja.dameCarta())
        }
    }

    fun pedirCarta() {
        jugador.añadirCarta(baraja.dameCarta())
        if (jugador.calcularPuntos() > 21) {
            // El jugador ha perdido, termina la partida
            jugador.fichas -= jugador.apuesta
        }
    }

    fun plantarse() {
        while (crupier.calcularPuntos() < 17) {
            crupier.añadirCarta(baraja.dameCarta())
        }
        crupierCartasBocaArriba = true
        if (jugador.calcularPuntos() > crupier.calcularPuntos()) {
            // El jugador gana
            jugador.fichas += jugador.apuesta * 2
        } else {
            // El crupier gana
            jugador.fichas -= jugador.apuesta
        }
    }

    fun duplicar() {
        jugador.añadirCarta(baraja.dameCarta())
        jugador.fichas *= 2
    }

    fun dividir() {
        if (jugador.mano.size == 2 && jugador.mano[0].nombre == jugador.mano[1].nombre) {
            val manoDividida = ArrayList<Carta>()
            manoDividida.add(jugador.mano.removeAt(1))
            manoDividida.add(baraja.dameCarta())
            jugador.mano.add(baraja.dameCarta())

            // Aquí debería manejar la nueva mano dividida. Podría crear un nuevo Jugador para ella.
        }
    }

    fun determinarGanador(): String {
        val puntosJugador = jugador.calcularPuntos()
        val puntosCrupier = crupier.calcularPuntos()

        return when {
            puntosJugador > 21 -> "Perdiste"
            puntosCrupier > 21 -> "Ganaste"
            puntosJugador > puntosCrupier -> "Ganaste"
            puntosJugador < puntosCrupier -> "Perdiste"
            else -> "Empate"
        }
    }
}