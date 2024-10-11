package com.ecapp.ecapp.screen

import android.annotation.SuppressLint
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.navigation.NavController
import com.google.firebase.Firebase
import com.google.firebase.auth.auth



@Composable
fun HomeScreen2(navController: NavController){

    Button(onClick = {
        Firebase.auth.signOut()
    }) {
        Text(text =  "Cerrar sesión" )

    }


}