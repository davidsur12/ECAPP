package com.ecapp.ecapp.screen.games.laberinto

import android.annotation.SuppressLint

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.material3.Button
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.material3.ButtonDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.sp
import com.ecapp.ecapp.navegation.AppScreens
import com.ecapp.ecapp.utils.Configuraciones
import com.ecapp.ecapp.utils.DateUser


@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun ScreenGameLaberinto(navController: NavController) {
    val context = LocalContext.current
    val nivel = remember { mutableStateOf(0) }
    val lives = remember { mutableStateOf(5) }


    Scaffold(
        modifier = Modifier
            .fillMaxSize()
            .background(colorResource(com.ecapp.ecapp.R.color.morado_fondo))
    ) {
        Column(
            modifier = Modifier.background(colorResource(com.ecapp.ecapp.R.color.morado_fondo)),
            verticalArrangement = Arrangement.Center
        ) {
            Text(
                text = "Laberinto",
                fontSize = Configuraciones.fontSizeTitulos.sp,
                style = androidx.compose.ui.text.TextStyle(fontWeight = FontWeight.Bold, color = Color.White),
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 16.dp, bottom = 8.dp)
                    .background(colorResource(com.ecapp.ecapp.R.color.morado_fondo)),
                textAlign = TextAlign.Center
            )

            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(bottom = 16.dp),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(

                    text = "Nivel: ${nivel.value + 1}",
                    style = androidx.compose.ui.text.TextStyle(fontWeight = FontWeight.Bold, color = Color.White),
                    fontSize = Configuraciones.fontSizeNormal.sp,
                    modifier = Modifier.background(colorResource(com.ecapp.ecapp.R.color.morado_fondo))
                )
                Text(
                    text = "Vidas: " + "❤️".repeat(lives.value),
                    style = androidx.compose.ui.text.TextStyle(fontWeight = FontWeight.Bold, color = Color.White),
                    fontSize = Configuraciones.fontSizeNormal.sp,
                    modifier = Modifier.background(colorResource(com.ecapp.ecapp.R.color.morado_fondo))
                )
            }

            Box(
                modifier = Modifier.weight(1f),
                contentAlignment = Alignment.Center
            ) {
                MazeGameApp(navController, nivel, lives)
            }
        }
    }
}

