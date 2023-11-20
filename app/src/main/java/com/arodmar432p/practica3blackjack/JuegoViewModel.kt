package com.arodmar432p.practica3blackjack

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

class JuegoViewModel(application: Application) : AndroidViewModel(application) {
    private val _estadoJuego = MutableStateFlow(EstadoJuego())
    val estadoJuego: StateFlow<EstadoJuego> = _estadoJuego

    private var partida: Partida? = null

    init {
        iniciarPartida()
    }

    private fun iniciarPartida() {
        // Resto del código...
    }

    fun pedirCarta() {
        partida?.pedirCarta()
        actualizarEstadoJuego()
    }

    fun plantarse() {
        partida?.plantarse()
        actualizarEstadoJuego()
    }

    // Resto del código...

    private fun actualizarEstadoJuego() {
        _estadoJuego.value = EstadoJuego(
            partida = partida,
            puntuacionJugador = partida?.jugador?.calcularPuntos() ?: 0,
            puntuacionCrupier = partida?.crupier?.calcularPuntos() ?: 0
        )
    }
}
