package com.ecapp.ecapp.screen.games.sopa_de_letras

import android.annotation.SuppressLint
import android.util.Log
import android.widget.Toast
import androidx.activity.compose.BackHandler
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.gestures.snapping.SnapPosition
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.lazy.grid.itemsIndexed
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.ecapp.ecapp.navegation.AppScreens
import com.ecapp.ecapp.screen.games.secuencia.gameSecuencia
import com.ecapp.ecapp.utils.DateUser
import kotlin.random.Random


@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun ScreenGameSopaLetras(navController: NavController){
    Scaffold {


        //WordSearchGame()
        //gameSopaLetras(navController)
        gameSopaLetrasNivel1(navController)
    }
}

@Composable
fun gameSopaLetras(navController: NavController){

Text("sopa de letras")



}

/*
@Composable
fun WordSearchGame() {
    val gridSize = 10
    val words = listOf("PERRO", "GATO", "LEON", "TIGRE", "ELEFANTE")
    val grid = remember { generateWordSearchGrid(gridSize, words) }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        for (row in grid) {
            Row {
                for (cell in row) {
                    Box(
                        contentAlignment = Alignment.Center,
                        modifier = Modifier
                            .size(30.dp)
                            .padding(2.dp)
                            .background(Color.LightGray, RoundedCornerShape(4.dp))
                            .clickable { /* Add Click Logic Here */ }
                    ) {
                        Text(
                            text = cell.toString(),
                            fontSize = 16.sp,
                            fontWeight = FontWeight.Bold
                        )
                    }
                }
            }
        }
    }
}

fun generateWordSearchGrid(size: Int, words: List<String>): Array<CharArray> {
    // Generate an empty grid
    val grid = Array(size) { CharArray(size) { ' ' } }

    // Place words in the grid
    for (word in words) {
        placeWordInGrid(word, grid)
    }

    // Fill empty spaces with random letters
    for (i in grid.indices) {
        for (j in grid[i].indices) {
            if (grid[i][j] == ' ') {
                grid[i][j] = ('A'..'Z').random()
            }
        }
    }

    return grid
}

fun placeWordInGrid(word: String, grid: Array<CharArray>) {
    val size = grid.size
    val random = Random(System.currentTimeMillis())

    // Randomly choose direction: 0 -> Horizontal, 1 -> Vertical
    val direction = random.nextInt(2)

    var placed = false
    while (!placed) {
        val row = random.nextInt(size)
        val col = random.nextInt(size)

        if (direction == 0 && col + word.length <= size) { // Horizontal
            if (canPlaceWord(word, grid, row, col, direction)) {
                for (i in word.indices) {
                    grid[row][col + i] = word[i]
                }
                placed = true
            }
        } else if (direction == 1 && row + word.length <= size) { // Vertical
            if (canPlaceWord(word, grid, row, col, direction)) {
                for (i in word.indices) {
                    grid[row + i][col] = word[i]
                }
                placed = true
            }
        }
    }
}

fun canPlaceWord(word: String, grid: Array<CharArray>, row: Int, col: Int, direction: Int): Boolean {
    for (i in word.indices) {
        if (direction == 0 && grid[row][col + i] != ' ' && grid[row][col + i] != word[i]) return false // Horizontal
        if (direction == 1 && grid[row + i][col] != ' ' && grid[row + i][col] != word[i]) return false // Vertical
    }
    return true
}

*/

