package com.ecapp.ecapp.screen

import android.annotation.SuppressLint
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
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
import androidx.compose.ui.tooling.preview.Preview
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
fun PerfilUser(navController: NavController){

Scaffold { InfoUser() }

}


@SuppressLint("CoroutineCreationDuringComposition")
@Composable
fun InfoUser(){
    var nombre by remember { mutableStateOf("") }
    var fechaNac by remember { mutableStateOf("") }
    var genero by remember { mutableStateOf("") }
    var direccion by remember { mutableStateOf("") }
    var telefono by remember { mutableStateOf("") }
    Column(
        modifier = Modifier.fillMaxSize()
            .background(
                colorResource(com.ecapp.ecapp.R.color.morado_fondo)
            )
            .verticalScroll(
                rememberScrollState(),
            ),horizontalAlignment = Alignment.CenterHorizontally
    ){

        Spacer(modifier = Modifier.height(50.dp))
        Text("Informacion Personal",textAlign = TextAlign.Center ,
            fontSize = 24.sp, color = Color.White)
        Spacer(modifier = Modifier.height(50.dp))
        OutlinedTextField(
            value = nombre,
            onValueChange = { nombre = it },
            label = { Text("Nombre Completo") },

            modifier =  Modifier.background(Color.White,)
        )
        Spacer(modifier = Modifier.height(50.dp))

        OutlinedTextField(
            value = fechaNac,
            onValueChange = { fechaNac = it },
            label = { Text("Fecha de Nacimiento") },

            modifier =  Modifier.background(Color.White,)
        )

        Spacer(modifier = Modifier.height(50.dp))

        OutlinedTextField(
            value = genero,
            onValueChange = { genero = it },
            label = { Text("Genero") },

            modifier =  Modifier.background(Color.White,)
        )

        Spacer(modifier = Modifier.height(50.dp))

        OutlinedTextField(
            value = direccion,
            onValueChange = { direccion = it },
            label = { Text("Direccion") },

            modifier =  Modifier.background(Color.White,)
        )

        Spacer(modifier = Modifier.height(50.dp))

        OutlinedTextField(
            value = telefono,
            onValueChange = { telefono = it },
            label = { Text("Telefono") },

            modifier =  Modifier.background(Color.White,)
        )

        Spacer(modifier = Modifier.height(50.dp))
        //FirebaseCloudUser().getUserById(DateUser.correo)
        CoroutineScope(Dispatchers.IO).launch {
            val userData = FirebaseCloudUser().getUserById(DateUser.correo)
            if (userData != null) {
                nombre = userData["Nombre"].toString() + " " + userData["Apellido"]
                fechaNac = userData["FechaNac"].toString()
                genero = userData["Genero"].toString()
                direccion = userData["Direccion"].toString()
                telefono = userData["Telefono"].toString()

                DateUser.nombre = nombre
                DateUser.apellido=userData["Apellido"].toString()
                DateUser.genero=genero
                DateUser.fechaNacimiento=fechaNac
                DateUser.telefono=telefono
                DateUser.direccion=direccion
                DateUser.genero=userData["Correo"].toString()

            } else {
                println("No se encontró el usuario o ocurrió un error.")
            }
        }
       // Text("")



    }

}