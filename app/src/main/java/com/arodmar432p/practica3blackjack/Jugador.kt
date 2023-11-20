package com.arodmar432p.practica3blackjack

class Jugador(val nombre: String) {
    var mano = ArrayList<Carta>()
    var fichas = 0

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

        while (puntos > 21 && ases > 0) {
            puntos -= 10
            ases -= 1
        }

        return puntos
    }

    fun reset() {
        mano.clear()
    }
}