@Composable
fun MazeGameApp(navController: NavController, nivel: MutableState<Int>, lives: MutableState<Int>) {
    val context = LocalContext.current




    val mazes = listOf(
        // Nivel 1
        listOf(
            listOf(1, 1, 1, 1, 1, 1, 1),
            listOf(1, 0, 0, 0, 1, 0, 1),
            listOf(1, 0, 1, 0, 1, 0, 1),
            listOf(1, 0, 1, 0, 0, 0, 1),
            listOf(1, 1, 1, 1, 1, 1, 1)
        ),
        // Nivel 2
        listOf(
            listOf(1, 1, 1, 1, 1, 1, 1),
            listOf(1, 0, 0, 1, 0, 0, 1),
            listOf(1, 0, 0, 1, 1, 0, 1),
            listOf(1, 0, 0, 0, 0, 0, 1),
            listOf(1, 1, 1, 1, 1, 1, 1)
        ),
        // Nivel 3
        listOf(
            listOf(1, 1, 1, 1, 1, 1, 1),
            listOf(1, 0, 1, 0, 1, 0, 1),
            listOf(1, 0, 1, 0, 0, 0, 1),
            listOf(1, 0, 1, 1, 1, 0, 1),
            listOf(1, 0, 0, 0, 0, 0, 1),
            listOf(1, 1, 1, 1, 1, 1, 1)
        ),
        // Nivel 4
        listOf(
            listOf(1, 1, 1, 1, 1, 1, 1),
            listOf(1, 0, 0, 1, 0, 0, 1),
            listOf(1, 1, 0, 1, 0, 1, 1),
            listOf(1, 0, 0, 0, 0, 0, 1),
            listOf(1, 1, 1, 1, 1, 1, 1)
        ),
        // Nivel 5
        listOf(
            listOf(1, 1, 1, 1, 1, 1, 1),
            listOf(1, 0, 0, 1, 1, 0, 1),
            listOf(1, 0, 0, 0, 0, 0, 1),
            listOf(1, 1, 1, 1, 1, 0, 0),
            listOf(1, 1, 1, 1, 1, 0, 1)
        ),
        // Nivel 6
        listOf(
            listOf(1, 1, 1, 1, 1, 1, 1),
            listOf(1, 0, 0, 1, 0, 0, 1),
            listOf(1, 0, 1, 0, 1, 1, 1),
            listOf(1, 0, 0, 0, 0, 0, 1),
            listOf(1, 1, 1, 1, 1, 1, 1)
        ),
        // Nivel 7
        listOf(
            listOf(1, 1, 1, 1, 1, 1, 1),
            listOf(1, 0, 1, 0, 1, 0, 1),
            listOf(1, 0, 1, 0, 0, 0, 1),
            listOf(1, 0, 1, 0, 1, 0, 1),
            listOf(1, 0, 0, 0, 1, 0, 1),
            listOf(1, 1, 1, 1, 1, 1, 1)
        ),
        // Nivel 8
        listOf(
            listOf(1, 1, 1, 1, 1, 1, 1),
            listOf(1, 0, 0, 1, 0, 0, 1),
            listOf(1, 1, 0, 0, 0, 1, 1),
            listOf(1, 0, 0, 0, 1, 0, 1),
            listOf(1, 0, 1, 0, 0, 0, 1),
            listOf(1, 1, 1, 1, 1, 1, 1)
        ),
        // Nivel 9
        listOf(
            listOf(1, 1, 1, 1, 1, 1, 1),
            listOf(1, 0, 1, 0, 1, 0, 1),
            listOf(1, 0, 1, 0, 0, 0, 1),
            listOf(1, 0, 0, 0, 1, 0, 1),
            listOf(1, 1, 1, 0, 1, 0, 1),
            listOf(1, 1, 1, 1, 1, 1, 1)
        ),
        // Nivel 10
        listOf(
            listOf(1, 1, 1, 1, 1, 1, 1),
            listOf(1, 0, 1, 1, 1, 0, 1),
            listOf(1, 0, 0, 0, 1, 0, 1),
            listOf(1, 1, 0, 1, 1, 0, 1),
            listOf(1, 0, 0, 0, 0, 0, 1),
            listOf(1, 1, 1, 1, 1, 1, 1)
        ),
        // Nivel 11
                listOf(
                listOf(1, 1, 1, 1, 1, 1, 1),
        listOf(1, 0, 0, 0, 0, 0, 1),
        listOf(1, 0, 1, 1, 0, 0, 1),
        listOf(1, 0, 0, 0, 1, 0, 1),
        listOf(1, 1, 1, 0, 0, 0, 1),
        listOf(1, 1, 1, 1, 1, 1, 1)
    ),
    // Nivel 12
    listOf(
        listOf(1, 1, 1, 1, 1, 1, 1),
        listOf(1, 0, 0, 0, 0, 0, 1),
        listOf(1, 0, 1, 0, 1, 0, 1),
        listOf(1, 0, 0, 0, 1, 0, 1),
        listOf(1, 1, 1, 1, 1, 0, 1),
        listOf(1, 1, 1, 1, 1, 1, 1)
    ),
    // Nivel 13
    listOf(
        listOf(1, 1, 1, 1, 1, 1, 1),
        listOf(1, 0, 0, 0, 1, 0, 1),
        listOf(1, 0, 1, 1, 1, 0, 1),
        listOf(1, 0, 1, 0, 0, 0, 1),
        listOf(1, 0, 0, 0, 0, 1, 1),
        listOf(1, 1, 1, 1, 1, 1, 1)
    ),
    // Nivel 14
    listOf(
        listOf(1, 1, 1, 1, 1, 1, 1),
        listOf(1, 0, 1, 0, 0, 0, 1),
        listOf(1, 0, 1, 0, 1, 0, 1),
        listOf(1, 0, 0, 0, 1, 0, 1),
        listOf(1, 1, 1, 1, 1, 0, 1),
        listOf(1, 1, 1, 1, 1, 1, 1)
    ),
    // Nivel 15
    listOf(
        listOf(1, 1, 1, 1, 1, 1, 1),
        listOf(1, 0, 1, 1, 1, 0, 1),
        listOf(1, 0, 0, 0, 1, 0, 1),
        listOf(1, 1, 1, 0, 1, 0, 1),
        listOf(1, 0, 0, 0, 0, 0, 1),
        listOf(1, 1, 1, 1, 1, 1, 1)
    )
    )


    val playerPosition = remember { mutableStateOf(Pair(1, 1)) }
    val goalPosition = remember { mutableStateOf(Pair(3, 5)) }

    // Estado para controlar si la navegación ya ha ocurrido
    val hasNavigated = remember { mutableStateOf(false) }

    Column(
        modifier = Modifier.background(colorResource(com.ecapp.ecapp.R.color.morado_fondo)),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        // Verifica si se han acabado las vidas
        if (lives.value <= 0 && !hasNavigated.value) {
            hasNavigated.value = true // Indica que la navegación ya ha ocurrido
            navController.navigate(AppScreens.screenGameOverLaberinto.route)
        } else if (nivel.value >= mazes.size - 1 && !hasNavigated.value) {
            hasNavigated.value = true // Indica que la navegación ya ha ocurrido
            DateUser.vidasLaberinto = lives.value
            navController.navigate(AppScreens.screenFelicitacionesGamesLaberinto.route)
        } else {
            // Solo ejecuta el juego si no se ha navegado
            MazeGame(mazes[nivel.value], playerPosition, goalPosition) {
                if (playerPosition.value == goalPosition.value) {
                    if (nivel.value < mazes.size - 1) {
                        nivel.value++ // Aumenta el nivel
                        playerPosition.value = Pair(1, 1) // Reinicia la posición del jugador
                    } else {
                        hasNavigated.value = true // Indica que la navegación ya ha ocurrido
                        navController.navigate(AppScreens.screenGameOverLaberinto.route)
                    }
                }
            }

            PlayerControls { direction ->
                movePlayer(direction, playerPosition.value, mazes[nivel.value], lives) { newPos ->
                    playerPosition.value = newPos
                }
            }
        }
    }
}

