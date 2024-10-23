package com.ecapp.ecapp.screen.games.rompecabezas

import android.annotation.SuppressLint
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.navigation.NavController
import com.ecapp.ecapp.screen.app

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun ScreenGameRompeCabezas(navController: NavController){

    Scaffold{
        app(navController)
    }
}

@Composable
fun gameRompeCabezas(navController: NavController){
    Text("RompeCabezas")
}