@Composable
fun gameSopaLetrasNivel1(navController: NavController) {

    BackHandler{
        navController.navigate("screenGames") {
            popUpTo("ScreenGameSopaLetras") { inclusive = true } // Elimina la pantalla actual de la pila
        }
    }



    val context = LocalContext.current
    // Ejemplo de letras para la sopa de letras
    val letters = listOf(
        'P', 'E', 'R', 'R', 'O',
        'F', 'G', 'H', 'I', 'T',
        'K', 'L', 'M', 'N', 'A',
        'T', 'I', 'G', 'R', 'G',
        'U', 'L', 'E', 'O', 'N'
    )

    val indicesCorrectos = listOf(0,1,2,3,4,9,14,19,21,22,23,24,15,16,17,18)

    // Tamaño del tablero
    val gridSize = 5

    // Lista mutable para almacenar los índices seleccionados
    val selectedIndices = remember { mutableStateListOf<Int>() }
    var vidas by remember { mutableStateOf(0) }
    var cambioListaSelected by remember { mutableStateOf(0) }
    vidas = DateUser.vidasSopaLetras
    cambioListaSelected = 0

Column(    modifier = Modifier
    .fillMaxSize().background(colorResource(com.ecapp.ecapp.R.color.morado_fondo)),
    verticalArrangement = Arrangement.Top,  // Centra los elementos verticalmente
    horizontalAlignment = Alignment.CenterHorizontally) {

    Spacer(modifier = Modifier.height(50.dp))
    Text("Actividad de Sopa de letras", fontSize = 25.sp,  textAlign = TextAlign.Center,
        modifier = Modifier.fillMaxWidth(), )
    Spacer(modifier = Modifier.height(50.dp))
    Text("Vidas: ${vidas}",  fontSize = 20.sp)
    Text("Nivel: ${DateUser.nivelSopaLetras}",  fontSize = 20.sp)
    Spacer(modifier = Modifier.height(15.dp))
    Text("Encuentra las Siguientes Palabras", fontSize = 20.sp)
    Spacer(modifier = Modifier.height(10.dp))
    Text("PERRO, GATO, LEON,TIGRE", fontSize = 18.sp)


    Box(
    modifier = Modifier
        //.fillMaxSize()
        .background(colorResource(com.ecapp.ecapp.R.color.morado_fondo))
        .padding(16.dp)
) {
    LazyVerticalGrid(
        columns = GridCells.Fixed(gridSize),
        modifier = Modifier
            .fillMaxSize()
            .align(Alignment.Center)
    ) {
        itemsIndexed(letters) { index, letter ->
            LetterBox(letter, index, selectedIndices)
        }


    }



    // Mostrar los índices seleccionados
/*
    Text(
        text = "Índices seleccionados: ${selectedIndices.joinToString(", ")}",
        modifier = Modifier
            .align(Alignment.BottomCenter)
            .padding(16.dp),
        color = Color.Black
    )
        */
    //val completado = indicesCorrectos.containsAll(selectedIndices)
        val completado = selectedIndices.containsAll(indicesCorrectos)
    //verifico si todas la sopa de letras esta llena correctamente
    if(completado && selectedIndices.size>= indicesCorrectos.size){




        val errores = selectedIndices.filterNot { indicesCorrectos.contains(it) }
        var contErrores = errores.count()
        DateUser.erroresSopaLetras = contErrores

        if( contErrores>=2){
            contErrores=0
            DateUser.vidasSopaLetras=4
        }
        if(DateUser.nivelSopaLetras==1){
            Toast.makeText(context, "Sopa de letras completada", Toast.LENGTH_SHORT).show()
            DateUser.nivelSopaLetras=2
            navController.navigate(AppScreens.screenGameOverSopaLetrasNivel2.route)

        }



    }
    else{


        // Toast.makeText(context, "Sopa de letras no completada", Toast.LENGTH_SHORT).show()
        //if(indicesCorrectos.contains(selectedIndices[selectedIndices.size-1]) )
        if(selectedIndices.size>0  )
        {// &&   cambioListaSelected != selectedIndices.size

            if( indicesCorrectos.contains(selectedIndices[selectedIndices.size-1]) &&  cambioListaSelected != selectedIndices.size){
                cambioListaSelected = selectedIndices.size
            }
            /*
            else{




                Toast.makeText(context, " tamaño ${selectedIndices.size} indice agregado ${selectedIndices[selectedIndices.size-1]}", Toast.LENGTH_SHORT).show()
                //si el ultimo  indice no corrresponde a ninguna palabra
                DateUser.erroresSopaLetras++
                if(DateUser.erroresSopaLetras == 2 &&  cambioListaSelected != selectedIndices.size){
                    cambioListaSelected = selectedIndices.size
                    //por cada dos errores quito una vida

                    DateUser.vidasSopaLetras--

                    DateUser.erroresSopaLetras = 0
                    vidas=DateUser.vidasSecuencia
                    Toast.makeText(context, "vidas ${DateUser.vidasSopaLetras}", Toast.LENGTH_SHORT).show()
                }
                if(DateUser.vidasSopaLetras == 0){
                    //perdiste todas las vidas
                    //pantalla de game over
                    Toast.makeText(context, "vidas ${DateUser.vidasSopaLetras}", Toast.LENGTH_SHORT).show()
                    //  navController.navigate(AppScreens.screenGameOverSopaLetras.route)
                }
            }

*/

        }

    }

}
}
}


