package com.arodmar432p.practica3blackjack.screens

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.sizeIn
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
import com.arodmar432p.practica3blackjack.Ficha
import com.arodmar432p.practica3blackjack.JuegoViewModel
import com.arodmar432p.practica3blackjack.R
import com.arodmar432p.practica3blackjack.ui.theme.Practica3BlackJackTheme

@Composable
fun SeleccionarApuesta(navController: NavController) {
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

            // Muestra las fichas para que el jugador haga su apuesta
            LazyRow {
                val fichas = listOf(
                    Ficha(1, R.drawable.f1),
                    Ficha(5, R.drawable.f5),
                    Ficha(10, R.drawable.f10),
                    Ficha(20, R.drawable.f20),
                    Ficha(50, R.drawable.f50),
                    Ficha(100, R.drawable.f100),
                    Ficha(500, R.drawable.f500),
                    Ficha(1000, R.drawable.f1000),
                    Ficha(5000, R.drawable.f5000)
                )
                items(fichas) { ficha ->
                    val imagenFicha = painterResource(id = ficha.idDrawable)
                    Image(painter = imagenFicha, contentDescription = null, modifier = Modifier.size(80.dp).clickable {
                        // Aquí deberías actualizar la apuesta del jugador
                        viewModel.hacerApuesta(ficha.valor)
                    })
                }
            }

            Button(
                onClick = { /* Navega a JuegoContraMaquina */ },
                colors = ButtonDefaults.buttonColors(containerColor = Color(0xFFD4AF37)),
                border = BorderStroke(2.dp, Color.White),
                modifier = Modifier.sizeIn(minWidth = 200.dp, minHeight = 50.dp).padding(top = 16.dp)
            ) {
                Text("Comenzar ronda", color= Color.Black)
            }
        }
    }
}

@Preview
@Composable
fun SeleccionarApuestaPreview() {
    Practica3BlackJackTheme {
        SeleccionarApuesta(navController = rememberNavController())
    }
}
