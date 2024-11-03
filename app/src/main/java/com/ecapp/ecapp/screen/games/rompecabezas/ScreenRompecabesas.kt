package com.ecapp.ecapp.screen.games.rompecabezas

import android.annotation.SuppressLint
import androidx.compose.material3.Scaffold

import androidx.navigation.NavController
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material3.Text
import androidx.compose.runtime.*

import android.widget.Toast
import androidx.activity.compose.BackHandler

import androidx.compose.foundation.Image

import androidx.compose.foundation.gestures.detectDragGestures
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment

import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.layout.LayoutCoordinates
import androidx.compose.ui.layout.onGloballyPositioned
import androidx.compose.ui.layout.positionInRoot
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.IntOffset
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import kotlin.math.roundToInt


import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color

import androidx.compose.ui.res.colorResource
import com.ecapp.ecapp.navegation.AppScreens
import com.ecapp.ecapp.utils.Configuraciones
import com.ecapp.ecapp.utils.DateUser


/*

rompe cabezas comienza con 3 vidas se completa cuando cada imagen este en su
respectiva posicion
*/

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun ScreenRompecabesas(navController: NavController){
    Scaffold {

       // GameRompecabesas(navController)
        gameRompeCabezasNivel1(navController)
    }
}


// Simagenes de los 3 niveles se dividieron en 9 partes


fun getDrawableForImage(index: Int): Int {
    return when (index) {
        0 -> com.ecapp.ecapp.R.drawable.gato1
        1 -> com.ecapp.ecapp.R.drawable.gato2
        2 -> com.ecapp.ecapp.R.drawable.gato3
        3 -> com.ecapp.ecapp.R.drawable.gato9
        4 -> com.ecapp.ecapp.R.drawable.gato4
        5 -> com.ecapp.ecapp.R.drawable.gato5
        6 -> com.ecapp.ecapp.R.drawable.gato8
        7 -> com.ecapp.ecapp.R.drawable.gato7
        8 -> com.ecapp.ecapp.R.drawable.gato6
        else -> com.ecapp.ecapp.R.drawable.uno
    }
}
fun getDrawableForImageCaballo(index: Int): Int {
    return when (index) {
        0 -> com.ecapp.ecapp.R.drawable.caballo9
        1 -> com.ecapp.ecapp.R.drawable.cabllo8
        2 -> com.ecapp.ecapp.R.drawable.caballo7
        3 -> com.ecapp.ecapp.R.drawable.caballo6
        4 -> com.ecapp.ecapp.R.drawable.caballo5
        5 -> com.ecapp.ecapp.R.drawable.caballo4
        6 -> com.ecapp.ecapp.R.drawable.caballo3
        7 -> com.ecapp.ecapp.R.drawable.caballo2
        8 -> com.ecapp.ecapp.R.drawable.caballo1
        else -> com.ecapp.ecapp.R.drawable.uno
    }
}
fun getDrawableForImageCalabaza(index: Int): Int {
    return when (index) {
        0 -> com.ecapp.ecapp.R.drawable.calabaza9
        1 -> com.ecapp.ecapp.R.drawable.calabaza8
        2 -> com.ecapp.ecapp.R.drawable.calabaza7
        3 -> com.ecapp.ecapp.R.drawable.calabaza6
        4 -> com.ecapp.ecapp.R.drawable.calabaza5
        5 -> com.ecapp.ecapp.R.drawable.calabaza4
        6 -> com.ecapp.ecapp.R.drawable.calabaza3
        7 -> com.ecapp.ecapp.R.drawable.calabaza2
        8 -> com.ecapp.ecapp.R.drawable.calabaza1
        else -> com.ecapp.ecapp.R.drawable.uno
    }
}


