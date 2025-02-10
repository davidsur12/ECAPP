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
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
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
import androidx.compose.ui.text.style.TextAlign
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
    Scaffold(
        modifier = Modifier.fillMaxSize(),
        //backgroundColor = colorResource(com.ecapp.ecapp.R.color.morado_fondo)
    ) {
        app(navController)
    }
}

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun app(navController: NavController) {

    BackHandler {
        navController.navigate("screenHome") {
            popUpTo("screenHome") { inclusive = true }
        }
    }

    // Añadimos verticalScroll para que se pueda desplazar en pantallas pequeñas
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(colorResource(com.ecapp.ecapp.R.color.morado_fondo))
            .verticalScroll(rememberScrollState()),  // Permite desplazamiento vertical
        verticalArrangement = Arrangement.Top,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            "ECAPP",
            modifier = Modifier.padding(start = 16.dp, top = 90.dp, end = 16.dp, bottom = 20.dp),
            style = androidx.compose.ui.text.TextStyle(
                fontSize = 45.sp,
                fontWeight = FontWeight.Bold,
                color = Color.White
            )
        )

        Image(
            painter = painterResource(id = com.ecapp.ecapp.R.drawable.nutricionista),
            contentDescription = null,
            modifier = Modifier.size(150.dp)
        )

        Text(
            "Aplicación de Estimulación Cognitiva para Personas Adultas Mayores",
            modifier = Modifier.padding(start = 16.dp, top = 20.dp, end = 16.dp, bottom = 20.dp),
            color = colorResource(com.ecapp.ecapp.R.color.white),
            style = androidx.compose.ui.text.TextStyle(
                textAlign = TextAlign.Center,
                fontSize = 20.sp,
                fontWeight = FontWeight.Bold,
            )
        )

        Canvas(
            modifier = Modifier
                .fillMaxWidth()
                .height(5.dp)
                .padding(vertical = 10.dp)
        ) {
            drawLine(
                color = Color.Blue,
                start = Offset(0f, 0f),
                end = Offset(size.width, 0f),
                strokeWidth = 2f
            )
        }

        btnRegistro(onClick = {
            navController.navigate(AppScreens.screenLogin.route)
        }, "Iniciar Sesión")

        btnRegistro(onClick = {
            navController.navigate(AppScreens.screenRegisterUser.route)
        }, "Registrarse")
    }
}

@Composable
fun btnRegistro(onClick: () -> Unit, nombre: String) {
    OutlinedButton(
        onClick = onClick,
        colors = ButtonDefaults.outlinedButtonColors(
            containerColor = Color.White
        ),
        modifier = Modifier
            .padding(vertical = 8.dp)
            .width(300.dp)
    ) {
        Text(text = nombre, fontSize = 18.sp)
    }
}


