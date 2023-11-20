package com.arodmar432p.practica3blackjack

data class EstadoJuego(
    val partida: Partida? = null,
    val puntuacionJugador: Int = 0,
    val puntuacionCrupier: Int = 0

)
