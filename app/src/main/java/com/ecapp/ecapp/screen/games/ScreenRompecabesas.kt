package com.ecapp.ecapp.screen.games

import android.annotation.SuppressLint
import androidx.compose.material3.Scaffold

import androidx.navigation.NavController
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.material3.Text
import androidx.compose.runtime.*




import android.content.ClipData
import android.content.ClipDescription
import android.widget.Toast

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.scaleIn
import androidx.compose.animation.scaleOut
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.draganddrop.dragAndDropSource
import androidx.compose.foundation.draganddrop.dragAndDropTarget
import androidx.compose.foundation.gestures.detectDragGestures
import androidx.compose.foundation.gestures.detectTapGestures
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue

import androidx.compose.ui.draganddrop.DragAndDropEvent
import androidx.compose.ui.draganddrop.DragAndDropTarget
import androidx.compose.ui.draganddrop.DragAndDropTransferData
import androidx.compose.ui.draganddrop.mimeTypes
import androidx.compose.ui.draganddrop.toAndroidDragEvent

import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.layout.LayoutCoordinates
import androidx.compose.ui.layout.onGloballyPositioned
import androidx.compose.ui.layout.positionInRoot
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.IntOffset
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import kotlin.math.absoluteValue
import kotlin.math.roundToInt
import kotlin.random.Random


import androidx.compose.ui.unit.dp


import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color

import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.unit.dp
import kotlin.math.roundToInt