fun movePlayer(
    direction: String,
    currentPosition: Pair<Int, Int>,
    maze: List<List<Int>>,
    lives: MutableState<Int>,
    onPositionChanged: (Pair<Int, Int>) -> Unit
) {
    val (row, col) = currentPosition
    val newPosition = when (direction) {
        "up" -> Pair(row - 1, col)
        "down" -> Pair(row + 1, col)
        "left" -> Pair(row, col - 1)
        "right" -> Pair(row, col + 1)
        else -> currentPosition
    }

    if (newPosition.first in maze.indices && newPosition.second in maze[newPosition.first].indices &&
        maze[newPosition.first][newPosition.second] == 0
    ) {
        onPositionChanged(newPosition) // Actualiza la posición del jugador
    } else {
        lives.value -= 1 // Resta una vida si el movimiento es inválido
    }
}

@Composable
fun MazeGame(
    maze: List<List<Int>>,
    playerPosition: MutableState<Pair<Int, Int>>,
    goalPosition: MutableState<Pair<Int, Int>>,
    onReachGoal: () -> Unit
) {
    val rows = maze.size
    val cols = maze[0].size // Asegúrate de que todas las filas tengan la misma longitud

    LazyVerticalGrid(
        verticalArrangement = Arrangement.Center,
        columns = GridCells.Fixed(cols),
        modifier = Modifier.fillMaxWidth()
    ) {
        items(rows * cols) { index ->
            val row = index / cols
            val col = index % cols

            Box(
                modifier = Modifier.size(50.dp)
            ) {
                if (playerPosition.value == Pair(row, col)) {
                    Image(
                        painter = painterResource(id = com.ecapp.ecapp.R.drawable.conejo_1),
                        contentDescription = "Jugador",
                        modifier = Modifier.fillMaxSize()
                    )
                } else {
                    Box(
                        modifier = Modifier
                            .fillMaxSize()
                            .background(
                                when {
                                    playerPosition.value == Pair(row, col) -> Color.Blue
                                    goalPosition.value == Pair(row, col) -> Color.Green
                                    maze[row][col] == 1 -> Color.Black
                                    else -> Color.White
                                }
                            )
                    )
                }
            }

            if (playerPosition.value == goalPosition.value) {
                onReachGoal()
            }
        }
    }
}

@Composable
fun PlayerControls(onMove: (String) -> Unit) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp)
            .background(colorResource(com.ecapp.ecapp.R.color.morado_fondo))
    ) {
        Row(horizontalArrangement = Arrangement.spacedBy(8.dp)) {
            Button(onClick = { onMove("up") }, colors = ButtonDefaults.buttonColors(
                containerColor = colorResource(com.ecapp.ecapp.R.color.morado)  ,     // Color de fondo del botón
                contentColor = colorResource(com.ecapp.ecapp.R.color.white)     // Color del texto o contenido
                // .background(colorResource(com.ecapp.ecapp.R.color.morado_fondo)),
            )) { Text("Arriba") }
        }
        Row(horizontalArrangement = Arrangement.spacedBy(8.dp)) {
            Button(onClick = { onMove("left") },
                colors = ButtonDefaults.buttonColors(
                    containerColor = colorResource(com.ecapp.ecapp.R.color.morado)  ,     // Color de fondo del botón
                    contentColor = colorResource(com.ecapp.ecapp.R.color.white)     // Color del texto o contenido
                    // .background(colorResource(com.ecapp.ecapp.R.color.morado_fondo)),
                )) { Text("Izquierda") }
            Button(onClick = { onMove("down") },
                colors = ButtonDefaults.buttonColors(
                    containerColor = colorResource(com.ecapp.ecapp.R.color.morado)  ,     // Color de fondo del botón
                    contentColor = colorResource(com.ecapp.ecapp.R.color.white)     // Color del texto o contenido
                    // .background(colorResource(com.ecapp.ecapp.R.color.morado_fondo)),
                )) { Text("Abajo") }
            Button(onClick = { onMove("right") },
                colors = ButtonDefaults.buttonColors(
                    containerColor = colorResource(com.ecapp.ecapp.R.color.morado)  ,     // Color de fondo del botón
                    contentColor = colorResource(com.ecapp.ecapp.R.color.white)     // Color del texto o contenido
                    // .background(colorResource(com.ecapp.ecapp.R.color.morado_fondo)),
                )) { Text("Derecha") }
        }
    }
}



