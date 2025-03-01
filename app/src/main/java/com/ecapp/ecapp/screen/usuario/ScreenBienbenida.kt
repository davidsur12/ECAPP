package com.ecapp.ecapp.screen.usuario

import android.annotation.SuppressLint
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
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
import com.ecapp.ecapp.navegation.AppScreens


@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun Bienbenida(navController: NavController){
    Scaffold {

       informacionBienbenida(navController)
    }


}


@Composable
fun informacionBienbenida(navController: NavController){
    Column(modifier = Modifier.fillMaxSize()
        .background(
            colorResource(com.ecapp.ecapp.R.color.morado_fondo)
        )
        .verticalScroll(
            rememberScrollState(),
        ),horizontalAlignment = Alignment.CenterHorizontally){

        Spacer(modifier = Modifier.height(50.dp))

        Text("ECAPP",
            color = Color.White, // Color del texto
            fontSize = 27.sp,   // Tamaño del texto
            fontWeight = FontWeight.Bold,)
        Spacer(modifier = Modifier.height(50.dp))

        Column(modifier = Modifier.background(colorResource(com.ecapp.ecapp.R.color.morado_fondo)).fillMaxWidth(1f),
            horizontalAlignment = Alignment.CenterHorizontally,
        ){
            Spacer(modifier = Modifier.height(15.dp))
            Text("¡Bienvenido a ECAPP!", color = Color.White, // Color del texto
                fontSize = 20.sp,   // Tamaño del texto
                fontWeight = FontWeight.Bold,)

            Spacer(modifier = Modifier.height(15.dp))
            Text("Nos alegra que estés aquí. Juntos, haremos de cada día una oportunidad para ejercitar tu mente, descubrir nuevas habilidades y mantenerte activo.\n" +
                    "Esta es tu herramienta para disfrutar de un bienestar cognitivo en un ambiente amigable\n" +
                    "y adaptado a ti. ¡Empecemos!", textAlign = TextAlign.Center,  color = Color.White)
            Spacer(modifier = Modifier.height(15.dp))

        }

        Button(
            onClick = {navController.navigate(AppScreens.screenUser.route)},
            colors = ButtonDefaults.buttonColors(
                containerColor = colorResource(com.ecapp.ecapp.R.color.morado)  ,     // Color de fondo del botón
                contentColor = colorResource(com.ecapp.ecapp.R.color.white)     // Color del texto o contenido
                // .background(colorResource(com.ecapp.ecapp.R.color.morado_fondo)),
            ),
            modifier = Modifier.padding(16.dp)
        ) {
            Text(text = "Comencemos")
        }
    }




}

