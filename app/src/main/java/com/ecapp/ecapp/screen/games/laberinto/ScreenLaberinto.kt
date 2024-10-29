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

import android.content.Context
import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource


@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun ScreenGameLaberinto(navController: NavController){


    Scaffold(modifier = Modifier.background(colorResource(com.ecapp.ecapp.R.color.morado_fondo))) {


        MazeGameApp(navController)


    }
}

@Composable
fun MazeGameApp(navController: NavController) {
    val context = LocalContext.current
    val currentLevel = remember { mutableStateOf(0) }
    val mazes = listOf(  listOf(
        listOf(1, 1, 1, 1, 1, 1, 1),
        listOf(1, 0, 0, 0, 1, 0, 1),
        listOf(1, 0, 1, 0, 1, 0, 1),
        listOf(1, 0, 1, 0, 0, 0, 1),
        listOf(1, 1, 1, 1, 1, 1, 1)
    ),
        listOf(
            listOf(1, 1, 1, 1, 1, 1, 1),
            listOf(1, 0, 0, 1, 0, 0, 1),
            listOf(1, 0, 0, 1, 1, 0, 1),
            listOf(1, 0, 0, 0, 0, 0, 1),
            listOf(1, 1, 1, 1, 1, 1, 1)
        ),
        // Agrega más laberintos aquí
        listOf(
            listOf(1, 1, 1, 1, 1, 1, 1),
            listOf(1, 0, 1, 0, 1, 0, 1),
            listOf(1, 0, 1, 0, 0, 0, 1),
            listOf(1, 0, 1, 1, 1, 0, 1),
            listOf(1, 0, 0, 0, 0, 0, 1),
            listOf(1, 1, 1, 1, 1, 1, 1)
        ),
        listOf(
            listOf(1, 1, 1, 1, 1, 1, 1),
            listOf(1, 0, 0, 1, 0, 0, 1),
            listOf(1, 1, 0, 1, 0, 1, 1),
            listOf(1, 0, 0, 0, 0, 0, 1),
            listOf(1, 1, 1, 1, 1, 1, 1)
        ),
        listOf(
            listOf(1, 1, 1, 1, 1, 1, 1),
            listOf(1, 0, 0, 1, 1, 0, 1),
            listOf(1, 0, 0, 0, 0, 0, 1),
            listOf(1, 1, 1, 1, 1, 0, 0),
            listOf(1, 1, 1, 1, 1, 0, 1)
        ))

    // Posición inicial y meta
    val playerPosition = remember { mutableStateOf(Pair(1, 1)) }
    val goalPosition = remember { mutableStateOf(Pair(3, 5)) } // Asegúrate de que esto sea válido para cada laberinto
Column(modifier = Modifier.background(colorResource(com.ecapp.ecapp.R.color.morado_fondo))) {
    MazeGame(mazes[currentLevel.value], playerPosition, goalPosition) {
    // Si el jugador llega a la meta, avanza al siguiente nivel
    if (playerPosition.value == goalPosition.value) {
        if (currentLevel.value < mazes.size - 1) {
            currentLevel.value++ // Aumenta el nivel
            playerPosition.value = Pair(1, 1) // Reiniciar la posición del jugador
            // Aquí podrías establecer la nueva meta según el nuevo laberinto
        } else {
            Toast.makeText(context, "¡Felicidades! Has completado todos los niveles.", Toast.LENGTH_SHORT).show()
        }
    }
}

    PlayerControls { direction ->
        movePlayer(direction, playerPosition.value, mazes[currentLevel.value]) { newPos ->
            playerPosition.value = newPos
        }
    }}
    // Mostrar el laberinto actual

}


