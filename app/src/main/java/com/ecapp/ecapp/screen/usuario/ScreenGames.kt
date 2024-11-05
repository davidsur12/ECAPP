package com.ecapp.ecapp.screen.usuario

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
import androidx.compose.foundation.layout.wrapContentSize
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
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.ecapp.ecapp.navegation.AppScreens
import com.ecapp.ecapp.utils.DateUser
import com.google.firebase.Firebase
import com.google.firebase.auth.auth

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun ScreenGames(navController: NavController) {


        Games(navController)

}
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun Games(navController: NavController) {
    BackHandler {
        navController.navigate("screenUser") {
            popUpTo("screenMemoria") { inclusive = true } // Elimina la pantalla actual de la pila
        }
    }


DateUser.GameSecuenciaNivel=0

    Scaffold(
        modifier = Modifier
            .fillMaxSize()
            .background(colorResource(com.ecapp.ecapp.R.color.morado_fondo)),
        topBar = {
            TopAppBar(

                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = colorResource(com.ecapp.ecapp.R.color.purple_500),
                    titleContentColor = Color.White,
                ),
                title = {
                    Text("Actividades")
                }
            )
        },
        content ={paddingValues ->
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(paddingValues)
                    .background(
                        colorResource(com.ecapp.ecapp.R.color.morado_fondo)
                    )
                    .verticalScroll(
                        rememberScrollState(),
                    ), horizontalAlignment = Alignment.CenterHorizontally
            ) {


                Spacer(modifier = Modifier.height(70.dp))
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(15.dp),
                    contentAlignment = Alignment.Center // Centra el contenido del Box
                ) {
                    Image(
                        painter = painterResource(id = com.ecapp.ecapp.R.drawable.juego_de_memoria),
                        contentDescription = null,
                        modifier = Modifier
                            .width(170.dp)
                            .height(120.dp)
                    )
                }

                Spacer(modifier = Modifier.height(20.dp))
                Button(
                    modifier = Modifier.wrapContentSize(), // Ajusta el tamaño del botón al contenido
                    colors = ButtonDefaults.buttonColors(Color.White),
                    onClick = {
                        navController.navigate(AppScreens.screenMemoria.route)
                    }
                ) {
                    Icon(
                        tint = Color.Black,
                        imageVector = Icons.Default.PlayArrow,
                        contentDescription = "Cancelacion de Objetos",
                        modifier = Modifier.size(18.dp)
                    )
                    Spacer(modifier = Modifier.width(8.dp)) // Espacio entre el icono y el texto
                    Text(text = "Cancelacion de Objetos", color = Color.Black, fontSize = 17.sp)
                }


                Spacer(modifier = Modifier.height(20.dp))

                Button(
                    modifier = Modifier
                        .width(270.dp)
                        .height(50.dp),
                    colors = ButtonDefaults.buttonColors(Color.White),
                    onClick = {

                        navController.navigate(AppScreens.screenGameSecuencia.route)
                    }) {

                    Icon(
                        tint = Color.Black,
                        imageVector = Icons.Default.PlayArrow, // Usa un icono predeterminado
                        contentDescription = "Secuencia",
                        modifier = Modifier.size(24.dp) // Ajusta el tamaño del icono
                    )
                    Text(text = "Secuencia", color = Color.Black , fontSize = 17.sp)

                }

                Spacer(modifier = Modifier.height(20.dp))

                Button(
                    modifier = Modifier
                        .width(270.dp)
                        .height(50.dp),
                    colors = ButtonDefaults.buttonColors(Color.White),
                    onClick = {

                        navController.navigate(AppScreens.screenRompecabesas.route)
                    }) {

                    Icon(
                        tint = Color.Black,
                        imageVector = Icons.Default.PlayArrow, // Usa un icono predeterminado
                        contentDescription = "Rompecabezas",
                        modifier = Modifier.size(24.dp) // Ajusta el tamaño del icono
                    )
                    Text(text = "Rompecabezas", color = Color.Black, fontSize = 17.sp)

                }
                Spacer(modifier = Modifier.height(20.dp))
                Button(
                    modifier = Modifier
                        .width(270.dp)
                        .height(50.dp),
                    colors = ButtonDefaults.buttonColors(Color.White),
                    onClick = {

                        navController.navigate(AppScreens.screenGameSopaLetras.route)
                    }) {

                    Icon(
                        tint = Color.Black,
                        imageVector = Icons.Default.PlayArrow, // Usa un icono predeterminado
                        contentDescription = "Sopa de Letras",
                        modifier = Modifier.size(24.dp) // Ajusta el tamaño del icono
                    )
                    Text(text = "Sopa de Letras", color = Color.Black, fontSize = 17.sp)

                }
                Spacer(modifier = Modifier.height(20.dp))

                Button(
                    modifier = Modifier
                        .width(270.dp)
                        .height(50.dp),
                    colors = ButtonDefaults.buttonColors(Color.White),
                    onClick = {


                        navController.navigate(AppScreens.screenGameLaberinto.route)
                    }) {

                    Icon(
                        tint = Color.Black,
                        imageVector = Icons.Default.PlayArrow, // Usa un icono predeterminado
                        contentDescription = "Laberintos",
                        modifier = Modifier.size(24.dp) // Ajusta el tamaño del icono
                    )
                    Text(text = "Laberintos", color = Color.Black, fontSize = 17.sp)

                }
                Spacer(modifier = Modifier.height(20.dp))


                Spacer(modifier = Modifier.height(20.dp))
/*
                Button(
                    modifier = Modifier.width(200.dp),
                    onClick = {
                        Firebase.auth.signOut()
                        navController.navigate(AppScreens.screenHome.route)
                    }) {
                    Text(text = "Cerrar sesión")

                }
                */


            }
        }

    )


}

