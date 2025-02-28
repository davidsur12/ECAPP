package com.ecapp.ecapp

import android.annotation.SuppressLint

import android.os.Build
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.annotation.RequiresApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Scaffold
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import com.ecapp.ecapp.ui.theme.ECAPPTheme
import com.ecapp.ecapp.navegation.AppNavegation
import com.ecapp.ecapp.cloud.LoginScreenViewModel
import com.google.firebase.FirebaseApp


class MainActivity : ComponentActivity() {


    var viewModel: LoginScreenViewModel = LoginScreenViewModel();


    @RequiresApi(Build.VERSION_CODES.O)
    @SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        FirebaseApp.initializeApp(this)
        enableEdgeToEdge()
        setContent {


            ECAPPTheme {
                Scaffold(
                    modifier = Modifier
                        .fillMaxSize()
                        .background(Color(0xba00ca))
                ) {
                    AppNavegation()//voy a la navegacion de pantalla que por defecto es la de actividades y registro

                }
            }


        }
    }




}






