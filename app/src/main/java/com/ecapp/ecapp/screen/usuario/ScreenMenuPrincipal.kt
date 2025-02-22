package com.ecapp.ecapp.screen.usuario

import android.annotation.SuppressLint
import android.os.Build
import android.widget.Toast
import androidx.activity.compose.BackHandler
import androidx.annotation.RequiresApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Divider
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Slider
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
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController

import com.ecapp.ecapp.cloud.FirebaseCloudUser
import com.ecapp.ecapp.navegation.AppScreens
import com.ecapp.ecapp.utils.DateUser
import com.google.firebase.Firebase
import com.google.firebase.auth.auth


@RequiresApi(Build.VERSION_CODES.O)
@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen2(navController: NavController){

    BackHandler{
        navController.navigate("screenUser") {
            popUpTo("screenUser") { inclusive = true } // Elimina la pantalla actual de la pila
        }
    }
/*
    try{
        //si el usuario no tiene los documentos  de los juegos registrados lo creo
        FirebaseCloudUser().crearDocumentosGames()
    }
    catch(ex: Exception){


    }
    */

//columna que ocupa todo el tamaño de la pantalla y alineado de forma centrada con un fondo de color morado



    Scaffold(
        modifier = Modifier
            .fillMaxSize()
            .background(colorResource(com.ecapp.ecapp.R.color.morado_fondo)),
        topBar = {
            TopAppBar(

                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = colorResource(com.ecapp.ecapp.R.color.morado_fondo),
                    titleContentColor = Color.White,
                ),
                title = {
                    Text("ECAPP")
                }
            )
        },
        content ={paddingValues ->
            Column(modifier = Modifier.fillMaxSize()
                .padding(paddingValues)
                .background(
                    colorResource(com.ecapp.ecapp.R.color.morado_fondo)
                )
                .verticalScroll(
                    rememberScrollState(),
                ),horizontalAlignment = Alignment.CenterHorizontally
            ){

                Spacer(modifier = Modifier.height(50.dp))//separacion del top
                //Texto de menu
                Text("MENÚ",
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
                    Text(text =  "Perfil del Usuario" , color = Color.Black, fontSize = 17.sp)

                }
                Spacer(modifier = Modifier.height(20.dp))
                Button(
                    modifier = Modifier
                        .width(270.dp)
                        .height(50.dp),
                    colors = ButtonDefaults.buttonColors(Color.White),
                    onClick = {
                        navController.navigate(AppScreens.screenGames.route)
                    }
                ) {
                    Text(
                        text = "Actividades de Estimulación",
                        color = Color.Black,
                        fontSize = 17.sp,
                        maxLines = 1, // Opción para limitar a una línea
                        overflow = TextOverflow.Ellipsis // Mostrar "..." si el texto es muy largo
                    )
                }



                Spacer(modifier = Modifier.height(20.dp))
                Button(
                    modifier = Modifier.width(270.dp).height(50.dp),
                    colors = ButtonDefaults.buttonColors( Color.White),
                    onClick = {

                        navController.navigate(AppScreens.screenProgresoCognitivo.route)
                    }) {
                    Text(text =  "Progreso Cognitivo" , color = Color.Black,  fontSize = 17.sp )

                }
                Spacer(modifier = Modifier.height(20.dp))
                Button(
                    modifier = Modifier.width(270.dp).height(50.dp),
                    colors = ButtonDefaults.buttonColors( Color.White),
                    onClick = {

                        navController.navigate(AppScreens.screenConfiguraciones.route)
                    }) {
                    Text(text =  "Configuraciones" , color = Color.Black,  fontSize = 17.sp)

                }
                Spacer(modifier = Modifier.height(20.dp))
                /*
                Button(
                    modifier = Modifier.width(200.dp),
                    onClick = {
                        //boton de cierre de session
                        //actualizo la ultima vez que inicio session
                        FirebaseCloudUser().updateFechaConecion(DateUser.correo);
                        Firebase.auth.signOut()
                        navController.navigate(AppScreens.screenHome.route)
                    },
                    colors = ButtonDefaults.buttonColors(
                        containerColor = colorResource(com.ecapp.ecapp.R.color.morado)  ,     // Color de fondo del botón
                        contentColor = colorResource(com.ecapp.ecapp.R.color.white)     // Color del texto o contenido
                           // .background(colorResource(com.ecapp.ecapp.R.color.morado_fondo)),
                    )
                ) {
                    Text(text =  "Cerrar sesión" ,  fontSize = 17.sp)

                }
                */
            }
        }

    )




/*
    val context = LocalContext.current

    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(text = "Bienvenido", fontSize = 24.sp)
        Spacer(modifier = Modifier.height(16.dp))
        Button(onClick = {
            Toast.makeText(context, "¡Botón presionado!", Toast.LENGTH_SHORT).show()
        }) {
            Text(text = "Presionar")
        }
    }

    */


}