@Composable
fun MazeGame(maze: List<List<Int>>, playerPosition: MutableState<Pair<Int, Int>>, goalPosition: MutableState<Pair<Int, Int>>, onReachGoal: () -> Unit) {
    val gridSize = maze.size

    LazyVerticalGrid(
        columns = GridCells.Fixed(gridSize),
        modifier = Modifier
            .fillMaxSize()
            .background(Color.Gray)
    ) {
        items(maze.flatten().size) { index ->
            val row = index / gridSize
            val col = index % gridSize

            Box(modifier = Modifier.size(50.dp)) {
                when {
                    playerPosition.value == Pair(row, col) -> {
                        // Cargar la imagen del jugador
                        Image(
                            painter = painterResource(id = com.ecapp.ecapp.R.drawable.caballo9), // Asegúrate de usar el nombre correcto de la imagen
                            contentDescription = "Jugador",
                            modifier = Modifier.size(50.dp)
                        )
                    }
                    goalPosition.value == Pair(row, col) -> {
                        // Puedes dejar el cuadro verde o también usar una imagen
                        Box(modifier = Modifier.size(50.dp).background(Color.Green))
                    }
                    maze[row][col] == 1 -> {
                        Box(modifier = Modifier.size(50.dp).background(Color.Black))
                    }
                    else -> {
                        Box(modifier = Modifier.size(50.dp).background(Color.White))
                    }
                }
            }

            // Verificar si el jugador ha llegado a la meta
            if (playerPosition.value == goalPosition.value) {
                onReachGoal() // Llama a la función cuando se alcanza la meta
            }
        }
    }
}




/*
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
        columns = GridCells.Fixed(cols),
        modifier = Modifier
            .fillMaxWidth()
            .background(colorResource(com.ecapp.ecapp.R.color.morado_fondo))
    ) {
        // Iterar sobre cada fila y columna
        items(rows * cols) { index ->
            val row = index / cols
            val col = index % cols

            // Aquí se crea la Box para cada celda del laberinto
            Box(
                modifier = Modifier
                    .size(50.dp)
                    .background(
                        when {
                            playerPosition.value == Pair(row, col) -> Color.Blue // Jugador
                            goalPosition.value == Pair(row, col) -> Color.Green // Meta
                            maze[row][col] == 1 -> Color.Black // Pared
                            else -> Color.White // Espacio vacío
                        }
                    )
            )

            // Verificar si el jugador ha llegado a la meta
            if (playerPosition.value == goalPosition.value) {
                onReachGoal() // Llama a la función cuando se alcanza la meta
            }
        }
    }
}


*/
fun movePlayer(direction: String, currentPosition: Pair<Int, Int>, maze: List<List<Int>>, onPositionChanged: (Pair<Int, Int>) -> Unit) {
    val (row, col) = currentPosition
    val newPosition = when (direction) {
        "up" -> Pair(row - 1, col)
        "down" -> Pair(row + 1, col)
        "left" -> Pair(row, col - 1)
        "right" -> Pair(row, col + 1)
        else -> currentPosition
    }

    // Verificar si la nueva posición está dentro de los límites y no es una pared
    if (newPosition.first in maze.indices && newPosition.second in maze[newPosition.first].indices &&
        maze[newPosition.first][newPosition.second] == 0) {
        onPositionChanged(newPosition) // Actualiza la posición del jugador
    }
}


@Composable
fun PlayerControls(onMove: (String) -> Unit) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier.fillMaxWidth().padding(16.dp)
            .background(colorResource(com.ecapp.ecapp.R.color.morado_fondo))
    ) {
        Row(horizontalArrangement = Arrangement.spacedBy(8.dp)) {
            Button(onClick = { onMove("up") }) { Text("Arriba") }
        }
        Row(horizontalArrangement = Arrangement.spacedBy(8.dp)) {
            Button(onClick = { onMove("left") }) { Text("Izquierda") }
            Button(onClick = { onMove("down") }) { Text("Abajo") }
            Button(onClick = { onMove("right") }) { Text("Derecha") }
        }
    }
}

