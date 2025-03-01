package com.ecapp.ecapp.screen.progreso

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
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
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
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.ecapp.ecapp.navegation.AppScreens

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ScreenProgreso(navController: NavController){
  progreso(navController)
}
@OptIn(ExperimentalMaterial3Api::class)
@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun progreso(navController: NavController){
    BackHandler {
        navController.navigate("screenUser") {
            popUpTo("ScreenProgresoCognitivo") { inclusive = true } // Elimina la pantalla actual de la pila
        }
    }


    Scaffold( modifier = Modifier
        .fillMaxSize()
        .background(colorResource(com.ecapp.ecapp.R.color.morado_fondo)),
        topBar = {
            TopAppBar(

                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = colorResource(com.ecapp.ecapp.R.color.morado_fondo),
                    titleContentColor = Color.White,
                ),
                title = {
                    Text("Progreso Cognitivo")
                }
            )
        },  content = { paddingValues ->
            Column(modifier = Modifier.fillMaxSize()
                .padding(paddingValues)
                .background(
                    colorResource(com.ecapp.ecapp.R.color.morado_fondo)
                )
                .verticalScroll(
                    rememberScrollState(),
                ),horizontalAlignment = Alignment.CenterHorizontally
            ) {

                Spacer(modifier = Modifier.height(20.dp))
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(15.dp),
                    contentAlignment = Alignment.Center // Centra el contenido del Box
                ) {
                    Image(
                        painter = painterResource(id = com.ecapp.ecapp.R.drawable.diario),
                        contentDescription = null
                    )
                }
                Spacer(modifier = Modifier.height(20.dp))

                Button(
                    modifier = Modifier.width(270.dp).height(50.dp),
                    colors = ButtonDefaults.buttonColors( Color.White),
                    onClick = {

                        navController.navigate(AppScreens.screenProgresoGamesOPciones.route)
                    }) {
                    Text(text =  "Resumen Diario" , color = Color.Black, fontSize = 18.sp)

                }
                Spacer(modifier = Modifier.height(20.dp))

                Button(
                    modifier = Modifier.width(270.dp).height(50.dp),
                    colors = ButtonDefaults.buttonColors( Color.White),
                    onClick = {

                        navController.navigate(AppScreens.screenProgresoGamesGraficos.route)
                    }) {
                    Text(text =  "Gráficos de evolución" , color = Color.Black, fontSize = 18.sp)

                }
                Spacer(modifier = Modifier.height(20.dp))




            }
        }

    )




}