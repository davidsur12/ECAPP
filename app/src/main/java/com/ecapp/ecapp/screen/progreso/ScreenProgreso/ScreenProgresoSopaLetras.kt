package com.ecapp.ecapp.screen.progreso.ScreenProgreso

import android.annotation.SuppressLint
import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.BorderStroke
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
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Star
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
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
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.ecapp.ecapp.cloud.FirebaseCloudUser
import com.ecapp.ecapp.utils.Configuraciones

import java.time.LocalTime
import java.time.format.DateTimeFormatter

@RequiresApi(Build.VERSION_CODES.O)
@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun ScreenProgresoSopaLetras(navController: NavController){
    Scaffold {

        progresoSopaLetras(navController)
    }
}

@RequiresApi(Build.VERSION_CODES.O)
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun progresoSopaLetras(navController: NavController){
    val scrollState = rememberScrollState()

    Scaffold(
        modifier = Modifier
            .fillMaxSize()
            .background(colorResource(com.ecapp.ecapp.R.color.morado_fondo)),
        topBar = {
            TopAppBar(

                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = colorResource(com.ecapp.ecapp.R.color.morado_fondo),
                    titleContentColor = Color.White,
                ),
                title = {
                    Text("Resumen Semanal")
                }
            )
        },
        content ={paddingValues ->
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .verticalScroll(scrollState) // Habilitar scroll vertical
                    .padding(paddingValues)
                    .background(colorResource(com.ecapp.ecapp.R.color.morado_fondo)),
                verticalArrangement = Arrangement.Top,  // Centra los elementos verticalmente
                horizontalAlignment = Alignment.CenterHorizontally // Ocupa todo el espacio disponible

            ){

                Spacer(modifier = Modifier.height(50.dp))
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(15.dp),
                    contentAlignment = Alignment.Center // Centra el contenido del Box
                ) {
                    Image(
                        painter = painterResource(id = com.ecapp.ecapp.R.drawable.cubitos),
                        contentDescription = null,
                        modifier = Modifier
                            .width(170.dp)
                            .height(120.dp)
                    )
                }



                Text("Resumen Semanal Sopa de Letras", style = androidx.compose.ui.text.TextStyle(fontWeight = FontWeight.Bold, color = Color.White),
                    fontSize = Configuraciones.fontSizeNormal.sp,)

                resumenDatos( "sopa_letras")
            }
        }

    )

}

@RequiresApi(Build.VERSION_CODES.O)

@Composable
fun resumenDatos(juegos: String) {
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
                    editableList.add(listOf("$fecha $horaFormateada", calificacion))
                }
            } else {
               // println("No se encontraron evaluaciones o hubo un error.")

            }
        }, juegos)
    }

    // Mostrar el contenido de la lista en un Text
    if (editableList.isNotEmpty()) {
        Column(
            modifier = Modifier
                .padding(16.dp) // Agregar padding al Column
                .border(BorderStroke(2.dp, Color.Black)) // Agregar borde
                .padding(16.dp) ,  verticalArrangement = Arrangement.Top,  // Centra los elementos verticalmente
            horizontalAlignment = Alignment.CenterHorizontally
            // Padding interno dentro del Column
        )  {

            editableList.forEach { item ->
                val fecha = item[0] as String
                val calificacion = item[1] as Int
                if( calificacion == 5){
                    //0 errores la calificacion sera de 3 estrella de oro

                    Row(
                        modifier = Modifier
                            .padding(2.dp)
                            .fillMaxWidth(),
                        horizontalArrangement = Arrangement.Center
                    ) {
                        repeat(3) { // Cambia el número 5 para mostrar más o menos estrellas
                            Icon(
                                imageVector = Icons.Default.Star,
                                contentDescription = "Star Icon",
                                tint = Color.Yellow,
                                modifier = Modifier
                                    .size(30.dp)
                                    .padding(4.dp)
                            )
                        }
                    }
                }
                if(calificacion >= 2 &&  calificacion < 5){
                    // calificacion sera de 2 estrellas de plata
                    Row(
                        modifier = Modifier
                            .padding(2.dp)
                            .fillMaxWidth(),
                        horizontalArrangement = Arrangement.Center
                    ) {
                        repeat(2) { // Cambia el número 5 para mostrar más o menos estrellas
                            Icon(
                                imageVector = Icons.Default.Star,
                                contentDescription = "Star Icon",
                                tint = Color.White,
                                modifier = Modifier
                                    .size(30.dp)
                                    .padding(4.dp)
                            )
                        }
                    }


                }
                if(calificacion <=1){
                    //sin vids la calificacion sera de 1 estrellas de bronce

                    Row(
                        modifier = Modifier
                            .padding(2.dp)
                            .fillMaxWidth(),
                        horizontalArrangement = Arrangement.Center
                    ) {
                        repeat(1) { // Cambia el número 5 para mostrar más o menos estrellas
                            Icon(
                                imageVector = Icons.Default.Star,
                                contentDescription = "Star Icon",
                                tint = Color.Red,
                                modifier = Modifier
                                    .size(30.dp)
                                    .padding(4.dp)
                            )
                        }
                    }
                }

                Text("Fecha: $fecha,  ", color = Color.White)
            }
        }
    } else {
        Text("No se encontraron evaluaciones.", style = androidx.compose.ui.text.TextStyle(fontWeight = FontWeight.Bold),
            fontSize = Configuraciones.fontSizeNormal.sp,)
    }
    Spacer(modifier = Modifier.height(12.dp))
}