/*
@Composable
fun MazeGameApp(navController: NavController) {
    val context = LocalContext.current
    val currentLevel = remember { mutableStateOf(0) }
    val mazes = listOf(
        // Define tus laberintos aquí
        listOf(
            listOf(1, 1, 1, 1, 1, 1, 1),
            listOf(1, 0, 0, 0, 1, 0, 1),
            listOf(1, 0, 1, 0, 1, 0, 1),
            listOf(1, 0, 1, 0, 0, 0, 1),
            listOf(1, 1, 1, 1, 1, 1, 1)
        )
        // Agrega más laberintos según sea necesario
    )

    val playerPosition = remember { mutableStateOf(Pair(1, 1)) }
    val goalPosition = remember { mutableStateOf(Pair(4, 5)) }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White) // Asegúrate de que el fondo sea visible
    ) {
        // Mostrar el laberinto
        MazeGame(mazes[currentLevel.value], playerPosition, goalPosition) {
            if (playerPosition.value == goalPosition.value) {
                if (currentLevel.value < mazes.size - 1) {
                    currentLevel.value++
                    playerPosition.value = Pair(1, 1)
                    goalPosition.value = Pair(4, 5) // Cambia esto si el laberinto cambia
                } else {
                    Toast.makeText(context, "¡Felicidades! Has completado todos los niveles.", Toast.LENGTH_SHORT).show()
                }
            }
        }

        // Control del jugador en la parte inferior
        PlayerControls { direction ->
            movePlayer(direction, playerPosition.value, mazes[currentLevel.value]) { newPos ->
                playerPosition.value = newPos
            }
        }
    }
}

@Composable
fun MazeGame(lists: List<List<Int>>, playerPosition: MutableState<Pair<Int, Int>>, goalPosition: MutableState<Pair<Int, Int>>, content: () -> Unit) {

}


fun movePlayer(direction: String, currentPosition: Pair<Int, Int>, maze: List<List<Int>>, onPositionChanged: (Pair<Int, Int>) -> Unit) {
    val (row, col) = currentPosition
    val newPosition = when (direction) {
        "up" -> Pair(row - 1, col)
        "down" -> Pair(row + 1, col)
        "left" -> Pair(row, col - 1)
        "right" -> Pair(row, col + 1)
        else -> currentPosition
    }

    // Verificar si la nueva posición está dentro de los límites y no es una pared
    if (newPosition.first in maze.indices && newPosition.second in maze[newPosition.first].indices &&
        maze[newPosition.first][newPosition.second] == 0) {
        onPositionChanged(newPosition) // Actualiza la posición del jugador
    }
}


@Composable
fun PlayerControls(onMove: (String) -> Unit) {
    Column(
        modifier = Modifier
            .fillMaxHeight()
            .fillMaxWidth()
            .padding(16.dp),
        verticalArrangement = Arrangement.SpaceBetween // Esto separa los botones del resto
    ) {
        // Espaciador para ocupar el espacio superior
        Spacer(modifier = Modifier.weight(1f))

        // Botones de control
        Row(
            horizontalArrangement = Arrangement.spacedBy(8.dp),
            modifier = Modifier.fillMaxWidth() // Asegura que los botones ocupen todo el ancho
        ) {
            Button(onClick = { onMove("up") }) { Text("Arriba") }
        }
        Row(
            horizontalArrangement = Arrangement.spacedBy(8.dp),
            modifier = Modifier.fillMaxWidth() // Asegura que los botones ocupen todo el ancho
        ) {
            Button(onClick = { onMove("left") }) { Text("Izquierda") }
            Button(onClick = { onMove("down") }) { Text("Abajo") }
            Button(onClick = { onMove("right") }) { Text("Derecha") }
        }
    }
}


*/