@Composable
fun gameRompeCabezasNivel1(navController: NavController) {
    BackHandler{
        navController.navigate("screenGames") {
            popUpTo("screenRompecabesas") { inclusive = true } // Elimina la pantalla actual de la pila
        }
    }

    DateUser.nivelRompeCabezas = 1
    DateUser.vidasRompecabesas = 5
    // Variables para la posición de las imágenes (inicialmente en la parte inferior)
    val density = LocalDensity.current
    val imageSize = 80.dp//tamaño de cada imagen
    val imageSizePx = with(density) { imageSize.toPx() }

    // Inicializar cajas de las imágenes para que aparezcan debajo de la columna
    val offsets = remember { mutableStateListOf<Pair<Float, Float>>().apply {
        repeat(9) { add(0f to 0f) }
    } }

    // Variables para controlar la visibilidad de las imágenes
    val visibleImages = remember { mutableStateListOf(true, true, true, true, true, true, true, true, true) }

    // Variables para las imágenes de fondo de las cajas
    val boxBackgroundImages = remember { mutableStateListOf<Int?>(null, null, null, null, null, null, null, null, null) }

    // Estados para las coordenadas de las cajas
    val boxCoordinates = remember { mutableStateListOf<LayoutCoordinates?>().apply { repeat(9) { add(null) } } }

    // Contador de imágenes colocadas correctamente
    var correctPlacementCounter by remember { mutableStateOf(0) }

    // Contador de errores
    var errorCounter by remember { mutableStateOf(0) }

    Box(
        //comenzamos con un box para que las imagenes se puedan superponer por toda la pantalla
        modifier = Modifier
            .fillMaxSize()
            .background(colorResource(com.ecapp.ecapp.R.color.morado_fondo))
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp)
                .background(colorResource(com.ecapp.ecapp.R.color.morado_fondo)),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            // Agregar el encabezado en la parte superior
            Spacer(modifier = Modifier.height(50.dp))
            Text(
                text = "Rompe Cabezas",
                fontSize = Configuraciones.fontSizeTitulos.sp,
                color = Color.White
            )
            Spacer(modifier = Modifier.height(20.dp))
            Text(
                text = "Nivel ${DateUser.nivelRompeCabezas}",
                fontSize = Configuraciones.fontSizeNormal.sp,
                color = Color.White
            )
            Text(
                text = "Vidas ${DateUser.vidasRompecabesas}",
                fontSize = Configuraciones.fontSizeNormal.sp,
                color = Color.White
            )

            Spacer(modifier = Modifier.height(20.dp))

            // cuadricula de 3x3 para las cajas
            LazyVerticalGrid(
                columns = GridCells.Fixed(3),
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 100.dp)
                    .height(350.dp) // Altura para ajustar la grilla
            ) {
                items(9) { index ->
                    Box(
                        modifier = Modifier
                            .size(100.dp)
                            .padding(4.dp)
                            .onGloballyPositioned { coordinates ->
                                boxCoordinates[index] = coordinates
                            }
                    ) {
                        // Mostrar color de fondo si no tiene imagen de fondo
                        if (boxBackgroundImages[index] != null) {
                            //si la imagen ya tiene su respectiva imagenla ponemos de fondo a la caja
                            Image(
                                painter = painterResource(id = boxBackgroundImages[index]!!),
                                contentDescription = "Imagen de fondo para la caja $index",
                                modifier = Modifier.fillMaxSize()
                            )
                        } else {
                            //si la imagen no tiene su respectiva imagen la caja sera de color gris
                            Box(
                                modifier = Modifier
                                    .fillMaxSize()
                                    .background(Color.LightGray)
                            )
                        }
                    }
                }
            }
        }

        // Imágenes arrastrables
        val imageYOffset = 420.dp // Ajustar según la altura de la columna y la grilla
        for (i in 0 until 9) {
            if (visibleImages[i]) {
                //cargo las 9 imagenes
                Image(
                    painter = painterResource(id = getDrawableForImageCalabaza(i)),
                    contentDescription = "Draggable Image $i",
                    modifier = Modifier
                        .size(imageSize)
                        .offset {
                            IntOffset(offsets[i].first.roundToInt(), offsets[i].second.roundToInt() + imageYOffset.toPx().toInt())
                        }
                        .pointerInput(Unit) {
                            detectDragGestures(
                                onDragEnd = {
                                    var isCorrect = false
                                    boxCoordinates.forEachIndexed { boxIndex, boxCoords ->
                                        if (isImageInsideBox(
                                                offsets[i].first,
                                                offsets[i].second + imageYOffset.toPx(),
                                                imageSizePx,
                                                boxCoords
                                            )
                                        ) {
                                            if (i == boxIndex) {
                                                /*
                                                Toast.makeText(
                                                    context,
                                                    "¡Felicidades! Imagen $i está en la caja ${boxIndex + 1}",
                                                    Toast.LENGTH_SHORT
                                                ).show()*/
                                                visibleImages[i] = false
                                                boxBackgroundImages[boxIndex] = getDrawableForImageCalabaza(i)
                                                correctPlacementCounter++
                                                isCorrect = true

                                                // Verificar si todas las imágenes están colocadas correctamente
                                                if (correctPlacementCounter == 9) {
                                                   // Toast.makeText(context, "¡Juego Terminado!", Toast.LENGTH_LONG).show()
                                                    DateUser.nivelRompeCabezas = 2
                                                    DateUser.vidasRompecabesas = DateUser.vidasRompecabesas-(errorCounter/4).toInt()//por cada 4 errores quito una vida
                                                    navController.navigate(AppScreens.screenGameRompeCabezasNivel2.route)

                                                    if(DateUser.vidasRompecabesas<=0){
                                                        //perdiste
                                                        navController.navigate(AppScreens.screenGameOverRompeCabezas.route)

                                                    }
                                                }
                                            }
                                        }
                                    }
                                    // Incrementar el contador de errores si no se colocó correctamente
                                    if (!isCorrect) {
                                        errorCounter++
                                        //Toast.makeText(context, "Intento incorrecto. Errores: $errorCounter", Toast.LENGTH_SHORT).show()
                                    }
                                }
                            ) { change, dragAmount ->
                                change.consume()
                                offsets[i] = offsets[i].first + dragAmount.x to offsets[i].second + dragAmount.y
                            }
                        }
                )
            }
        }
    }
}

