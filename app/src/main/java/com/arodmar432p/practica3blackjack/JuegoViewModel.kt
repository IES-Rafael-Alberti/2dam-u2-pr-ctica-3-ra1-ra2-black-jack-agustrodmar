package com.arodmar432p.practica3blackjack

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

class JuegoViewModel(application: Application) : AndroidViewModel(application) {
    private val _estadoJuego = MutableStateFlow(EstadoJuego())
    val estadoJuego: StateFlow<EstadoJuego> = _estadoJuego

    private var partida: Partida? = null

    val baraja = Baraja
    init {
        iniciarPartida()
    }

    private fun iniciarPartida() {
        // Crea una nueva baraja de cartas
        baraja.crearBaraja(getApplication())

        // Baraja las cartas
        baraja.barajar()

        // Crea un nuevo jugador y un nuevo crupier
        val jugador = Jugador("Jugador")
        val crupier = Jugador("Crupier")

        // Crea una nueva partida con el jugador, el crupier y la baraja
        partida = Partida(jugador, crupier, baraja)

        // Actualiza el estado del juego
        actualizarEstadoJuego()
    }
    fun pedirCarta() {
        partida?.pedirCarta()
        if (partida?.jugador?.calcularPuntos()!! > 21) {
            // El jugador ha perdido, termina la partida
        } else {
            actualizarEstadoJuego()
        }
    }

    fun plantarse() {
        while (partida?.crupier?.calcularPuntos()!! < 17) {
            partida?.crupier?.añadirCarta(baraja.dameCarta())
        }
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