/*
@Composable
fun MazeGameApp(navController: NavController) {
    val context = LocalContext.current
    val currentLevel = remember { mutableStateOf(0) }
    val mazes = listOf(  listOf(
        listOf(1, 1, 1, 1, 1, 1, 1),
        listOf(1, 0, 0, 0, 1, 0, 1),
        listOf(1, 0, 1, 0, 1, 0, 1),
        listOf(1, 0, 1, 0, 0, 0, 1),
        listOf(1, 1, 1, 1, 1, 1, 1)
    ),
        listOf(
            listOf(1, 1, 1, 1, 1, 1, 1),
            listOf(1, 0, 0, 1, 0, 0, 1),
            listOf(1, 0, 0, 1, 1, 0, 1),
            listOf(1, 0, 0, 0, 0, 0, 1),
            listOf(1, 1, 1, 1, 1, 1, 1)
        ),
        // Agrega más laberintos aquí
        listOf(
            listOf(1, 1, 1, 1, 1, 1, 1),
            listOf(1, 0, 1, 0, 1, 0, 1),
            listOf(1, 0, 1, 0, 0, 0, 1),
            listOf(1, 0, 1, 1, 1, 0, 1),
            listOf(1, 0, 0, 0, 0, 0, 1),
            listOf(1, 1, 1, 1, 1, 1, 1)
        ),
        listOf(
            listOf(1, 1, 1, 1, 1, 1, 1),
            listOf(1, 0, 0, 1, 0, 0, 1),
            listOf(1, 1, 0, 1, 0, 1, 1),
            listOf(1, 0, 0, 0, 0, 0, 1),
            listOf(1, 1, 1, 1, 1, 1, 1)
        ),
        listOf(
            listOf(1, 1, 1, 1, 1, 1, 1),
            listOf(1, 0, 0, 1, 1, 0, 1),
            listOf(1, 0, 0, 0, 0, 0, 1),
            listOf(1, 1, 1, 1, 1, 0, 0),
            listOf(1, 1, 1, 1, 1, 0, 1)
        ))

    // Posición inicial y meta
    val playerPosition = remember { mutableStateOf(Pair(1, 1)) }
    val goalPosition = remember { mutableStateOf(Pair(3, 5)) } // Asegúrate de que esto sea válido para cada laberinto

    // Mostrar el laberinto actual
    MazeGame(mazes[currentLevel.value], playerPosition, goalPosition) {
        // Si el jugador llega a la meta, avanza al siguiente nivel
        if (playerPosition.value == goalPosition.value) {
            if (currentLevel.value < mazes.size - 1) {
                currentLevel.value++ // Aumenta el nivel
                playerPosition.value = Pair(1, 1) // Reiniciar la posición del jugador
                // Aquí podrías establecer la nueva meta según el nuevo laberinto
            } else {
                Toast.makeText(context, "¡Felicidades! Has completado todos los niveles.", Toast.LENGTH_SHORT).show()
            }
        }
    }

    PlayerControls { direction ->
        movePlayer(direction, playerPosition.value, mazes[currentLevel.value]) { newPos ->
            playerPosition.value = newPos
        }
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
        columns = GridCells.Fixed(cols),
        modifier = Modifier
            .fillMaxSize()
            .background(Color.Gray)
    ) {
        // Iterar sobre cada fila y columna
        items(rows * cols) { index ->
            val row = index / cols
            val col = index % cols

            // Aquí se crea la Box para cada celda del laberinto
            Box(
                modifier = Modifier
                    .size(50.dp)
                    .background(
                        when {
                            playerPosition.value == Pair(row, col) -> Color.Blue // Jugador
                            goalPosition.value == Pair(row, col) -> Color.Green // Meta
                            maze[row][col] == 1 -> Color.Black // Pared
                            else -> Color.White // Espacio vacío
                        }
                    )
            )

            // Verificar si el jugador ha llegado a la meta
            if (playerPosition.value == goalPosition.value) {
                onReachGoal() // Llama a la función cuando se alcanza la meta
            }
        }
    }
}

fun movePlayer(direction: String, currentPosition: Pair<Int, Int>, maze: List<List<Int>>, onPositionChanged: (Pair<Int, Int>) -> Unit) {
    val (row, col) = currentPosition
    val newPosition = when (direction) {
        "up" -> Pair(row - 1, col)
        "down" -> Pair(row + 1, col)
        "left" -> Pair(row, col - 1)
        "right" -> Pair(row, col + 1)
        else -> currentPosition
    }

    // Verificar si la nueva posición está dentro de los límites y no es una pared
    if (newPosition.first in maze.indices && newPosition.second in maze[newPosition.first].indices &&
        maze[newPosition.first][newPosition.second] == 0) {
        onPositionChanged(newPosition) // Actualiza la posición del jugador
    }
}


@Composable
fun PlayerControls(onMove: (String) -> Unit) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier.fillMaxWidth().padding(16.dp)
    ) {
        Row(horizontalArrangement = Arrangement.spacedBy(8.dp)) {
            Button(onClick = { onMove("up") }) { Text("Arriba") }
        }
        Row(horizontalArrangement = Arrangement.spacedBy(8.dp)) {
            Button(onClick = { onMove("left") }) { Text("Izquierda") }
            Button(onClick = { onMove("down") }) { Text("Abajo") }
            Button(onClick = { onMove("right") }) { Text("Derecha") }
        }
    }
}
*/



