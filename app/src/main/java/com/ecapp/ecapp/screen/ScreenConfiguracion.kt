package com.ecapp.ecapp.screen

import android.annotation.SuppressLint
import android.widget.Toast
import androidx.activity.compose.BackHandler
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
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
import androidx.compose.material3.Divider
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Slider
import androidx.compose.material3.SliderDefaults
import androidx.compose.material3.Switch
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
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
import com.ecapp.ecapp.navegation.AppScreens
import com.ecapp.ecapp.utils.Configuraciones

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun ScreenConfiguracion(navController: NavController){


    SettingsScreen()

}

@Composable
fun  configuracion(navController: NavController){


    BackHandler{
        navController.navigate("screenUser") {
            popUpTo("screenUser") { inclusive = true } // Elimina la pantalla actual de la pila
        }
    }

    Column(modifier = Modifier.fillMaxSize()
        .background(
            colorResource(com.ecapp.ecapp.R.color.morado_fondo)
        )
        .verticalScroll(
            rememberScrollState(),
        ),horizontalAlignment = Alignment.CenterHorizontally
    ){


        Spacer(modifier = Modifier.height(50.dp))//separacion del top
        //Texto de menu
        Text("Configuracion",
            color = Color.White, // Color del texto
            fontSize = 27.sp,   // Tamaño del texto
            fontWeight = FontWeight.Bold,)

        //Espacio
        Spacer(modifier = Modifier.height(70.dp))


        //Menu de botones que me permite navegar entre las diferentes opciones
        Button(
            modifier = Modifier.width(270.dp).height(50.dp),
            colors = ButtonDefaults.buttonColors( Color.White),
            onClick = {

                navController.navigate(AppScreens.screenPerfilUser.route)
            }) {
            Text(text =  "Activar Sonido" , color = Color.Black,)

        }
        Spacer(modifier = Modifier.height(20.dp))

        Button(
            modifier = Modifier.width(270.dp).height(50.dp),
            colors = ButtonDefaults.buttonColors( Color.White),
            onClick = {

                navController.navigate(AppScreens.screenPerfilUser.route)
            }) {
            Text(text =  "Ajustar tamaño de letra" , color = Color.Black,)

        }
    }


}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SettingsScreen() {
    val context = LocalContext.current
    // Estado para el sonido (activado/desactivado)
    val isSoundEnabled = remember { mutableStateOf(true) }

    // Estado para el tamaño de fuente
    val fontSize = remember { mutableStateOf(16f) } // Tamaño inicial de fuente en sp

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
                    Text("ECAPP")
                }
            )
        },
        content =  { paddingValues ->

            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .verticalScroll(rememberScrollState())
                    .padding(paddingValues)
                    .background(colorResource(com.ecapp.ecapp.R.color.morado_fondo))
            )

            {

                Spacer(modifier = Modifier.height(50.dp))

                Box(
                    modifier = Modifier
                        .fillMaxWidth(),
                    contentAlignment = Alignment.Center // Centra el contenido del Box
                ) {
                    Image(
                        painter = painterResource(id = com.ecapp.ecapp.R.drawable.ajuste),
                        contentDescription = null
                    )
                }



                // Opción de sonido
                Row (
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(vertical = 8.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(
                        text = "Sonido",
                        fontSize = 30.sp,
                        modifier = Modifier.weight(1f),
                        color = Color.White
                    )
                    Switch(
                        checked = isSoundEnabled.value,
                        onCheckedChange = { isSoundEnabled.value = it
                            Configuraciones.ActivateSonido = isSoundEnabled.value
                        }
                    )
                }

                Divider(modifier = Modifier.padding(vertical = 8.dp))

                // Opción de tamaño de fuente
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(vertical = 14.dp)
                ) {
                    Text(
                        text = "Tamaño de fuente: ${fontSize.value.toInt()}",
                        fontSize = 30.sp,
                        color = Color.White
                    )
                    Slider(
                        value = fontSize.value,
                        onValueChange = { fontSize.value = it
                            //  Toast.makeText(context, "${fontSize.value}", Toast.LENGTH_SHORT).show()
                            Configuraciones.fontSizeTitulos = fontSize.value
                            Configuraciones.fontSizeNormal = fontSize.value - 6

                        },
                        colors = SliderDefaults.colors(
                            thumbColor = Color.Red, // Color del botón deslizante
                            activeTrackColor = Color.White, // Color de la pista activa (donde está el valor actual)
                            inactiveTrackColor = Color.Gray // Color de la pista inactiva
                        ),
                        valueRange = 12f..28f, // Rango de tamaño de fuente en sp
                        steps = 6 // Opcional: número de pasos intermedios entre el valor mínimo y máximo

                    )
                }
            }
        }

    )

}
