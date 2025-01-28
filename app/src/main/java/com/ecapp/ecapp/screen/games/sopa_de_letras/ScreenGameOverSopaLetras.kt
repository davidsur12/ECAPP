package com.ecapp.ecapp.screen.games.sopa_de_letras

import android.annotation.SuppressLint
import androidx.activity.compose.BackHandler
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.ecapp.ecapp.navegation.AppScreens

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun ScreenGameOverSopaLetras(navController: NavController){
    Scaffold {


        //WordSearchGame()
        //gameSopaLetras(navController)
        gameOverSopaLetras(navController)
    }
}

@Composable
fun gameOverSopaLetras(navController: NavController){
    BackHandler{
        navController.navigate("screenGames") {
            popUpTo("ScreenGameOverSopaLetras") { inclusive = true } // Elimina la pantalla actual de la pila
        }
    }



    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center,
        modifier = Modifier.fillMaxSize()
            .background(colorResource(com.ecapp.ecapp.R.color.morado_fondo))
    ) {

        // Mostrar u ocultar la columna según el estado

        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            Text(text = "Oops", fontSize = 25.sp, color = Color.White)
            Text(text = "Fallaste Vuelve a Intentarlo", fontSize = 30.sp, // Ocupa el ancho completo
                textAlign = TextAlign.Center, color = Color.White)

            Image(
                painter = painterResource(id = com.ecapp.ecapp.R.drawable.triste),
                contentDescription = "Descripción de la imagen",
                modifier = Modifier.size(100.dp) // Cambia el tamaño de la imagen si lo deseas
            )

            Button(onClick = {navController.navigate(AppScreens.screenGameSopaLetras.route)},
                colors = ButtonDefaults.buttonColors(
                    containerColor = colorResource(com.ecapp.ecapp.R.color.morado)  ,     // Color de fondo del botón
                    contentColor = colorResource(com.ecapp.ecapp.R.color.white)     // Color del texto o contenido
                    // .background(colorResource(com.ecapp.ecapp.R.color.morado_fondo)),
                ), modifier = Modifier.width(175.dp)){ Text("Volver a jugar") }
            Button(onClick = {navController.navigate(AppScreens.screenUser.route)},
                colors = ButtonDefaults.buttonColors(
                    containerColor = colorResource(com.ecapp.ecapp.R.color.morado)  ,     // Color de fondo del botón
                    contentColor = colorResource(com.ecapp.ecapp.R.color.white)     // Color del texto o contenido
                    // .background(colorResource(com.ecapp.ecapp.R.color.morado_fondo)),
                ), modifier = Modifier.width(175.dp)){ Text("Inicio") }

        }

    }
}

