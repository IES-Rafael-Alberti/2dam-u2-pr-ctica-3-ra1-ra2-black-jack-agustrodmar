package com.arodmar432p.practica3blackjack.model

sealed class BlackjackRoutes(val route: String) {
    object MainMenu : BlackjackRoutes("MainMenu")
    object SeleccionarApuesta : BlackjackRoutes("SeleccionarApuesta")
    object JuegoContraMaquina : BlackjackRoutes("JuegoContraMaquina")

}
