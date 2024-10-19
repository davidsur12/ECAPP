package com.ecapp.ecapp.screen

import android.annotation.SuppressLint
import android.util.Log
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
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.ecapp.ecapp.btnRegistro
import com.ecapp.ecapp.navegation.AppScreens


@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun HomeScreenn(navController: NavController){

    Scaffold{
        app(navController)
    }
}

@Composable
fun app(navController: NavController){


    Column(
        modifier = Modifier
            .fillMaxSize().background(colorResource(com.ecapp.ecapp.R.color.morado_fondo)),  // Ocupa todo el espacio disponible
        verticalArrangement = Arrangement.Top,  // Centra los elementos verticalmente
        horizontalAlignment = Alignment.CenterHorizontally   // Centra los elementos verticalmente
    ) {

        Text("ECAAP",
            modifier = Modifier.padding(start = 16.dp, top = 90.dp, end = 16.dp, bottom = 20.dp),
            style = androidx.compose.ui.text.TextStyle(
                fontSize = 45.sp, // Cambiar tamaño de la fuente
                fontWeight = FontWeight.Bold // Cambiar el peso de la fuente (negrita)
            ))



        Image(painter = painterResource(id = com.ecapp.ecapp.R.drawable.nutricionista, ),
            contentDescription = null,
            // modifier = Modifier.background(Color.Red).padding(50.dp)
        )


        Text("Aplicación Estimulación Cognitiva" ,
            modifier = Modifier.padding(start = 16.dp, top = 20.dp, end = 16.dp, bottom = 20.dp),
            color = colorResource(com.ecapp.ecapp.R.color.white),
            style = androidx.compose.ui.text.TextStyle(
                fontSize = 18.sp, // Cambiar tamaño de la fuente
                fontWeight = FontWeight.Bold, // Cambiar el peso de la fuente (negrita)


            ))
        // Dibujar una línea horizontal
        Canvas(
            modifier = Modifier
                .fillMaxWidth() // Ocupar el ancho completo
                .height(15.dp) // Altura de la línea
                .padding(5.dp)
        ) {

            drawLine(
                color = Color.Blue, // Color de la línea
                start = androidx.compose.ui.geometry.Offset(0f, 0f), // Punto de inicio (x, y)
                end = androidx.compose.ui.geometry.Offset(size.width, 0f), // Punto final (x, y)
                strokeWidth = 2f // Grosor de la línea
            )
        }


        btnRegistro(onClick = {
            // Acción para el botón simple
            println("Botón Simple presionado")

            try{
                navController.navigate(AppScreens.screenLogin.route)

            }catch(ex: Exception){
                Log.d("error" , "${ex.message}");

            }


            // Crear Intent para iniciar SecondActivity

        }, "Iniciar Session")

        btnRegistro(onClick = {
            //navController.navigate(AppScreens.screenRegisterUser.route)
            navController.navigate(AppScreens.screenMemoria.route)

        }, "Registrarse")
    }
}



