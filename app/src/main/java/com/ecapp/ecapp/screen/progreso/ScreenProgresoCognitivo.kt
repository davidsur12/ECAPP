package com.ecapp.ecapp.screen.progreso

import android.annotation.SuppressLint
import androidx.activity.compose.BackHandler
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.ecapp.ecapp.navegation.AppScreens
import com.ecapp.ecapp.screen.games.rompecabezas.gameRompeCabezasNivel1

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun ScreenProgreso(navController: NavController){
    Scaffold {

        // GameRompecabesas(navController)
        progreso(navController)
    }
}
@Composable
fun progreso(navController: NavController){
    BackHandler {
        navController.navigate("screenUser") {
            popUpTo("ScreenProgresoCognitivo") { inclusive = true } // Elimina la pantalla actual de la pila
        }
    }

Column(modifier = Modifier.fillMaxSize()
    .background(
        colorResource(com.ecapp.ecapp.R.color.morado_fondo)
    )
    .verticalScroll(
        rememberScrollState(),
    ),horizontalAlignment = Alignment.CenterHorizontally
) {

    Spacer(modifier = Modifier.height(50.dp))
    Text("Progreso Cognitivo", style = androidx.compose.ui.text.TextStyle(
        fontSize = 40.sp, // Cambiar tamaño de la fuente
        fontWeight = FontWeight.Bold // Cambiar el peso de la fuente (negrita)
    ))
    Spacer(modifier = Modifier.height(20.dp))

    Button(
        modifier = Modifier.width(270.dp).height(50.dp),
        colors = ButtonDefaults.buttonColors( Color.White),
        onClick = {

            navController.navigate(AppScreens.screenProgresoGamesOPciones.route)
        }) {
        Text(text =  "Resumen Diario" , color = Color.Black,)

    }
    Spacer(modifier = Modifier.height(20.dp))

    Button(
        modifier = Modifier.width(270.dp).height(50.dp),
        colors = ButtonDefaults.buttonColors( Color.White),
        onClick = {

            navController.navigate(AppScreens.screenProgresoGamesGraficos.route)
        }) {
        Text(text =  "Graficos de Evolucion" , color = Color.Black,)

    }
    Spacer(modifier = Modifier.height(20.dp))

    Button(
        modifier = Modifier.width(270.dp).height(50.dp),
        colors = ButtonDefaults.buttonColors( Color.White),
        onClick = {

            navController.navigate(AppScreens.screenPerfilUser.route)
        }) {
        Text(text =  "Recomendaciones Personalisadas" , color = Color.Black,)

    }


}

}