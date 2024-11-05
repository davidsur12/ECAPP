package com.ecapp.ecapp.screen

import android.annotation.SuppressLint
import android.os.Build
import android.util.Log
import androidx.activity.compose.BackHandler
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
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController

import com.ecapp.ecapp.navegation.AppScreens
import com.ecapp.ecapp.utils.Configuraciones
import java.time.format.TextStyle


@RequiresApi(Build.VERSION_CODES.O)
@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun HomeScreenn(navController: NavController) {
    // Utilizamos un Scaffold para proporcionar una estructura básica
    Scaffold(
        modifier = Modifier.fillMaxSize(),
        //backgroundColor = colorResource(com.ecapp.ecapp.R.color.morado_fondo) // Color de fondo de la pantalla
    ) {
        app(navController)
    }
}

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun app(navController: NavController) {

    BackHandler {
        navController.navigate("screenHome") {
            popUpTo("screenHome") { inclusive = true } // Elimina la pantalla actual de la pila
        }
    }

    // Usamos Column para organizar los elementos verticalmente
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(colorResource(com.ecapp.ecapp.R.color.morado_fondo)),  // Ocupa todo el espacio disponible
        verticalArrangement = Arrangement.Top,  // Centra los elementos verticalmente
        horizontalAlignment = Alignment.CenterHorizontally

     ) {
        Text(
            "ECAPP",
            modifier = Modifier.padding(start = 16.dp, top = 90.dp, end = 16.dp, bottom = 20.dp),//modifico la posicion me diante un padding
            style = androidx.compose.ui.text.TextStyle(
                fontSize = 45.sp, // Cambiar tamaño de la fuente
                fontWeight = FontWeight.Bold // Cambiar el peso de la fuente (negrita)
            )
        )


        Image(
            painter = painterResource(id = com.ecapp.ecapp.R.drawable.nutricionista), // Cargamos la imagen
            contentDescription = null, // Descripción nula
            modifier = Modifier.size(150.dp) // Ajustamos el tamaño de la imagen
        )




        Text(
            "Aplicación Estimulación Cognitiva",
            modifier = Modifier.padding(start = 16.dp, top = 20.dp, end = 16.dp, bottom = 20.dp),//modifico la posicion me diante un padding
            color = colorResource(com.ecapp.ecapp.R.color.white),//cambio el colora balnco
            style = androidx.compose.ui.text.TextStyle(
                fontSize = 20.sp, // Cambiar tamaño de la fuente
                fontWeight = FontWeight.Bold, // Cambiar el peso de la fuente (negrita)


            )
        )

        // Línea horizontal
        Canvas(
            modifier = Modifier
                .fillMaxWidth() // Ocupar el ancho completo
                .height(5.dp) // Altura de la línea
                .padding(vertical = 10.dp) // Padding para separación
        ) {
            drawLine(
                color = Color.Blue, // Color de la línea
                start = Offset(0f, 0f), // Punto de inicio
                end = Offset(size.width, 0f), // Punto final
                strokeWidth = 2f // Grosor de la línea
            )
        }

        // Botón de iniciar sesión
        btnRegistro(onClick = {
            navController.navigate(AppScreens.screenLogin.route) // Navegar a la pantalla de inicio de sesión
        }, "Iniciar Sesión")

        // Botón de registrarse
        btnRegistro(onClick = {
             navController.navigate(AppScreens.screenRegisterUser.route) // Navegar a la pantalla de registro

           // navController.navigate(AppScreens.screenGameSecuencia.route)
            //  navController.navigate(AppScreens.screenMemoria.route)
            // navController.navigate(AppScreens.screenGameSopaLetras.route)
            //navController.navigate(AppScreens.screenGameLaberinto.route)
            //navController.navigate(AppScreens.screenRompecabesas.route)
        }, "Registrarse")
    }
}

@Composable
fun btnRegistro(onClick: () -> Unit, nombre: String) {
    OutlinedButton(
        onClick = onClick,
        colors = ButtonDefaults.outlinedButtonColors(
            containerColor = Color.White // Fondo del botón
        ),
        modifier = Modifier
            .padding(vertical = 8.dp) // Añadimos un padding vertical
            .width(300.dp) // Ancho del botón
    ) {
        Text(text = nombre, fontSize = 18.sp)
    }
}

/*
@RequiresApi(Build.VERSION_CODES.O)
@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun HomeScreenn(navController: NavController) {
//es un layout que proporciona una estructura básica para las interfaces de usuario de Compose
    Scaffold {
        app(navController)
    }
}




@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun app(navController: NavController) {

    BackHandler{
        navController.navigate("screenHome") {
            popUpTo("screenHome") { inclusive = true } // Elimina la pantalla actual de la pila
        }
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(colorResource(com.ecapp.ecapp.R.color.morado_fondo)),  // Ocupa todo el espacio disponible
        verticalArrangement = Arrangement.Top,  // Centra los elementos verticalmente
        horizontalAlignment = Alignment.CenterHorizontally   // Centra los elementos verticalmente
    ) {

        Text(
            "ECAPP",
            modifier = Modifier.padding(start = 16.dp, top = 90.dp, end = 16.dp, bottom = 20.dp),//modifico la posicion me diante un padding
            style = androidx.compose.ui.text.TextStyle(
                fontSize = 45.sp, // Cambiar tamaño de la fuente
                fontWeight = FontWeight.Bold // Cambiar el peso de la fuente (negrita)
            )
        )



        Image(
            painter = painterResource(id = com.ecapp.ecapp.R.drawable.nutricionista),//cargo la imagen
            contentDescription = null,//la descripcion es null

        )


        Text(
            "Aplicación Estimulación Cognitiva",
            modifier = Modifier.padding(start = 16.dp, top = 20.dp, end = 16.dp, bottom = 20.dp),//modifico la posicion me diante un padding
            color = colorResource(com.ecapp.ecapp.R.color.white),//cambio el colora balnco
            style = androidx.compose.ui.text.TextStyle(
                fontSize = 20.sp, // Cambiar tamaño de la fuente
                fontWeight = FontWeight.Bold, // Cambiar el peso de la fuente (negrita)


            )
        )
        //Línea horizontal
        Canvas(
            modifier = Modifier
                .fillMaxWidth() // Ocupar el ancho completo
                .height(15.dp) // Altura de la línea
                .padding(5.dp) //un padding para una separacion
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


            try {
                //me dirigo al screen  de inicio de session
                navController.navigate(AppScreens.screenLogin.route)

            } catch (ex: Exception) {
                //Error al cambiar de pantalla
                Log.d("error", "${ex.message}");

            }




        }, "Iniciar Session")
        val context = LocalContext.current
        btnRegistro(onClick = {
            //me dirigo al screen de registro de usuario
            //Configuraciones.reproducirSonidoConCorrutinas(context ,   com.ecapp.ecapp.R.raw.victoria)

             navController.navigate(AppScreens.screenRegisterUser.route)
            //  navController.navigate(AppScreens.screenMemoria.route)
            // navController.navigate(AppScreens.screenGameSopaLetras.route)
            //navController.navigate(AppScreens.screenGameLaberinto.route)
            //navController.navigate(AppScreens.screenRompecabesas.route)

        }, "Registrarse")
    }
}


@Composable
fun btnRegistro(onClick: () -> Unit, nombre: String) {
    OutlinedButton(
        onClick = onClick,
        colors = ButtonDefaults.outlinedButtonColors(
            containerColor = Color.White // Cambiar el fondo a blanco
        ),
        modifier = Modifier.padding(16.dp)
            .width(300.dp)
    ) {
        Text(text = nombre, fontSize = 18.sp)
    }
}

*/