@Composable
fun  ScreenGameRompeCabezasNivel2(navController: NavController) {

    BackHandler{
        navController.navigate("screenGames") {
            popUpTo("screenGameMemorianivel2") { inclusive = true } // Elimina la pantalla actual de la pila
        }
    }

    DateUser.nivelRompeCabezas = 2
    //por cada 4 errores quito una vida

    // Variables para la posición de las imágenes (inicialmente en la parte inferior)
    val density = LocalDensity.current
    val imageSize = 80.dp
    val imageSizePx = with(density) { imageSize.toPx() }

    // Inicializar offsets de las imágenes para que aparezcan debajo de la columna
    val offsets = remember { mutableStateListOf<Pair<Float, Float>>().apply {
        repeat(9) { add(0f to 0f) }
    } }

    // Variables para controlar la visibilidad de las imágenes
    val visibleImages = remember { mutableStateListOf(true, true, true, true, true, true, true, true, true) }

    // Variables para las imágenes de fondo de las cajas
    val boxBackgroundImages = remember { mutableStateListOf<Int?>(null, null, null, null, null, null, null, null, null) }

    // Estados para las coordenadas de las cajas
    val boxCoordinates = remember { mutableStateListOf<LayoutCoordinates?>().apply { repeat(9) { add(null) } } }

    // Contexto para mostrar Toast
    val context = LocalContext.current

    // Contador de imágenes colocadas correctamente
    var correctPlacementCounter by remember { mutableStateOf(0) }

    // Contador de errores
    var errorCounter by remember { mutableStateOf(0) }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(colorResource(com.ecapp.ecapp.R.color.morado_fondo))
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp)
                .background(colorResource(com.ecapp.ecapp.R.color.morado_fondo)),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            // Agregar el encabezado en la parte superior
            Spacer(modifier = Modifier.height(50.dp))
            Text(
                text = "Rompe Cabezas",
                fontSize = Configuraciones.fontSizeTitulos.sp,
                color = Color.White
            )
            Spacer(modifier = Modifier.height(20.dp))
            Text(
                text = "Nivel ${DateUser.nivelRompeCabezas}",
                fontSize = Configuraciones.fontSizeNormal.sp,
                color = Color.White
            )
            Text(
                text = "Vidas ${DateUser.vidasRompecabesas}",
                fontSize = Configuraciones.fontSizeNormal.sp,
                color = Color.White
            )

            Spacer(modifier = Modifier.height(20.dp))

            // Grid de 3x3 para las cajas
            LazyVerticalGrid(
                columns = GridCells.Fixed(3),
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 100.dp)
                    .height(350.dp) // Altura para ajustar la grilla
            ) {
                items(9) { index ->
                    Box(
                        modifier = Modifier
                            .size(100.dp)
                            .padding(4.dp)
                            .onGloballyPositioned { coordinates ->
                                boxCoordinates[index] = coordinates
                            }
                    ) {
                        // Mostrar color de fondo si no tiene imagen de fondo
                        if (boxBackgroundImages[index] != null) {
                            Image(
                                painter = painterResource(id = boxBackgroundImages[index]!!),
                                contentDescription = "Imagen de fondo para la caja $index",
                                modifier = Modifier.fillMaxSize()
                            )
                        } else {
                            Box(
                                modifier = Modifier
                                    .fillMaxSize()
                                    .background(Color.LightGray)
                            )
                        }
                    }
                }
            }
        }

        // Imágenes arrastrables colocadas debajo de la columna
        val imageYOffset = 420.dp // Ajustar según la altura de la columna y la grilla
        for (i in 0 until 9) {
            if (visibleImages[i]) {
                Image(
                    painter = painterResource(id = getDrawableForImage(i)),
                    contentDescription = "Draggable Image $i",
                    modifier = Modifier
                        .size(imageSize)
                        .offset {
                            IntOffset(offsets[i].first.roundToInt(), offsets[i].second.roundToInt() + imageYOffset.toPx().toInt())
                        }
                        .pointerInput(Unit) {
                            detectDragGestures(
                                onDragEnd = {
                                    var isCorrect = false
                                    boxCoordinates.forEachIndexed { boxIndex, boxCoords ->
                                        if (isImageInsideBox(
                                                offsets[i].first,
                                                offsets[i].second + imageYOffset.toPx(),
                                                imageSizePx,
                                                boxCoords
                                            )
                                        ) {
                                            if (i == boxIndex) {
                                                /*
                                                Toast.makeText(
                                                    context,
                                                    "¡Felicidades! Imagen $i está en la caja ${boxIndex + 1}",
                                                    Toast.LENGTH_SHORT
                                                ).show()*/
                                                visibleImages[i] = false
                                                boxBackgroundImages[boxIndex] = getDrawableForImage(i)
                                                correctPlacementCounter++
                                                isCorrect = true

                                                // Verificar si todas las imágenes están colocadas correctamente
                                                if (correctPlacementCounter == 9) {
                                                   // Toast.makeText(context, "¡Juego Terminado!", Toast.LENGTH_LONG).show()
                                                    DateUser.nivelRompeCabezas = 3
                                                    DateUser.vidasRompecabesas = DateUser.vidasRompecabesas-(errorCounter/4).toInt()//por cada 4 errores quito una vida

                                                    navController.navigate(AppScreens.screenGameRompeCabezasNivel3.route)
                                                    if(DateUser.vidasRompecabesas<=0){
                                                        //perdiste
                                                        navController.navigate(AppScreens.screenGameOverRompeCabezas.route)

                                                    }
                                                }
                                            }
                                        }
                                    }
                                    // Incrementar el contador de errores si no se colocó correctamente
                                    if (!isCorrect) {
                                        errorCounter++
                                        //Toast.makeText(context, "Intento incorrecto. Errores: $errorCounter", Toast.LENGTH_SHORT).show()
                                    }
                                }
                            ) { change, dragAmount ->
                                change.consume()
                                offsets[i] = offsets[i].first + dragAmount.x to offsets[i].second + dragAmount.y
                            }
                        }
                )
            }
        }
    }
}
@Composable
fun  ScreenGameRompeCabezasNivel3(navController: NavController) {

    BackHandler{
        navController.navigate("screenGames") {
            popUpTo("screenGameMemorianivel3") { inclusive = true } // Elimina la pantalla actual de la pila
        }
    }


    DateUser.nivelRompeCabezas = 3
    // Variables para la posición de las imágenes (inicialmente en la parte inferior)
    val density = LocalDensity.current
    val imageSize = 80.dp
    val imageSizePx = with(density) { imageSize.toPx() }

    // Inicializar offsets de las imágenes para que aparezcan debajo de la columna
    val offsets = remember { mutableStateListOf<Pair<Float, Float>>().apply {
        repeat(9) { add(0f to 0f) }
    } }

    // Variables para controlar la visibilidad de las imágenes
    val visibleImages = remember { mutableStateListOf(true, true, true, true, true, true, true, true, true) }

    // Variables para las imágenes de fondo de las cajas
    val boxBackgroundImages = remember { mutableStateListOf<Int?>(null, null, null, null, null, null, null, null, null) }

    // Estados para las coordenadas de las cajas
    val boxCoordinates = remember { mutableStateListOf<LayoutCoordinates?>().apply { repeat(9) { add(null) } } }

    // Contexto para mostrar Toast
    val context = LocalContext.current

    // Contador de imágenes colocadas correctamente
    var correctPlacementCounter by remember { mutableStateOf(0) }

    // Contador de errores
    var errorCounter by remember { mutableStateOf(0) }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(colorResource(com.ecapp.ecapp.R.color.morado_fondo))
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp)
                .background(colorResource(com.ecapp.ecapp.R.color.morado_fondo)),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            // Agregar el encabezado en la parte superior
            Spacer(modifier = Modifier.height(50.dp))
            Text(
                text = "Rompe Cabezas",
                fontSize = Configuraciones.fontSizeTitulos.sp,
                color = Color.White
            )
            Spacer(modifier = Modifier.height(20.dp))
            Text(
                text = "Nivel ${DateUser.nivelRompeCabezas}",
                fontSize = Configuraciones.fontSizeNormal.sp,
                color = Color.White
            )
            Text(
                text = "Vidas ${DateUser.vidasRompecabesas}",
                fontSize = Configuraciones.fontSizeNormal.sp,
                color = Color.White
            )

            Spacer(modifier = Modifier.height(20.dp))

            // Grid de 3x3 para las cajas
            LazyVerticalGrid(
                columns = GridCells.Fixed(3),
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 100.dp)
                    .height(350.dp) // Altura para ajustar la grilla
            ) {
                items(9) { index ->
                    Box(
                        modifier = Modifier
                            .size(100.dp)
                            .padding(4.dp)
                            .onGloballyPositioned { coordinates ->
                                boxCoordinates[index] = coordinates
                            }
                    ) {
                        // Mostrar color de fondo si no tiene imagen de fondo
                        if (boxBackgroundImages[index] != null) {
                            Image(
                                painter = painterResource(id = boxBackgroundImages[index]!!),
                                contentDescription = "Imagen de fondo para la caja $index",
                                modifier = Modifier.fillMaxSize()
                            )
                        } else {
                            Box(
                                modifier = Modifier
                                    .fillMaxSize()
                                    .background(Color.LightGray)
                            )
                        }
                    }
                }
            }
        }

        // Imágenes arrastrables colocadas debajo de la columna
        val imageYOffset = 420.dp // Ajustar según la altura de la columna y la grilla
        for (i in 0 until 9) {
            if (visibleImages[i]) {
                Image(
                    painter = painterResource(id = getDrawableForImageCaballo(i)),
                    contentDescription = "Draggable Image $i",
                    modifier = Modifier
                        .size(imageSize)
                        .offset {
                            IntOffset(offsets[i].first.roundToInt(), offsets[i].second.roundToInt() + imageYOffset.toPx().toInt())
                        }
                        .pointerInput(Unit) {
                            detectDragGestures(
                                onDragEnd = {
                                    var isCorrect = false
                                    boxCoordinates.forEachIndexed { boxIndex, boxCoords ->
                                        if (isImageInsideBox(
                                                offsets[i].first,
                                                offsets[i].second + imageYOffset.toPx(),
                                                imageSizePx,
                                                boxCoords
                                            )
                                        ) {
                                            if (i == boxIndex) {
                                                /*
                                                Toast.makeText(
                                                    context,
                                                    "¡Felicidades! Imagen $i está en la caja ${boxIndex + 1}",
                                                    Toast.LENGTH_SHORT
                                                ).show()*/
                                                visibleImages[i] = false
                                                boxBackgroundImages[boxIndex] = getDrawableForImageCaballo(i)
                                                correctPlacementCounter++
                                                isCorrect = true

                                                // Verificar si todas las imágenes están colocadas correctamente
                                                if (correctPlacementCounter == 9) {
                                                   // Toast.makeText(context, "¡Juego Terminado!", Toast.LENGTH_LONG).show()
                                                    DateUser.nivelRompeCabezas = 1
                                                    DateUser.vidasRompecabesas = DateUser.vidasRompecabesas-(errorCounter/4).toInt()//por cada 4 errores quito una vida


                                                    if(DateUser.vidasRompecabesas<=0){
                                                        //perdiste


                                                        navController.navigate(AppScreens.screenGameOverRompeCabezas.route)

                                                    }
                                                    else{
                                                        navController.navigate(AppScreens.screenFelicitacionesGameRompeCabezas.route)
                                                    }
                                                }
                                            }
                                        }
                                    }
                                    // Incrementar el contador de errores si no se colocó correctamente
                                    if (!isCorrect) {
                                        errorCounter++
                                        //Toast.makeText(context, "Intento incorrecto. Errores: $errorCounter", Toast.LENGTH_SHORT).show()
                                    }
                                }
                            ) { change, dragAmount ->
                                change.consume()
                                offsets[i] = offsets[i].first + dragAmount.x to offsets[i].second + dragAmount.y
                            }
                        }
                )
            }
        }
    }
}


// Función para comprobar si la imagen está dentro de la caja
fun isImageInsideBox(
    offsetX: Float,
    offsetY: Float,
    imageSizePx: Float,
    boxCoords: LayoutCoordinates?
): Boolean {
    if (boxCoords == null) return false

    // Obtener posición absoluta de la caja
    val boxTopLeft = boxCoords.positionInRoot()

    // Verificar si la posición de la imagen cae dentro de la caja
    return offsetX >= boxTopLeft.x &&
            offsetY >= boxTopLeft.y &&
            (offsetX + imageSizePx) <= (boxTopLeft.x + boxCoords.size.width) &&
            (offsetY + imageSizePx) <= (boxTopLeft.y + boxCoords.size.height)
}
