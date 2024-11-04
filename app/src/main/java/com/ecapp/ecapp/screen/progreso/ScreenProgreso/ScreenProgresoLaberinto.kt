package com.ecapp.ecapp.screen.progreso.ScreenProgreso

import android.annotation.SuppressLint
import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.ExperimentalMaterial3Api
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
import com.ecapp.ecapp.utils.Configuraciones

@RequiresApi(Build.VERSION_CODES.O)
@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun ScreenProgresoLaberinto(navController: NavController){


    progresoLaberinto(navController)

}

@RequiresApi(Build.VERSION_CODES.O)
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun progresoLaberinto(navController: NavController){
    val scrollState = rememberScrollState()

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
                    Text("Resumen Semanal")
                }
            )
        },
        content ={paddingValues ->
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .verticalScroll(scrollState) // Habilitar scroll vertical
                    .padding(paddingValues)
                    .background(colorResource(com.ecapp.ecapp.R.color.morado_fondo)),
                verticalArrangement = Arrangement.Top,  // Centra los elementos verticalmente
                horizontalAlignment = Alignment.CenterHorizontally // Ocupa todo el espacio disponible

            ){


                Spacer(modifier = Modifier.height(20.dp))
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(15.dp),
                    contentAlignment = Alignment.Center // Centra el contenido del Box
                ) {
                    Image(
                        painter = painterResource(id = com.ecapp.ecapp.R.drawable.laberinto),
                        contentDescription = null,
                        modifier = Modifier
                            .width(170.dp)
                            .height(120.dp)
                    )
                }

                Spacer(modifier = Modifier.height(20.dp))


                Text("Resumen Semanal Laberinto", color = Color.White,
                    fontSize = Configuraciones.fontSizeNormal.sp, textAlign = TextAlign.Center)

                resumenDatos( "laberinto")
            }
        }

    )

}