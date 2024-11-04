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

@OptIn(ExperimentalMaterial3Api::class)
@RequiresApi(Build.VERSION_CODES.O)
@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun ScreenGraficos(navController: NavController) {
    Scaffold(
        modifier = Modifier
            .fillMaxSize()
            .background(colorResource(com.ecapp.ecapp.R.color.morado_fondo)),
        topBar = {
            TopAppBar(

                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = colorResource(com.ecapp.ecapp.R.color.purple_500),
                    titleContentColor = androidx.compose.ui.graphics.Color.White,
                ),
                title = {
                    Text("Graficos de Evolucion")
                }
            )
        },
        content ={paddingValues ->

            Spacer(modifier = Modifier.height(50.dp))
            LazyColumn(
                modifier = Modifier.fillMaxSize()
                    .padding(paddingValues),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                // Agregar múltiples gráficos a la pantalla
                items(5) { index ->
                    // Puedes cambiar el nombre del juego o usar el mismo para todos
                    when (index) {
                        0 -> resumenDatos2("sopa_letras", "Sopa de letras 1")
                        1 -> resumenDatos2("secuencia", "Secuencia")
                        2 -> resumenDatos2("rompecabezas", "Rompecabezas")
                        3 -> resumenDatos2("cancelación_objetos", "SCancelacion de Objetos")

                    }
                    Spacer(modifier = Modifier.height(16.dp)) // Espacio entre gráficos
                }
            }
        }

    )
}


/*
 LazyColumn(
            modifier = Modifier.fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            // Agregar múltiples gráficos a la pantalla
            items(5) { index ->
                // Puedes cambiar el nombre del juego o usar el mismo para todos
                when (index) {
                    0 -> resumenDatos2("sopa_letras", "Sopa de letras 1")
                    1 -> resumenDatos2("secuencia", "Secuencia")
                    2 -> resumenDatos2("rompecabezas", "Rompecabezas")
                    3 -> resumenDatos2("cancelación_objetos", "SCancelacion de Objetos")

                }
                Spacer(modifier = Modifier.height(16.dp)) // Espacio entre gráficos
            }
        }
*/

/*
fun ScreenGraficos(navController: NavController){
    Scaffold {

        resumenDatos2("sopa_letras", "Sopa de letras")
        Spacer(modifier = Modifier.height(10.dp))
        resumenDatos2("sopa_letras", "Sopa de letras")
    }
}
*/

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

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun resumenDatos2(juegos: String, nombreGame: String) {
    // Crear una lista mutable y reactiva que actualizará la UI automáticamente con elementos de tipo List<Any>
    val editableList = remember { mutableStateListOf<List<Any>>() }

    // Usar LaunchedEffect para ejecutar el bloque solo la primera vez
    LaunchedEffect(juegos) { // Dependiendo de "juegos"
        FirebaseCloudUser().getEvaluaciones({ evaluaciones ->
            if (evaluaciones != null) {
                // Limpiar la lista antes de agregar
                editableList.clear()

                // Transformar cada evaluación en un arreglo que contenga la fecha y calificación
                evaluaciones.forEach { evaluacionMap ->
                    val calificacion = evaluacionMap["calificacion"]?.toString()?.toIntOrNull() ?: 0  // Convertir calificación a Int
                    val fechaCompleta = evaluacionMap["fecha"]?.toString() ?: "Sin fecha"

                    // Separar fecha y hora
                    val (fecha, hora) = fechaCompleta.split("T")
                    val horaSinMilisegundos = hora.split(".")[0]

                    // Formatear la hora a formato de 12 horas (AM/PM)
                    val formatterHora = DateTimeFormatter.ofPattern("hh:mm:ss a")
                    val horaFormateada = LocalTime.parse(horaSinMilisegundos).format(formatterHora)

                    // Agregar a la lista como [fecha + horaFormateada, calificación]
                    editableList.add(listOf("Nivel: $calificacion ", calificacion))
                }
            } else {
                // Manejo de error si no hay evaluaciones
                println("No se encontraron evaluaciones o hubo un error.")
            }
        }, juegos)
    }

    // Mostrar el contenido de la lista en un Text
    if (editableList.isNotEmpty()) {
        // Crear una lista de pares de fecha y calificación para el gráfico
        val data = editableList.take(5).map { Pair(it[0] as String, it[1] as Int) }

        Column(
            modifier = Modifier
                .padding(16.dp) // Agregar padding al Column
                .border(BorderStroke(2.dp, androidx.compose.ui.graphics.Color.Black)) // Agregar borde
                .padding(16.dp)
                .background(colorResource(com.ecapp.ecapp.R.color.morado_fondo)),  // Padding interno dentro del Column
            verticalArrangement = Arrangement.Top,  // Centra los elementos verticalmente
            horizontalAlignment = Alignment.CenterHorizontally

        ) {
            // Generar el gráfico con los datos
            Spacer(modifier = Modifier.height(10.dp))
            Text("$nombreGame", color = androidx.compose.ui.graphics.Color.White, fontSize = Configuraciones.fontSizeNormal.sp )
            Spacer(modifier = Modifier.height(20.dp))
            BarChart(data = data)

            // Mostrar los datos de las primeras 5 evaluaciones
            data.forEach { (fecha, calificacion) ->
                Text("Calificación: $calificacion", color = androidx.compose.ui.graphics.Color.White,fontSize = Configuraciones.fontSizeNormal.sp  )
            }
        }
    } else {
        Text("No se encontraron evaluaciones o hubo un error.", color = androidx.compose.ui.graphics.Color.White, textAlign = TextAlign.Center)
    }
    Spacer(modifier = Modifier.height(12.dp))
}


@Composable
fun graficos() {
    val evaluaciones = listOf(
        "2024-10-20" to 5,
        "2024-10-21" to 7,
        "2024-10-22" to 4,
        "2024-10-23" to 6,
        "2024-10-24" to 8
    )

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp)
    ) {
        Text(
            text = "Evolución de Calificaciones",
            //style = MaterialTheme.typography.h6,
            modifier = Modifier.align(Alignment.CenterHorizontally)
        )
        Spacer(modifier = Modifier.height(16.dp))
        BarChart(data = evaluaciones)
    }
}