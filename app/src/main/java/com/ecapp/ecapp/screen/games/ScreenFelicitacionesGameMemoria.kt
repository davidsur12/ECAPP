package com.ecapp.ecapp.screen.games

import android.annotation.SuppressLint
import androidx.activity.compose.BackHandler
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
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.ecapp.ecapp.navegation.AppScreens
import com.ecapp.ecapp.utils.DateUser


@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun ScreenFelicitacionesMemoria(navController: NavController){
    Scaffold {
        ScreenFelicitacionesM(navController)

    }
}



@Composable
fun ScreenFelicitacionesM(navController: NavController){

    BackHandler{
        navController.navigate("screenGames") {
            popUpTo("screenFelicitacionesMemoria") { inclusive = true } // Elimina la pantalla actual de la pila
        }
    }

    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center,
        modifier = Modifier.fillMaxSize()
    ) {

        // Mostrar u ocultar la columna según el estado

            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                Text(text = "Bien Echo", fontSize = 25.sp)
                Text(text = "Felicitaciones", fontSize = 45.sp)
                if( DateUser.erroresGameMemoria==0){
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
                if(DateUser.erroresGameMemoria==1){
                    //1 error la calificacion sera de 2 estrellas de plata
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
                if(DateUser.erroresGameMemoria==5){
                    //sin vids la calificacion sera de 1 estrellas de bronce

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
                                tint = Color.Red,
                                modifier = Modifier
                                    .size(32.dp)
                                    .padding(4.dp)
                            )
                        }
                    }
                }


                Button(onClick = {navController.navigate(AppScreens.screenMemoria.route)} , modifier = Modifier.width(175.dp)){ Text("Volver a jugar") }
                Button(onClick = {navController.navigate(AppScreens.screenUser.route)}, modifier = Modifier.width(175.dp)){ Text("Inicio") }

            }

    }
}