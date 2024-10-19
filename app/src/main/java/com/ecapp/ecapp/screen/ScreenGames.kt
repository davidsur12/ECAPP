package com.ecapp.ecapp.screen

import android.annotation.SuppressLint
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
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
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.ecapp.ecapp.navegation.AppScreens
import com.google.firebase.Firebase
import com.google.firebase.auth.auth

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun ScreenGames(navController: NavController){
    Scaffold {

        Games(navController)
    }
}

@Composable
fun Games(navController: NavController){


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

                navController.navigate(AppScreens.screenMemoria.route)
            }) {

            Icon(
                tint = Color.Black,
                imageVector = Icons.Default.PlayArrow, // Usa un icono predeterminado
                contentDescription = "Juegos de Memoria",
                modifier = Modifier.size(24.dp) // Ajusta el tamaño del icono
            )
            Text(text =  "Juegos de Memoria" , color = Color.Black,)

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
                contentDescription = "Cancelacion de Objetos",
                modifier = Modifier.size(24.dp) // Ajusta el tamaño del icono
            )
            Text(text =  "Cancelacion de Objetos" , color = Color.Black,)

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

                navController.navigate(AppScreens.screenPerfilUser.route)
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

        Button(
            modifier = Modifier.width(270.dp).height(50.dp),
            colors = ButtonDefaults.buttonColors( Color.White),
            onClick = {

                navController.navigate(AppScreens.screenRompecabesas.route)
            }) {

            Icon(
                tint = Color.Black,
                imageVector = Icons.Default.PlayArrow, // Usa un icono predeterminado
                contentDescription = "Ejercicios de Secuencia",
                modifier = Modifier.size(24.dp) // Ajusta el tamaño del icono
            )
            Text(text =  "Rompecabezas" , color = Color.Black,)

        }
        Spacer(modifier = Modifier.height(20.dp))

        Button(
            modifier = Modifier.width(200.dp),
            onClick = {
                Firebase.auth.signOut()
                navController.navigate(AppScreens.screenHome.route)
            }) {
            Text(text =  "Cerrar sesión" )

        }



    }
}