@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun ScreenRompecabesas(navController: NavController){
    Scaffold {

       // GameRompecabesas(navController)
        DraggableImagesWithGrid()
    }
}
@Composable
fun DraggableImagesWithGrid() {
    // Variables para la posición de las imágenes
    val offsets = remember { mutableStateListOf<Pair<Float, Float>>().apply {
        repeat(9) { add(0f to 0f) }
    } }

    // Variables para controlar la visibilidad de las imágenes
    val visibleImages = remember { mutableStateListOf(true, true, true, true, true, true, true, true, true) }

    // Variables para las imágenes de fondo de las cajas
    val boxBackgroundImages = remember { mutableStateListOf<Int?>(null, null, null, null, null, null, null, null, null) }

    // Tamaño de la caja y la imagen
    val boxSize = 100.dp
    val imageSize = 80.dp

    // Convertir `imageSize` a píxeles
    val imageSizePx = with(LocalDensity.current) { imageSize.toPx() }

    // Contexto para mostrar Toast
    val context = LocalContext.current

    // Estados para las coordenadas de las cajas
    val boxCoordinates = remember { mutableStateListOf<LayoutCoordinates?>().apply {
        repeat(9) { add(null) }
    } }

    // Lista de colores para las cajas
    val boxColors = listOf(
        Color.Red, Color.Green, Color.Blue,
        Color.Yellow, Color.Cyan, Color.Magenta,
        Color.LightGray, Color.DarkGray, Color.Blue
    )

    // Contador de imágenes colocadas correctamente
    var correctPlacementCounter by remember { mutableStateOf(0) }

    // Contador de errores
    var errorCounter by remember { mutableStateOf(0) }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.LightGray)
    ) {
        // Grid de 3x3 para las cajas
        LazyVerticalGrid(
            columns = GridCells.Fixed(3),
            modifier = Modifier.fillMaxSize()
        ) {
            items(9) { index ->
                Box(
                    modifier = Modifier
                        .size(boxSize)
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
                                .background(boxColors[index])
                        )
                    }
                }
            }
        }

        // Imágenes arrastrables
        for (i in 0 until 9) {
            if (visibleImages[i]) {  // Solo mostrar imágenes visibles
                Image(
                    painter = painterResource(id = getDrawableForImage(i)), // Reemplaza esto con tus imágenes
                    contentDescription = "Draggable Image $i",
                    modifier = Modifier
                        .size(imageSize)
                        .offset { IntOffset(offsets[i].first.roundToInt(), offsets[i].second.roundToInt()) }
                        .pointerInput(Unit) {
                            detectDragGestures(
                                onDragEnd = {
                                    var isCorrect = false
                                    boxCoordinates.forEachIndexed { boxIndex, boxCoords ->
                                        // Comprobar si la imagen está dentro de la caja correspondiente
                                        if (isImageInsideBox(
                                                offsets[i].first,
                                                offsets[i].second,
                                                imageSizePx,
                                                boxCoords
                                            )) {
                                            if (i == boxIndex) {
                                                // Colocación correcta
                                                Toast.makeText(context, "Imagen $i está en la caja ${boxIndex + 1}", Toast.LENGTH_SHORT).show()
                                                visibleImages[i] = false
                                                boxBackgroundImages[boxIndex] = getDrawableForImage(i)
                                                correctPlacementCounter++
                                                isCorrect = true

                                                // Verificar si todas las imágenes están colocadas correctamente
                                                if (correctPlacementCounter == 9) {
                                                    Toast.makeText(context, "¡Juego Terminado! Errores: $errorCounter", Toast.LENGTH_LONG).show()
                                                }
                                            }
                                        }
                                    }
                                    // Incrementar el contador de errores si no se colocó correctamente
                                    if (!isCorrect) {
                                        errorCounter++
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

// Simular imágenes. Aquí debes poner las imágenes reales
fun getDrawableForImage(index: Int): Int {
    return when (index) {
        0 -> com.ecapp.ecapp.R.drawable.gato1
        1 -> com.ecapp.ecapp.R.drawable.gato1
        2 -> com.ecapp.ecapp.R.drawable.gato1
        3 -> com.ecapp.ecapp.R.drawable.gato1
        4 -> com.ecapp.ecapp.R.drawable.gato1
        5 -> com.ecapp.ecapp.R.drawable.gato1
        6 -> com.ecapp.ecapp.R.drawable.gato1
        7 -> com.ecapp.ecapp.R.drawable.gato1
        8 -> com.ecapp.ecapp.R.drawable.gato1
        else -> com.ecapp.ecapp.R.drawable.uno
    }
}


/*
@Composable
fun DraggableImagesWithGrid() {
    // Variables para la posición de las imágenes
    val offsets = remember { mutableStateListOf<Pair<Float, Float>>().apply {
        repeat(9) { add(0f to 0f) }
    } }

    // Variables para controlar la visibilidad de las imágenes
    val visibleImages = remember { mutableStateListOf(true, true, true, true, true, true, true, true, true) }

    // Variables para las imágenes de fondo de las cajas
    val boxBackgroundImages = remember { mutableStateListOf<Int?>(null, null, null, null, null, null, null, null, null) }

    // Tamaño de la caja y la imagen
    val boxSize = 100.dp
    val imageSize = 80.dp

    // Convertir `imageSize` a píxeles
    val imageSizePx = with(LocalDensity.current) { imageSize.toPx() }

    // Contexto para mostrar Toast
    val context = LocalContext.current

    // Estados para las coordenadas de las cajas
    val boxCoordinates = remember { mutableStateListOf<LayoutCoordinates?>().apply {
        repeat(9) { add(null) }
    } }

    // Lista de colores para las cajas
    val boxColors = listOf(
        Color.Red, Color.Green, Color.Blue,
        Color.Yellow, Color.Cyan, Color.Magenta,
        Color.LightGray, Color.DarkGray, Color.Blue
    )

    // Contador de imágenes colocadas correctamente
    var correctPlacementCounter by remember { mutableStateOf(0) }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.LightGray)
    ) {
        // Grid de 3x3 para las cajas
        LazyVerticalGrid(
            columns = GridCells.Fixed(3),
            modifier = Modifier.fillMaxSize()
        ) {
            items(9) { index ->
                Box(
                    modifier = Modifier
                        .size(boxSize)
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
                                .background(boxColors[index])
                        )
                    }
                }
            }
        }

        // Imágenes arrastrables
        for (i in 0 until 9) {
            if (visibleImages[i]) {  // Solo mostrar imágenes visibles
                Image(
                    painter = painterResource(id = getDrawableForImage(i)), // Reemplaza esto con tus imágenes
                    contentDescription = "Draggable Image $i",
                    modifier = Modifier
                        .size(imageSize)
                        .offset { IntOffset(offsets[i].first.roundToInt(), offsets[i].second.roundToInt()) }
                        .pointerInput(Unit) {
                            detectDragGestures(
                                onDragEnd = {
                                    boxCoordinates.forEachIndexed { boxIndex, boxCoords ->
                                        // Comprobar si la imagen está dentro de la caja correspondiente
                                        if (isImageInsideBox(
                                                offsets[i].first,
                                                offsets[i].second,
                                                imageSizePx,
                                                boxCoords
                                            ) && i == boxIndex) {
                                            // Mostrar Toast si la imagen está dentro de la caja correcta
                                            Toast.makeText(context, "Imagen $i está en la caja ${boxIndex + 1}", Toast.LENGTH_SHORT).show()

                                            // Ocultar la imagen
                                            visibleImages[i] = false

                                            // Cambiar el fondo de la caja a la imagen
                                            boxBackgroundImages[boxIndex] = getDrawableForImage(i)

                                            // Incrementar el contador de colocaciones correctas
                                            correctPlacementCounter++

                                            // Verificar si todas las imágenes están colocadas correctamente
                                            if (correctPlacementCounter == 9) {
                                                Toast.makeText(context, "¡Juego Terminado!", Toast.LENGTH_LONG).show()
                                            }
                                        }
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

// Simular imágenes. Aquí debes poner las imágenes reales
fun getDrawableForImage(index: Int): Int {
    return when (index) {
        0 -> com.ecapp.ecapp.R.drawable.gato1
        1 -> com.ecapp.ecapp.R.drawable.gato1
        2 -> com.ecapp.ecapp.R.drawable.gato1
        3 -> com.ecapp.ecapp.R.drawable.gato1
        4 -> com.ecapp.ecapp.R.drawable.gato1
        5 -> com.ecapp.ecapp.R.drawable.gato1
        6 -> com.ecapp.ecapp.R.drawable.gato1
        7 -> com.ecapp.ecapp.R.drawable.gato1
        8 -> com.ecapp.ecapp.R.drawable.gato1
        else -> com.ecapp.ecapp.R.drawable.uno
    }
}

*/