@Composable
fun gameSopaLetrasNivel2(navController: NavController) {

    BackHandler{
        navController.navigate("screenGames") {
            popUpTo("gameSopaLetrasNivel2") { inclusive = true } // Elimina la pantalla actual de la pila
        }
    }
    val context = LocalContext.current
    // Ejemplo de letras para la sopa de letras
    val letters = listOf(
        'T', 'E', 'N', 'E', 'D','O','R',
        'P', 'L', 'A', 'T', 'O','Y','P',
        'K', 'V', 'A', 'S', 'O','W','O',
        'C', 'U', 'C', 'H', 'A','R','A',
        'S', 'A', 'R', 'T', 'E','N','O',
    )

    val indicesCorrectos = listOf(0,1,2,3,4,5,6,7,8,9,10,11,15,16,17,18,23,24,25,26,27,28,29,30,31,32,33)

    // Tamaño del tablero
    val gridSize = 5

    // Lista mutable para almacenar los índices seleccionados
    val selectedIndices = remember { mutableStateListOf<Int>() }
    var vidas by remember { mutableStateOf(0) }
    var cambioListaSelected by remember { mutableStateOf(0) }
    vidas = DateUser.vidasSopaLetras
    cambioListaSelected = 0

    Column(    modifier = Modifier
        .fillMaxSize().background(colorResource(com.ecapp.ecapp.R.color.morado_fondo)),
        verticalArrangement = Arrangement.Top,  // Centra los elementos verticalmente
        horizontalAlignment = Alignment.CenterHorizontally) {

        Spacer(modifier = Modifier.height(50.dp))
        Text("Actividad de Sopa de letras", fontSize = 25.sp,  textAlign = TextAlign.Center,
            modifier = Modifier.fillMaxWidth(), )
        Spacer(modifier = Modifier.height(50.dp))
        Text("Vidas: ${vidas}",  fontSize = 20.sp)

        Text("Nivel: ${DateUser.nivelSopaLetras}",  fontSize = 20.sp)
        Spacer(modifier = Modifier.height(15.dp))
        Text("Encuentra las Siguientes Palabras", fontSize = 20.sp)
        Spacer(modifier = Modifier.height(10.dp))
        Text("TRNEDOR, PLATO, VASO, CUCHARA, SARTEN", fontSize = 18.sp)
        /*
   * Tenedores
Cucharas Platos
Vasos Cacerolas
* Sarten
   * */
        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(colorResource(com.ecapp.ecapp.R.color.morado_fondo))
                .padding(16.dp)
        ) {
            LazyVerticalGrid(
                columns = GridCells.Fixed(7),
                modifier = Modifier
                    .fillMaxSize()
                    .align(Alignment.Center)
            ) {
                itemsIndexed(letters) { index, letter ->
                    LetterBox2(letter, index, selectedIndices)
                }
            }

            // Mostrar los índices seleccionados


            //val completado = indicesCorrectos.containsAll(selectedIndices)
            val completado = selectedIndices.containsAll(indicesCorrectos)
            //verifico si todas la sopa de letras esta llena correctamente
            if(completado && selectedIndices.size>= indicesCorrectos.size){

                Toast.makeText(context, "Sopa de letras completada", Toast.LENGTH_SHORT).show()


                val errores = selectedIndices.filterNot { indicesCorrectos.contains(it) }
                var contErrores = errores.count()
                DateUser.erroresSopaLetras = contErrores
                if( contErrores>=2){

                    DateUser.vidasSopaLetras=3
                }
                if(DateUser.nivelSopaLetras==2){
                    Toast.makeText(context, "Sopa de letras completada", Toast.LENGTH_SHORT).show()
                    DateUser.nivelSopaLetras=3
                    navController.navigate(AppScreens.screenGameOverSopaLetrasNivel3.route)

                }
            }
            else{


                // Toast.makeText(context, "Sopa de letras no completada", Toast.LENGTH_SHORT).show()
                //if(indicesCorrectos.contains(selectedIndices[selectedIndices.size-1]) )
                if(selectedIndices.size>0  )
                {// &&   cambioListaSelected != selectedIndices.size

                    if( indicesCorrectos.contains(selectedIndices[selectedIndices.size-1]) &&  cambioListaSelected != selectedIndices.size){
                        cambioListaSelected = selectedIndices.size
                    }

                }

            }

        }
    }
}

