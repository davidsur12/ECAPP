package com.ecapp.ecapp

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.annotation.RequiresApi
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.ecapp.ecapp.ui.theme.ECAPPTheme
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import com.ecapp.ecapp.navegation.AppNavegation
import com.ecapp.ecapp.screen.Loginctivity
import com.ecapp.ecapp.cloud.LoginScreenViewModel
import com.google.firebase.Firebase
import com.google.firebase.FirebaseApp
import com.google.firebase.firestore.firestore


class MainActivity : ComponentActivity() {


    var viewModel: LoginScreenViewModel = LoginScreenViewModel();
/*
    public override fun onStart() {
        super.onStart()

        viewModel = LoginScreenViewModel();
        // verifico si tengo alguna  session activa

        val currentUser = viewModel.auth.currentUser
        if (currentUser != null) {
            Log.d("login", "Usuario Inicio session")
        }
        else{
            Log.d("login", "Usuario No inicio session")
        }
    }
    */


            @RequiresApi(Build.VERSION_CODES.O)
            @SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
                FirebaseApp.initializeApp(this)
        enableEdgeToEdge()
        setContent {



            ECAPPTheme {
                Scaffold(modifier = Modifier.fillMaxSize().background(Color(0xba00ca))) {
                    AppNavegation()//voy a la navegacion de pantalla que por defecto es la de actividades y registro

                }
            }


        }
    }

    override fun onPause() {
        super.onPause()

    }


}






