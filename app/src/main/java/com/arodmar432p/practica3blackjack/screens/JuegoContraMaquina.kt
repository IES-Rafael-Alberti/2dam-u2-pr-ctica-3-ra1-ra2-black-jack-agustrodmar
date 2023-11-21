package com.arodmar432p.practica3blackjack.screens

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.sizeIn
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.arodmar432p.practica3blackjack.JuegoViewModel
import com.arodmar432p.practica3blackjack.R
import com.arodmar432p.practica3blackjack.ui.theme.Practica3BlackJackTheme

@Composable
fun JuegoContraMaquina(navController: NavController) {
    val viewModel: JuegoViewModel = viewModel()
    val estadoJuego by viewModel.estadoJuego.collectAsState()

    val background = painterResource(id = R.drawable.tapete)

    Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
        Image(
            painter = background,
            contentDescription = "Background",
            contentScale = ContentScale.Crop,
            modifier = Modifier.fillMaxSize()
        )

        Column(modifier = Modifier.fillMaxSize(), horizontalAlignment = Alignment.CenterHorizontally) {
            Text(text = "BlackJack", fontSize = 30.sp, fontWeight = FontWeight.Bold, modifier = Modifier.padding(16.dp))
            Spacer(modifier = Modifier.height(24.dp))

            // Muestra las fichas del jugador en lugar de los puntos
            Text(text = "Fichas: ${estadoJuego.partida?.jugador?.fichas}", fontSize = 20.sp, fontWeight = FontWeight.Bold)
            Spacer(modifier = Modifier.height(24.dp))

            // Muestra las cartas del crupier boca abajo
            LazyRow {
                items(estadoJuego.partida?.crupier?.mano ?: emptyList()) { carta ->
                    if (estadoJuego.partida?.crupierCartasBocaArriba == true) {
                        val imagenCarta = painterResource(id = carta.idDrawable)
                        Image(painter = imagenCarta, contentDescription = null, modifier = Modifier.size(100.dp))
                    } else {
                        val imagenCartaBocaAbajo = painterResource(id = R.drawable.bocabajo)
                        Image(painter = imagenCartaBocaAbajo, contentDescription = null, modifier = Modifier.size(100.dp))
                    }
                }
            }

            Spacer(Modifier.weight(1f))

            // Muestra las cartas del jugador en la parte inferior
            LazyRow {
                items(estadoJuego.partida?.jugador?.mano ?: emptyList()) { carta ->
                    val imagenCarta = painterResource(id = carta.idDrawable)
                    Image(painter = imagenCarta, contentDescription = null, modifier = Modifier.size(100.dp))
                }
            }

            Spacer(modifier = Modifier.height(24.dp))

            Row {
                Button(
                    onClick = { viewModel.pedirCarta() },
                    colors = ButtonDefaults.buttonColors(containerColor = Color(0xFFD4AF37)),
                    border = BorderStroke(2.dp, Color.White),
                    modifier = Modifier.sizeIn(minWidth = 200.dp, minHeight = 50.dp)
                ) {
                    Text("Pedir Carta", color = Color.Black)
                }

                Spacer(modifier = Modifier.width(8.dp))

                Button(
                    onClick = { viewModel.plantarse() },
                    colors = ButtonDefaults.buttonColors(containerColor = Color(0xFFD4AF37)),
                    border = BorderStroke(2.dp, Color.White),
                    modifier = Modifier.sizeIn(minWidth = 200.dp, minHeight = 50.dp)
                ) {
                    Text("Plantarse", color = Color.Black)
                }
            }

            Text(text = estadoJuego.partida?.determinarGanador() ?: "", fontSize = 20.sp, fontWeight = FontWeight.Bold)
        }
    }
}

@Preview
@Composable
fun JuegoContraMaquinaPreview() {
    Practica3BlackJackTheme {
        JuegoContraMaquina(navController = rememberNavController())
    }
}
