package com.ecapp.ecapp.screen

import android.annotation.SuppressLint
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
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.ecapp.ecapp.cloud.FirebaseCloudUser
import com.ecapp.ecapp.navegation.AppScreens
import com.google.firebase.Firebase
import com.google.firebase.auth.auth


@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun HomeScreenUser(navController: NavController){
    Scaffold {

        HomeScreen2(navController)
    }
}

@Composable
fun HomeScreen2(navController: NavController){
    try{
        FirebaseCloudUser().crearDocumentosGames()
    }
    catch(ex: Exception){


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
        Text("MENU",
            color = Color.White, // Color del texto
            fontSize = 27.sp,   // Tamaño del texto
            fontWeight = FontWeight.Bold,)

        Spacer(modifier = Modifier.height(50.dp))



        Spacer(modifier = Modifier.height(20.dp))
        Button(
            modifier = Modifier.width(270.dp).height(50.dp),
            colors = ButtonDefaults.buttonColors( Color.White),
            onClick = {

                navController.navigate(AppScreens.screenPerfilUser.route)
            }) {
            Text(text =  "Perfil del Usuario" , color = Color.Black,)

        }
        Spacer(modifier = Modifier.height(20.dp))
        Button(
            modifier = Modifier.width(270.dp).height(50.dp),
            colors = ButtonDefaults.buttonColors( Color.White),
            onClick = {

                navController.navigate(AppScreens.screenGames.route)
            }) {
            Text(text =  "Actividades de Estimulacion Cognitiva" , color = Color.Black,textAlign = TextAlign.Center)

        }
        Spacer(modifier = Modifier.height(20.dp))
        Button(
            modifier = Modifier.width(270.dp).height(50.dp),
            colors = ButtonDefaults.buttonColors( Color.White),
            onClick = {

                navController.navigate(AppScreens.screenProgresoCognitivo.route)
            }) {
            Text(text =  "Progreso Cognitivo" , color = Color.Black)

        }
        Spacer(modifier = Modifier.height(20.dp))
        Button(
            modifier = Modifier.width(270.dp).height(50.dp),
            colors = ButtonDefaults.buttonColors( Color.White),
            onClick = {

                //navController.navigate(AppScreens.screenHome.route)
            }) {
            Text(text =  "Configuraciones" , color = Color.Black)

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



    }}
