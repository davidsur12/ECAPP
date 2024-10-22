package com.ecapp.ecapp.navegation

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.ecapp.ecapp.screen.Bienbenida
import com.ecapp.ecapp.screen.HomeScreen2
import com.ecapp.ecapp.screen.HomeScreenn
import com.ecapp.ecapp.screen.PerfilUser
import com.ecapp.ecapp.screen.RegistroUser
import com.ecapp.ecapp.screen.ScreenGames
import com.ecapp.ecapp.screen.games.ScreenFelicitacionesMemoria
import com.ecapp.ecapp.screen.games.ScreenGameMemoria
import com.ecapp.ecapp.screen.games.ScreenGameOverMemoria
import com.ecapp.ecapp.screen.games.ScreenRompecabesas
import com.ecapp.ecapp.screen.games.screenGameMemoriaNivel2
import com.ecapp.ecapp.screen.games.screenGameMemoriaNivel3
import com.ecapp.ecapp.screen.games.secuencia.ScreenFelicitacionesSecuencia
import com.ecapp.ecapp.screen.games.secuencia.ScreenGameOverSecuencia
import com.ecapp.ecapp.screen.games.secuencia.ScreenGameSecuencia
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
            HomeScreenn(navController)
        }

        //pantalla logeo correo y pasword
        composable(route = AppScreens.screenLogin.route) {
            loginScreen(navController)
        }
        //pantalla usuario logeado
        composable(route = AppScreens.screenUser.route) {
            HomeScreen2(navController)
        }
          //pntalla de registro de usuario
        composable(route = AppScreens.screenRegisterUser.route) {
            RegistroUser(navController)
        }

        //pantalla de Bienbenida
        composable(route = AppScreens.screenBienbenida.route) {
            Bienbenida(navController)
        }

        //pantalla de Perfil Usuario
        composable(route = AppScreens.screenPerfilUser.route) {
            PerfilUser(navController)
        }

        //pantalla de Games
        composable(route = AppScreens.screenGames.route) {
            ScreenGames(navController)
        }
        //pantalla de Rompecabesas
        composable(route = AppScreens.screenRompecabesas.route) {
            ScreenRompecabesas(navController)
        }
        //pantalla de Memoria
        composable(route = AppScreens.screenMemoria.route) {
            ScreenGameMemoria(navController)
        }

        //pantalla de Felicitaciones Memoria
        composable(route = AppScreens.screenFelicitacionesMemoria.route) {
            ScreenFelicitacionesMemoria(navController)
        }

        //pantalla de GameOver Memoria
        composable(route = AppScreens.screenGameOverMemoria.route) {
            ScreenGameOverMemoria(navController)
        }
        //pantalla de Memoria nivel 2
        composable(route = AppScreens.screenGameMmeorianivel2.route) {
            screenGameMemoriaNivel2(navController)
        }

        //pantalla de Memoria nivel 3
        composable(route = AppScreens.screenGameMmeorianivel3.route) {
            screenGameMemoriaNivel3(navController)
        }

        //pantalla de juego de Secuencia
        composable(route = AppScreens.screenGameSecuencia.route) {
            ScreenGameSecuencia(navController)
        }

        //pantalla de game over de Secuencia
        composable(route = AppScreens.screenGameOverSecuencia.route) {
            ScreenGameOverSecuencia(navController)
        }
        //pantalla de game over de Secuencia
        composable(route = AppScreens.screenFelicitacionesGameSecuencia.route) {
            ScreenFelicitacionesSecuencia(navController)
        }



}
}