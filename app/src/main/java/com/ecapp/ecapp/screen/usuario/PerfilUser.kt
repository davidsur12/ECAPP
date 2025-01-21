package com.ecapp.ecapp.screen.usuario

import android.annotation.SuppressLint
import androidx.activity.compose.BackHandler
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Person
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.ecapp.ecapp.cloud.FirebaseCloudUser
import com.ecapp.ecapp.utils.DateUser
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun PerfilUser(navController: NavController) {

    InfoUser(navController)

}


@SuppressLint("CoroutineCreationDuringComposition")
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun InfoUser(navController: NavController) {
//regreso a la pantalla de opciones y borro de la pila la pantalla actual
    BackHandler {
        navController.navigate("screenUser") {
            popUpTo("screenUser") { inclusive = true } // Elimina la pantalla actual de la pila
        }
    }


    //variables que almacenan la informacion
    var nombre by remember { mutableStateOf("") }
    var fechaNac by remember { mutableStateOf("") }
    var genero by remember { mutableStateOf("") }
    var direccion by remember { mutableStateOf("") }
    var telefono by remember { mutableStateOf("") }
    var ultimaFechaConectado: String by remember { mutableStateOf("Te acabas de registrar") }

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
        content = { paddingValues ->
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(paddingValues)
                    .background(
                        colorResource(com.ecapp.ecapp.R.color.morado_fondo)
                    )
                    .verticalScroll(
                        rememberScrollState(),
                    ), horizontalAlignment = Alignment.CenterHorizontally
            ) {

                Spacer(modifier = Modifier.height(50.dp))
                Text(
                    "Informacion Personal", textAlign = TextAlign.Center,
                    fontSize = 24.sp, color = Color.White
                )
                Spacer(modifier = Modifier.height(30.dp))

                Icon(
                    imageVector = Icons.Default.Person, // Selecciona el ícono que quieras
                    contentDescription = "Ícono de favorito",
                    tint = Color.White,
                    modifier = Modifier.size(130.dp)
                )
                Spacer(modifier = Modifier.height(20.dp))




                Text(
                    text = " última conexión: " + ultimaFechaConectado,
                    fontSize = 22.sp, // Tamaño del texto
                    textAlign = TextAlign.Center, // Alineación del texto
                    modifier = Modifier.fillMaxSize(), // Modificador para tamaño y disposición
                    color = Color.White
                )

                Spacer(modifier = Modifier.height(20.dp))
                OutlinedTextField(
                    value = nombre,
                    onValueChange = { nombre = it },
                    label = { Text("Nombre Completo") },

                    modifier = Modifier.background(Color.White)
                )

                Spacer(modifier = Modifier.height(50.dp))

                OutlinedTextField(
                    value = fechaNac,
                    onValueChange = { fechaNac = it },
                    label = { Text("Fecha de Nacimiento") },

                    modifier = Modifier.background(Color.White)
                )

                Spacer(modifier = Modifier.height(50.dp))

                OutlinedTextField(
                    value = genero,
                    onValueChange = { genero = it },
                    label = { Text("Genero") },

                    modifier = Modifier.background(Color.White)
                )

                Spacer(modifier = Modifier.height(50.dp))

                OutlinedTextField(
                    value = direccion,
                    onValueChange = { direccion = it },
                    label = { Text("Direccion") },

                    modifier = Modifier.background(Color.White)
                )

                Spacer(modifier = Modifier.height(50.dp))

                OutlinedTextField(
                    value = telefono,
                    onValueChange = { telefono = it },
                    label = { Text("Telefono") },

                    modifier = Modifier.background(Color.White)
                )

                Spacer(modifier = Modifier.height(50.dp))
                //FirebaseCloudUser().getUserById(DateUser.correo)

                CoroutineScope(Dispatchers.IO).launch {

                    /*
                    creamos una corutina una especie de tarea asincrona que cuando obtiene los datos
                    del usuario que se consultaron con el metodo getDataUser de la clase FirebaseCloudUser
                    pasandole como parametro el correo ya que es el identificador asigna los valores a las repestivas
                    variables

                    */
                    val userData = FirebaseCloudUser().getDataUser(DateUser.correo)
                    if (userData != null) {
                        ultimaFechaConectado= userData["FechaConection"].toString()
                        nombre = userData["Nombre"].toString() + " " + userData["Apellido"]
                        fechaNac = userData["FechaNac"].toString()
                        genero = userData["Genero"].toString()
                        direccion = userData["Direccion"].toString()
                        telefono = userData["Telefono"].toString()

                        DateUser.FechaConection=ultimaFechaConectado;
                        DateUser.nombre = nombre
                        DateUser.apellido = userData["Apellido"].toString()
                        DateUser.genero = genero
                        DateUser.fechaNacimiento = fechaNac
                        DateUser.telefono = telefono
                        DateUser.direccion = direccion
                        DateUser.genero = userData["Correo"].toString()

                    } else {
                        println("No se encontró el usuario o ocurrió un error.")
                    }
                }
                // Text("")


            }
        }

    )


}