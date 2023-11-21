package com.arodmar432p.practica3blackjack

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
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
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
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
import androidx.navigation.compose.rememberNavController
import com.arodmar432p.practica3blackjack.ui.theme.Practica3BlackJackTheme



class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            Practica3BlackJackTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    MainMenu()
                }
            }
        }
    }
}

@Composable
@Preview
fun MainMenu() {
    val openDialog = remember { mutableStateOf(false) }
    val navController = rememberNavController()
    val background = painterResource(id = R.drawable.tapete)

    Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
        Image(
            painter = background,
            contentDescription = "Background",
            contentScale = ContentScale.Crop,
            modifier = Modifier.fillMaxSize()
        )

        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.spacedBy(10.dp),
            modifier = Modifier.padding(16.dp)
        ) {
            Button(
                onClick = { openDialog.value = true },
                colors = ButtonDefaults.buttonColors(containerColor = Color(0xFFD4AF37)),
                border = BorderStroke(2.dp, Color.White),
                modifier = Modifier.sizeIn(minWidth = 200.dp, minHeight = 50.dp)
            ) {
                Text("Empezar Partida", color = Color.Black)
            }

            if (openDialog.value) {
                AlertDialog(
                    onDismissRequest = { openDialog.value = false },
                    title = { Text("Elige un modo de juego", color= Color.White) },
                    text = {
                        Column {
                            Button(
                                onClick = { /* Contra la máquina */ },
                                colors = ButtonDefaults.buttonColors(containerColor = Color(0xFFD4AF37)),
                                border = BorderStroke(2.dp, Color.White)
                            ) {
                                Text("Contra la máquina")
                            }
                            Button(
                                onClick = { /* 2 jugadores */ },
                                colors = ButtonDefaults.buttonColors(containerColor = Color(0xFFD4AF37)),
                                border = BorderStroke(2.dp, Color.White)
                            ) {
                                Text("2 jugadores")
                            }
                        }
                    },
                    confirmButton = { },
                    dismissButton = { },
                    containerColor = Color.Black
                )
            }

            Button(
                onClick = { /* Cargar Partida */ },
                colors = ButtonDefaults.buttonColors(containerColor = Color(0xFFD4AF37)),
                border = BorderStroke(2.dp, Color.White),
                modifier = Modifier.sizeIn(minWidth = 200.dp, minHeight = 50.dp)
            ) {
                Text("Cargar Partida", color = Color.Black)
            }

            Button(
                onClick = { navController.navigate("Resultados") },
                colors = ButtonDefaults.buttonColors(containerColor = Color(0xFFD4AF37)),
                border = BorderStroke(2.dp, Color.White),
                modifier = Modifier.sizeIn(minWidth = 200.dp, minHeight = 50.dp)
            ) {
                Text("Resultados", color = Color.Black)
            }

            Button(
                onClick = { /* Salir */ },
                colors = ButtonDefaults.buttonColors(containerColor = Color(0xFFD4AF37)),
                border = BorderStroke(2.dp, Color.White),
                modifier = Modifier.sizeIn(minWidth = 200.dp, minHeight = 50.dp)
            ) {
                Text("Salir", color = Color.Black)
            }
        }
    }
}

@Composable
@Preview
fun JuegoContraMaquina() {
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

            if (!viewModel.juegoIniciado) {
                Button(
                    onClick = { viewModel.iniciarPartida() },
                    colors = ButtonDefaults.buttonColors(containerColor = Color(0xFFD4AF37)),
                    border = BorderStroke(2.dp, Color.White),
                    modifier = Modifier.sizeIn(minWidth = 200.dp, minHeight = 50.dp).padding(bottom = 16.dp)
                ) {
                    Text("Comenzar ronda", color= Color.Black)
                }

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
                            // Se actualiza la apuesta del jugador
                            viewModel.hacerApuesta(ficha.valor)
                        })
                    }
                }
            } else {
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
}


