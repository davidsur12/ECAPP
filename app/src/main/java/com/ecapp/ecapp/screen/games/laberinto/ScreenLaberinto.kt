package com.ecapp.ecapp.screen.games.laberinto

import android.annotation.SuppressLint
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material3.Button
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.IntOffset
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.ecapp.ecapp.screen.Games

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun ScreenGameLaberinto(navController: NavController){


    Scaffold {

        gameLaberinto(navController)
    }
}
val maze = listOf(
    listOf(1, 1, 1, 1, 1, 1, 1),
    listOf(1, 0, 0, 0, 1, 0, 1),
    listOf(1, 0, 1, 0, 1, 0, 1),
    listOf(1, 0, 1, 0, 0, 0, 1),
    listOf(1, 1, 1, 1, 1, 1, 1)
)

@Composable
fun gameLaberinto(navController: NavController){



/*
    // Variables para almacenar la posición de la imagen
    var offsetX by remember { mutableStateOf(0f) }
    var offsetY by remember { mutableStateOf(0f) }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.LightGray),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        // Imagen que se moverá
        Image(
            painter = painterResource(id = R.drawable.ic_launcher_foreground), // Usa tu recurso de imagen
            contentDescription = "Movable Image",
            modifier = Modifier
                .size(100.dp)
                .offset { IntOffset(offsetX.roundToInt(), offsetY.roundToInt()) }
        )

        // Espacio entre la imagen y los botones
        Spacer(modifier = Modifier.height(20.dp))

        // Botones de control
        Row(
            horizontalArrangement = Arrangement.Center,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Button(onClick = { offsetX -= 20f }) {
                Text("Izquierda")
            }
            Spacer(modifier = Modifier.width(10.dp))
            Column {
                Button(onClick = { offsetY -= 20f }) {
                    Text("Arriba")
                }
                Spacer(modifier = Modifier.height(10.dp))
                Button(onClick = { offsetY += 20f }) {
                    Text("Abajo")
                }
            }
            Spacer(modifier = Modifier.width(10.dp))
            Button(onClick = { offsetX = 20f }) {
                Text("Derecha")
            }
        }
    }

*/

    val gridSize = 7 // Tamaño del laberinto (ancho y alto)
/*
    Box(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        contentAlignment = Alignment.Center
    ) {
        LazyVerticalGrid(
            columns = GridCells.Fixed(gridSize),
            modifier = Modifier
                .size(300.dp) // Ajusta el tamaño del laberinto
        ) {
            items(maze.flatten()) { cell ->
                MazeCell(isWall = cell == 1)
            }
        }
    }

    */


}
/*

@Composable
fun MazeCell(isWall: Boolean) {
    Box(
        modifier = Modifier
            .size(40.dp)
            .padding(2.dp)
            .background(if (isWall) Color.Black else Color.White)
            .border(1.dp, Color.Gray)
    )
}

@Composable
fun InteractiveMazeGame() {

    var playerPosition by remember { mutableStateOf(Pair(1, 1)) }

    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        MazeGameWithPlayer(playerPosition)

        // Botones para mover al jugador
        Row(
            horizontalArrangement = Arrangement.SpaceEvenly,
            modifier = Modifier.fillMaxWidth().padding(16.dp)
        ) {
            Button(onClick = { movePlayer("up", playerPosition)?.let { playerPosition = it } }) {
                Text("↑")
            }
            Button(onClick = { movePlayer("left", playerPosition)?.let { playerPosition = it } }) {
                Text("←")
            }
            Button(onClick = { movePlayer("down", playerPosition)?.let { playerPosition = it } }) {
                Text("↓")
            }
            Button(onClick = { movePlayer("right", playerPosition)?.let { playerPosition = it } }) {
                Text("→")
            }
        }
    }
}

@Composable
fun MazeGameWithPlayer(playerPosition: Pair<Int, Int>) {

    val gridSize = 7

    LazyVerticalGrid(
        columns = GridCells.Fixed(gridSize),
        modifier = Modifier.size(300.dp)
    ) {
        items(maze.flatten().withIndex()) { (index, cell) ->
            val row = index / gridSize
            val col = index % gridSize
            val isPlayerPosition = playerPosition == Pair(row, col)

            Box(
                modifier = Modifier
                    .size(40.dp)
                    .padding(2.dp)
                    .background(
                        when {
                            isPlayerPosition -> Color.Blue
                            cell == 1 -> Color.Black
                            else -> Color.White
                        }
                    )
                    .border(1.dp, Color.Gray)
            )
        }
    }


    */

/*
}

fun movePlayer(direction: String, currentPosition: Pair<Int, Int>): Pair<Int, Int>? {
    val (x, y) = currentPosition
    return when (direction) {
        "up" -> if (maze.getOrNull(x - 1)?.get(y) == 0) Pair(x - 1, y) else null
        "down" -> if (maze.getOrNull(x + 1)?.get(y) == 0) Pair(x + 1, y) else null
        "left" -> if (maze[x].getOrNull(y - 1) == 0) Pair(x, y - 1) else null
        "right" -> if (maze[x].getOrNull(y + 1) == 0) Pair(x, y + 1) else null
        else -> null
    }
}

*/