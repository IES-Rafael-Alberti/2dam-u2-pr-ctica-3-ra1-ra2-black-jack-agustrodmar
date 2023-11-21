package com.arodmar432p.practica3blackjack

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.arodmar432p.practica3blackjack.model.BlackjackRoutes
import com.arodmar432p.practica3blackjack.screens.JuegoContraMaquina
import com.arodmar432p.practica3blackjack.screens.MainMenu
import com.arodmar432p.practica3blackjack.screens.SeleccionarApuesta
import com.arodmar432p.practica3blackjack.ui.theme.Practica3BlackJackTheme



class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            val navController = rememberNavController()

            Practica3BlackJackTheme {
                NavHost(navController = navController, startDestination = BlackjackRoutes.MainMenu.route) {
                    composable(BlackjackRoutes.MainMenu.route) { MainMenu(navController) }
                    composable(BlackjackRoutes.SeleccionarApuesta.route) { SeleccionarApuesta(navController) }
                    composable(BlackjackRoutes.JuegoContraMaquina.route) { JuegoContraMaquina(navController) }

                }
            }
        }
    }
}