@Composable
fun gameSopaLetrasNivel3(navController: NavController) {


    BackHandler{
        navController.navigate("screenGames") {
            popUpTo("gameSopaLetrasNivel3") { inclusive = true } // Elimina la pantalla actual de la pila
        }
    }

   // Argentina Bolivia Brasil Colombia Guyana
    val context = LocalContext.current
    // Ejemplo de letras para la sopa de letras
    val letters = listOf(
        'B', 'O', 'L', 'I', 'V','I','A',
        'B', 'R', 'A', 'S', 'I','L','P',
        'G', 'U', 'A', 'Y', 'A','N','A',
        'P', 'E', 'R', 'U', 'A','R','A',
        'S', 'A', 'P', 'T', 'E','P','O',
    )

    val indicesCorrectos = listOf(0,1,2,3,4,5,6,7,8,9,10,11,12,14,15,16,17,18,19,20,21,22,23,24)

    // Tamaño del tablero
    val gridSize = 5

    // Lista mutable para almacenar los índices seleccionados
    val selectedIndices = remember { mutableStateListOf<Int>() }
    var vidas by remember { mutableStateOf(0) }
    var cambioListaSelected by remember { mutableStateOf(0) }
    vidas = DateUser.vidasSopaLetras
    cambioListaSelected = 0

    Column(    modifier = Modifier
        .fillMaxSize().background(colorResource(com.ecapp.ecapp.R.color.morado_fondo)),
        verticalArrangement = Arrangement.Top,  // Centra los elementos verticalmente
        horizontalAlignment = Alignment.CenterHorizontally) {

        Spacer(modifier = Modifier.height(50.dp))
        Text("Actividad de Sopa de letras", fontSize = 25.sp,  textAlign = TextAlign.Center,
            modifier = Modifier.fillMaxWidth(), )
        Spacer(modifier = Modifier.height(50.dp))
        Text("Vidas: ${vidas}",  fontSize = 20.sp)
        Text("Nivel: ${DateUser.nivelSopaLetras}",  fontSize = 20.sp)
        Spacer(modifier = Modifier.height(15.dp))
        Text("Encuentra las Siguientes Palabras", fontSize = 20.sp)
        Spacer(modifier = Modifier.height(10.dp))
        Text("BOLIVIA, BRASIL, GUAYANA, PERU")

        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(colorResource(com.ecapp.ecapp.R.color.morado_fondo))
                .padding(16.dp)
        ) {
            LazyVerticalGrid(
                columns = GridCells.Fixed(7),
                modifier = Modifier
                    .fillMaxSize()
                    .align(Alignment.Center)
            ) {
                itemsIndexed(letters) { index, letter ->
                    LetterBox3(letter, index, selectedIndices)
                }
            }

            // Mostrar los índices seleccionados


            //val completado = indicesCorrectos.containsAll(selectedIndices)
            val completado = selectedIndices.containsAll(indicesCorrectos)
            //verifico si todas la sopa de letras esta llena correctamente
            if(completado && selectedIndices.size>= indicesCorrectos.size){

                Toast.makeText(context, "Sopa de letras completada", Toast.LENGTH_SHORT).show()


                val errores = selectedIndices.filterNot { indicesCorrectos.contains(it) }
                var contErrores = errores.count()
                DateUser.erroresSopaLetras = contErrores
                if( contErrores>=2){
                    contErrores=0
                    DateUser.vidasSopaLetras=2
                }
                if(DateUser.nivelSopaLetras==3){
                    DateUser.nivelSopaLetras=4

                    try {
                        navController.navigate(AppScreens.screenFelicitacionesGameSopaLetras.route)
                    }catch (ex:Exception){
                        Toast.makeText(context, "Eroor  al cmabiar  $ex", Toast.LENGTH_SHORT).show()
                    Log.d("errorn",ex.toString() )
                    }

                   // Toast.makeText(context, "Sopa de letras completada", Toast.LENGTH_SHORT).show()
                    //DateUser.nivelSopaLetras=0


                }




            }
            else{


                // Toast.makeText(context, "Sopa de letras no completada", Toast.LENGTH_SHORT).show()
                //if(indicesCorrectos.contains(selectedIndices[selectedIndices.size-1]) )
                if(selectedIndices.size>0  )
                {// &&   cambioListaSelected != selectedIndices.size

                    if( indicesCorrectos.contains(selectedIndices[selectedIndices.size-1]) &&  cambioListaSelected != selectedIndices.size){
                        cambioListaSelected = selectedIndices.size
                    }

                }

            }

        }
    }
}


