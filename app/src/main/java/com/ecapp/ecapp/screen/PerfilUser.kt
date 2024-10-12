package com.ecapp.ecapp.screen

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.OutlinedTextField
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
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController

@Composable
fun PerfilUser(navController: NavController){



}

@Preview
@Composable
fun pr(){
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

    }

}