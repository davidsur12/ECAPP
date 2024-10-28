package com.ecapp.ecapp.screen.progreso

import android.annotation.SuppressLint
import android.os.Build
import androidx.activity.compose.BackHandler
import androidx.annotation.RequiresApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
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
import androidx.compose.material.icons.filled.PlayArrow
import androidx.compose.material.icons.filled.Star
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ElevatedButton
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.ecapp.ecapp.cloud.FirebaseCloudUser
import com.ecapp.ecapp.navegation.AppScreens
import com.ecapp.ecapp.utils.DateUser
import com.google.firebase.Firebase
import com.google.firebase.auth.auth
import java.time.LocalDateTime
import java.time.LocalTime
import java.time.format.DateTimeFormatter

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun ScreenProgresoGamesOpciones(navController: NavController){
    Scaffold {

        progresoGamesOpciones(navController)
    }
}

@Composable
fun progresoGamesOpciones(navController: NavController){
    BackHandler{
        navController.navigate("screenUser") {
            popUpTo("screenMemoria") { inclusive = true } // Elimina la pantalla actual de la pila
        }
    }


    Column(modifier = Modifier.fillMaxSize()
        .background(
            colorResource(com.ecapp.ecapp.R.color.morado_fondo)
        )
        .verticalScroll(
            rememberScrollState(),
        ),horizontalAlignment = Alignment.CenterHorizontally
    ){


        Spacer(modifier = Modifier.height(50.dp))



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
            Text(text =  "Cancelacion de Objetos" , color = Color.Black,)

        }

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
                contentDescription = "Cancelacion de Objetos",
                modifier = Modifier.size(24.dp) // Ajusta el tamaño del icono
            )
            Text(text =  "Secuencia" , color = Color.Black,)

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
            Text(text =  "Rompecabezas" , color = Color.Black,)

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
            Text(text =  "Sopa de Letras" , color = Color.Black,)

        }
        Spacer(modifier = Modifier.height(20.dp))

        Button(
            modifier = Modifier.width(270.dp).height(50.dp),
            colors = ButtonDefaults.buttonColors( Color.White),
            onClick = {

                navController.navigate(AppScreens.screenPerfilUser.route)
            }) {

            Icon(
                tint = Color.Black,
                imageVector = Icons.Default.PlayArrow, // Usa un icono predeterminado
                contentDescription = "Laberintos",
                modifier = Modifier.size(24.dp) // Ajusta el tamaño del icono
            )
            Text(text =  "Laberintos" , color = Color.Black,)

        }
        Spacer(modifier = Modifier.height(20.dp))


        Spacer(modifier = Modifier.height(20.dp))





    }
}