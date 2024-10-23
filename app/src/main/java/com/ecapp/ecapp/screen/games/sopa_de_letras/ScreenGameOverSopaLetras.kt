package com.ecapp.ecapp.screen.games.sopa_de_letras

import android.annotation.SuppressLint
import androidx.activity.compose.BackHandler
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.navigation.NavController

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

    Text("Game Over")
}

