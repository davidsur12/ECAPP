package com.ecapp.ecapp.screen.games.sopa_de_letras

import android.annotation.SuppressLint
import android.os.Build
import android.util.Log
import android.widget.Toast
import androidx.activity.compose.BackHandler
import androidx.annotation.RequiresApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Star
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.ecapp.ecapp.cloud.FirebaseCloudUser
import com.ecapp.ecapp.navegation.AppScreens
import com.ecapp.ecapp.utils.Configuraciones
import com.ecapp.ecapp.utils.DateUser
import java.time.LocalDateTime


@RequiresApi(Build.VERSION_CODES.O)
@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun ScreenFelicitacionesSopaLetras(navController: NavController){
    val context = LocalContext.current
    Scaffold {

        if(Configuraciones.ActivateSonido){
            Configuraciones.reproducirSonidoConCorrutinas(context ,   com.ecapp.ecapp.R.raw.victoria)
        }
        FelicitacionesSecuencia(navController)

    }
}



@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun FelicitacionesSecuencia(navController: NavController){

    BackHandler{
        navController.navigate("screenGames") {
            popUpTo("ScreenFelicitacionesSopaLetras") { inclusive = true } // Elimina la pantalla actual de la pila
        }
    }

    BackHandler{
        navController.navigate("screenGames") {
            popUpTo("screenFelicitacionesMemoria") { inclusive = true } // Elimina la pantalla actual de la pila
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
            Text(text = "Bien Echo", fontSize = 25.sp, color = Color.White)

            Text(text = "Felicitaciones", fontSize = 45.sp, color = Color.White)
            Text(text = "Calificacion", fontSize = 25.sp, color = Color.White)
            if( DateUser.vidasSopaLetras==5){
                //0 errores la calificacion sera de 3 estrella de oro

                Row(
                    modifier = Modifier
                        .padding(16.dp)
                        .fillMaxWidth(),
                    horizontalArrangement = Arrangement.Center
                ) {
                    repeat(3) { // Cambia el número 5 para mostrar más o menos estrellas
                        Icon(
                            imageVector = Icons.Default.Star,
                            contentDescription = "Star Icon",
                            tint = Color.Yellow,
                            modifier = Modifier
                                .size(50.dp)
                                .padding(4.dp)
                        )
                    }
                }
            }
            if(DateUser.vidasSopaLetras>=2 &&  DateUser.vidasSopaLetras<5){
                // calificacion sera de 2 estrellas de plata
                Row(
                    modifier = Modifier
                        .padding(50.dp)
                        .fillMaxWidth(),
                    horizontalArrangement = Arrangement.Center
                ) {
                    repeat(2) { // Cambia el número 5 para mostrar más o menos estrellas
                        Icon(
                            imageVector = Icons.Default.Star,
                            contentDescription = "Star Icon",
                            tint = Color.Gray,
                            modifier = Modifier
                                .size(50.dp)
                                .padding(4.dp)
                        )
                    }
                }


            }
            if(DateUser.vidasSopaLetras==1){
                //sin vids la calificacion sera de 1 estrellas de bronce

                Row(
                    modifier = Modifier
                        .padding(16.dp)
                        .fillMaxWidth(),
                    horizontalArrangement = Arrangement.Center
                ) {
                    repeat(1) { // Cambia el número 5 para mostrar más o menos estrellas
                        Icon(
                            imageVector = Icons.Default.Star,
                            contentDescription = "Star Icon",
                            tint = Color.Red,
                            modifier = Modifier
                                .size(50.dp)
                                .padding(4.dp)
                        )
                    }
                }
            }
            val context = LocalContext.current
           DateUser.calificacionGameSopaLetras = DateUser.vidasSopaLetras
            Log.d("informacionVidas", "Vidas: ${DateUser.vidasSopaLetras} calificacion: ${DateUser.calificacionGameSopaLetras}")

            FirebaseCloudUser().agregarCalificacion(LocalDateTime.now().toString(), DateUser.calificacionGameSopaLetras, "sopa_letras")
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