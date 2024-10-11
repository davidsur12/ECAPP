package com.ecapp.ecapp.navegation

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.ecapp.ecapp.screen.HomeScreenn
import com.ecapp.ecapp.screen.loginScreen

@Composable
fun AppNavegation(){
    val navController = rememberNavController()

    NavHost(
        navController = navController,
        startDestination = AppScreens.screenHome.route // Pantalla inicial
    ) {
        // Primera pantalla: Home
        composable(route = AppScreens.screenHome.route) {
            HomeScreenn(navController) // Pasa el navController para navegar a otra pantalla
        }

        // Segunda pantalla: Details
        composable(route = AppScreens.screenLogin.route) {
            loginScreen(navController) // Nueva pantalla
        }
    }



}