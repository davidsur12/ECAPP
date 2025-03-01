package com.ecapp.ecapp.screen.progreso.graficos

import android.annotation.SuppressLint
import android.content.Context
import android.graphics.Color
import android.os.Build
import android.util.Log
import androidx.annotation.RequiresApi
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Star
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.nativeCanvas
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.viewinterop.AndroidView
import androidx.navigation.NavController
import com.ecapp.ecapp.cloud.FirebaseCloudUser
import com.ecapp.ecapp.screen.progreso.progresoGamesOpciones
import com.ecapp.ecapp.utils.Configuraciones
import java.time.LocalTime
import java.time.format.DateTimeFormatter
import androidx.compose.material3.Text
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.*

@OptIn(ExperimentalMaterial3Api::class)
@RequiresApi(Build.VERSION_CODES.O)
@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun ScreenGraficos(navController: NavController) {
    // Variable para verificar si hay datos en al menos un gráfico
    val hasData = remember { mutableStateOf(false) }

    val contador = remember { mutableStateOf(0) }

    Scaffold(
        modifier = Modifier
            .fillMaxSize()
            .background(colorResource(com.ecapp.ecapp.R.color.morado_fondo)),
        topBar = {
            TopAppBar(
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = colorResource(com.ecapp.ecapp.R.color.morado_fondo),
                    titleContentColor = androidx.compose.ui.graphics.Color.White,
                ),
                title = { Text("Gráficos de Evolución") }
            )
        },
        content = { paddingValues ->
            LazyColumn(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(paddingValues),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                // Agregar múltiples gráficos a la pantalla
                items(5) { index ->
                    when (index) {
                        0 -> resumenDatos2("sopa_letras", "Sopa de letras 1", hasData, contador)
                        1 -> resumenDatos2("secuencia", "Secuencia", hasData, contador)
                        2 -> resumenDatos2("rompecabezas", "Rompecabezas", hasData, contador)
                        3 -> resumenDatos2("cancelación_objetos", "Cancelación de Objetos", hasData, contador)
                        4 -> resumenDatos2("laberinto", "laberinto", hasData, contador)
                    }
                    Spacer(modifier = Modifier.height(16.dp))
                }

                // Si no hay datos en ningún gráfico, mostrar un mensaje
                item {
                    if (!hasData.value) {
                        /*
                        if(contador >= 4){
                            println("total contador $contador")
                            contador =0;
                            Column(
                                modifier = Modifier.fillMaxSize(),
                                verticalArrangement = Arrangement.Center,
                                horizontalAlignment = Alignment.CenterHorizontally
                            ) {
                                Text(text = "Texto centrado en el Column")
                            }
                        }
                        */
                        /*
                        Box(
                            modifier = Modifier
                                .fillMaxSize()
                                .padding(16.dp),
                            contentAlignment = Alignment.Center
                        ) {
                            Text(
                                text = "No se encontraron gráficos disponibles",
                                color = androidx.compose.ui.graphics.Color.White,
                                textAlign = TextAlign.Center,
                                fontSize = 16.sp
                            )
                        }
                        */

                        Column(
                            modifier = Modifier.fillMaxSize(),
                            verticalArrangement = Arrangement.Center,
                            horizontalAlignment = Alignment.CenterHorizontally
                        ) {
                            Text(text = "No se encontraron resultados")
                        }
                    }
                }
            }
        }
    )
}

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun resumenDatos2(juegos: String, nombreGame: String, hasData: MutableState<Boolean>,  contador: MutableState<Int>) {
    val editableList = remember { mutableStateListOf<List<Any>>() }
    var contador by remember { mutableStateOf(0) }
    LaunchedEffect(juegos) {
        FirebaseCloudUser().getEvaluaciones({ evaluaciones ->
            if (evaluaciones != null && evaluaciones.isNotEmpty()) {
                editableList.clear()
                evaluaciones.forEach { evaluacionMap ->
                    val calificacion = evaluacionMap["calificacion"]?.toString()?.toIntOrNull() ?: 0
                    val fechaCompleta = evaluacionMap["fecha"]?.toString() ?: "Sin fecha"
                    val (fecha, hora) = fechaCompleta.split("T")
                    val horaSinMilisegundos = hora.split(".")[0]
                    val formatterHora = DateTimeFormatter.ofPattern("hh:mm:ss a")
                    val horaFormateada = LocalTime.parse(horaSinMilisegundos).format(formatterHora)
                    editableList.add(listOf("Nivel: $calificacion", calificacion))
                }
                hasData.value = true // Marcar que hay datos disponibles
            }
        }, juegos)
    }

    if (editableList.isNotEmpty()) {
        val data = editableList.take(5).map { Pair(it[0] as String, it[1] as Int) }

        Column(
            modifier = Modifier
                .padding(16.dp)
                .border(BorderStroke(2.dp, androidx.compose.ui.graphics.Color.Black))
                .padding(16.dp)
                .background(colorResource(com.ecapp.ecapp.R.color.morado_fondo)),
            verticalArrangement = Arrangement.Top,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text("$nombreGame", color = androidx.compose.ui.graphics.Color.White, fontSize = 18.sp)
            Spacer(modifier = Modifier.height(20.dp))
            BarChart(data = data)
            data.forEach { (_, calificacion) ->
                Text("Calificación: $calificacion", color = androidx.compose.ui.graphics.Color.White, fontSize = 16.sp)
            }
        }
    } else {
        contador++
        // Si no hay datos en este gráfico, no hacemos nada especial
        println("total contador $contador");



    }
}



@Composable
fun BarChart(data: List<Pair<String, Int>>) {
    val maxDataValue = if (data.isNotEmpty()) data.maxOf { it.second }.toFloat() else 1f

    Canvas(modifier = Modifier
        .fillMaxWidth()
        .height(200.dp)
        .padding(16.dp)
    ) {
        // Tomar solo los primeros 5 elementos de la lista
        val limitedData = data.take(5)

        val barWidth = size.width / (limitedData.size * 2)
        val spacing = barWidth / 2

        limitedData.forEachIndexed { index, (fecha, calificacion) ->
            // Determinar el color y tamaño de la barra según la calificación
            val (barColor, barHeightFactor) = when {
                calificacion == 1 -> androidx.compose.ui.graphics.Color.Red to 0.2f // Barra pequeña roja
                calificacion in 2..4 -> androidx.compose.ui.graphics.Color.Green to 0.6f // Barra mediana verde
                calificacion == 5 -> androidx.compose.ui.graphics.Color.Yellow to 1.0f // Barra grande amarilla
                else -> androidx.compose.ui.graphics.Color.Gray to 0.4f // Barra predeterminada para otros valores
            }

            // Calcular la altura de la barra en función del factor de tamaño
            val barHeight = (barHeightFactor * size.height).coerceAtMost(size.height)

            // Coordenadas para la barra
            val startX = index * (barWidth + spacing * 2) + spacing
            val endY = size.height - barHeight

            drawRect(
                color = barColor,
                topLeft = Offset(startX, endY),
                size = Size(barWidth, barHeight)
            )

            // Dibuja las etiquetas debajo de cada barra
            drawContext.canvas.nativeCanvas.apply {
                drawText(
                    fecha,
                    startX + (barWidth / 2),
                    size.height + 20f,
                    android.graphics.Paint().apply {
                        textAlign = android.graphics.Paint.Align.CENTER
                        textSize = 24f
                        color = android.graphics.Color.BLACK
                    }
                )
            }
        }
    }
}

