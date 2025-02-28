package com.ecapp.ecapp.screen.progreso

import android.annotation.SuppressLint
import androidx.activity.compose.BackHandler
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
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
import androidx.compose.material.icons.filled.PlayArrow
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
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
import com.ecapp.ecapp.navegation.AppScreens
import com.ecapp.ecapp.utils.Configuraciones


@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun ScreenProgresoGamesOpciones(navController: NavController){


        progresoGamesOpciones(navController)

}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun progresoGamesOpciones(navController: NavController){
    BackHandler {
        navController.navigate("ScreenProgresoCognitivo") {
            popUpTo("ScreenProgresoGamesOpciones") { inclusive = true } // Elimina la pantalla actual de la pila
        }
    }
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
                    Text("Resumen Diario")
                }
            )
        },
        content ={ paddingValues ->
            Column(modifier = Modifier.fillMaxSize()
                .padding(paddingValues)
                .background(
                    colorResource(com.ecapp.ecapp.R.color.morado_fondo)
                )
                .verticalScroll(
                    rememberScrollState(),
                ),horizontalAlignment = Alignment.CenterHorizontally
            )
            {


                Spacer(modifier = Modifier.height(50.dp))

                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(15.dp),
                    contentAlignment = Alignment.Center // Centra el contenido del Box
                ) {
                    Image(
                        painter = painterResource(id = com.ecapp.ecapp.R.drawable.rutina),
                        contentDescription = null,
                        modifier = Modifier
                            .width(170.dp)
                            .height(120.dp)
                    )
                }
Text("Por Favor selecciona la actividad de la cual deseas conocer tus Resumenes" ,
    fontSize = Configuraciones.fontSizeNormal.sp
, textAlign = TextAlign.Center
    ,  style = androidx.compose.ui.text.TextStyle(fontWeight = FontWeight.Bold, color = Color.White),
    )
                Spacer(modifier = Modifier.height(20.dp))

                Button(
                    modifier = Modifier.width(270.dp).height(50.dp),
                    colors = ButtonDefaults.buttonColors( Color.White),
                    onClick = {

                        navController.navigate(AppScreens.screenProgresoGamesCancelacionObjetos.route)
                    }) {

                    Icon(
                        tint = Color.Black,
                        imageVector = Icons.Default.PlayArrow, // Usa un icono predeterminado
                        contentDescription = "Juegos de Memoria",
                        modifier = Modifier.size(24.dp) // Ajusta el tamaño del icono
                    )
                    Text(text =  "Cancelacion de Objetos" , color = Color.Black, fontSize = 17.sp)

                }

                Spacer(modifier = Modifier.height(20.dp))

                Button(
                    modifier = Modifier.width(270.dp).height(50.dp),
                    colors = ButtonDefaults.buttonColors( Color.White),
                    onClick = {

                        navController.navigate(AppScreens.screenProgresoGamesSecuencia.route)
                    }) {

                    Icon(
                        tint = Color.Black,
                        imageVector = Icons.Default.PlayArrow, // Usa un icono predeterminado
                        contentDescription = "Cancelacion de Objetos",
                        modifier = Modifier.size(24.dp) // Ajusta el tamaño del icono
                    )
                    Text(text =  "Secuencia" , color = Color.Black, fontSize = 17.sp)

                }

                Spacer(modifier = Modifier.height(20.dp))

                Button(
                    modifier = Modifier.width(270.dp).height(50.dp),
                    colors = ButtonDefaults.buttonColors( Color.White),
                    onClick = {

                        navController.navigate(AppScreens.screenProgresoGamesRompeCabezas.route)
                    }) {

                    Icon(
                        tint = Color.Black,
                        imageVector = Icons.Default.PlayArrow, // Usa un icono predeterminado
                        contentDescription = "Rompecabezas",
                        modifier = Modifier.size(24.dp) // Ajusta el tamaño del icono
                    )
                    Text(text =  "Rompecabezas" , color = Color.Black, fontSize = 17.sp)

                }
                Spacer(modifier = Modifier.height(20.dp))
                Button(
                    modifier = Modifier.width(270.dp).height(50.dp),
                    colors = ButtonDefaults.buttonColors( Color.White),
                    onClick = {

                        navController.navigate(AppScreens.screenProgresoGamesSopaLetras.route)
                    }) {

                    Icon(
                        tint = Color.Black,
                        imageVector = Icons.Default.PlayArrow, // Usa un icono predeterminado
                        contentDescription = "Sopa de Letras",
                        modifier = Modifier.size(24.dp) // Ajusta el tamaño del icono
                    )
                    Text(text =  "Sopa de Letras" , color = Color.Black, fontSize = 17.sp)

                }
                Spacer(modifier = Modifier.height(20.dp))

                Button(
                    modifier = Modifier.width(270.dp).height(50.dp),
                    colors = ButtonDefaults.buttonColors( Color.White),
                    onClick = {

                        navController.navigate(AppScreens.screenProgresoLaberinto.route)
                    }) {

                    Icon(
                        tint = Color.Black,
                        imageVector = Icons.Default.PlayArrow, // Usa un icono predeterminado
                        contentDescription = "Laberintos",
                        modifier = Modifier.size(24.dp) // Ajusta el tamaño del icono
                    )
                    Text(text =  "Laberintos" , color = Color.Black, fontSize = 17.sp)

                }
                Spacer(modifier = Modifier.height(20.dp))


                Spacer(modifier = Modifier.height(20.dp))





            }

        }

    )
    BackHandler{
        navController.navigate("screenUser") {
            popUpTo("screenMemoria") { inclusive = true } // Elimina la pantalla actual de la pila
        }
    }



}