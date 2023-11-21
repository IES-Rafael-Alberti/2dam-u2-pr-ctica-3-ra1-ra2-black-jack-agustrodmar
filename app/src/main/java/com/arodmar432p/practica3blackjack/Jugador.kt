package com.arodmar432p.practica3blackjack

class Jugador(val nombre: String) {
    var mano = ArrayList<Carta>()
    var fichas = 0
    var apuesta = 0
    fun añadirCarta(carta: Carta) {
        mano.add(carta)
    }

    fun calcularPuntos(): Int {
        var puntos = 0
        var ases = 0

        for (carta in mano) {
            puntos += carta.puntosMax
            if (carta.nombre == Naipe.AS) {
                ases += 1
            }
        }

        fun setApuesta(ficha: Int) {
            apuesta += ficha
            fichas -= ficha
        }

        fun doblarApuesta() {
            if (fichas >= apuesta) {
                fichas -= apuesta
                apuesta *= 2
            } else {
                // Tengo pendiente de añadir la lógica ante la falta de fichas
            }
        }

        while (puntos > 21 && ases > 0) {
            puntos -= 10
            ases -= 1
        }

        return puntos
    }

    fun reset() {
        mano.clear()
        apuesta = 0
    }
}