@Composable
fun LetterBox(letter: Char, index: Int, selectedIndices: MutableList<Int>) {
    val context = LocalContext.current
    var isSelected by remember { mutableStateOf(false) }

    Box(
        modifier = Modifier
            .size(60.dp)
            .padding(4.dp)
            .clip(RoundedCornerShape(8.dp))
            .background(if (isSelected) Color.Cyan else Color.White)
            .clickable {
                isSelected = !isSelected
                if (isSelected) {
                    selectedIndices.add(index) // Agregar índice a la lista
                } else {
                    selectedIndices.remove(index) // Eliminar índice si se deselecciona
                }
               // Toast.makeText(context, "Índice: $index", Toast.LENGTH_SHORT).show()
            },
        contentAlignment = Alignment.Center
    ) {
        Text(
            text = letter.toString(),
            fontSize = 24.sp,
            color = Color.Black,
            textAlign = TextAlign.Center
        )
    }
}
@Composable
fun LetterBox2(letter: Char, index: Int, selectedIndices: MutableList<Int>) {
    val context = LocalContext.current
    var isSelected by remember { mutableStateOf(false) }

    Box(
        modifier = Modifier
            .size(60.dp)
            .padding(4.dp)
            .clip(RoundedCornerShape(8.dp))
            .background(if (isSelected) Color.Cyan else Color.White)
            .clickable {
                isSelected = !isSelected
                if (isSelected) {
                    selectedIndices.add(index) // Agregar índice a la lista
                } else {
                    selectedIndices.remove(index) // Eliminar índice si se deselecciona
                }
                // Toast.makeText(context, "Índice: $index", Toast.LENGTH_SHORT).show()
            },
        contentAlignment = Alignment.Center
    ) {
        Text(
            text = letter.toString(),
            fontSize = 24.sp,
            color = Color.Black,
            textAlign = TextAlign.Center
        )
    }
}
@Composable
fun LetterBox3(letter: Char, index: Int, selectedIndices: MutableList<Int>) {
    val context = LocalContext.current
    var isSelected by remember { mutableStateOf(false) }

    Box(
        modifier = Modifier
            .size(60.dp)
            .padding(4.dp)
            .clip(RoundedCornerShape(8.dp))
            .background(if (isSelected) Color.Cyan else Color.White)
            .clickable {
                isSelected = !isSelected
                if (isSelected) {
                    selectedIndices.add(index) // Agregar índice a la lista
                } else {
                    selectedIndices.remove(index) // Eliminar índice si se deselecciona
                }
                // Toast.makeText(context, "Índice: $index", Toast.LENGTH_SHORT).show()
            },
        contentAlignment = Alignment.Center
    ) {
        Text(
            text = letter.toString(),
            fontSize = 24.sp,
            color = Color.Black,
            textAlign = TextAlign.Center
        )
    }
}



