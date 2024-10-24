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
import com.ecapp.ecapp.screen.games.ScreenGameRompeCabezasNivel2
import com.ecapp.ecapp.screen.games.ScreenGameRompeCabezasNivel3
import com.ecapp.ecapp.screen.games.ScreenRompecabesas

import com.ecapp.ecapp.screen.games.laberinto.ScreenGameLaberinto
import com.ecapp.ecapp.screen.games.rompecabezas.ScreenFelicitacionesRompeCabezas
import com.ecapp.ecapp.screen.games.rompecabezas.ScreenGameOverRompeCabezas
import com.ecapp.ecapp.screen.games.screenGameMemoriaNivel2
import com.ecapp.ecapp.screen.games.screenGameMemoriaNivel3
import com.ecapp.ecapp.screen.games.secuencia.ScreenFelicitacionesSecuencia
import com.ecapp.ecapp.screen.games.secuencia.ScreenGameOverSecuencia
import com.ecapp.ecapp.screen.games.secuencia.ScreenGameSecuencia
import com.ecapp.ecapp.screen.loginScreen
import com.ecapp.ecapp.screen.games.sopa_de_letras.ScreenFelicitacionesSopaLetras
import com.ecapp.ecapp.screen.games.sopa_de_letras.ScreenGameOverSopaLetras
import com.ecapp.ecapp.screen.games.sopa_de_letras.ScreenGameSopaLetras
import com.ecapp.ecapp.screen.games.sopa_de_letras.gameSopaLetrasNivel2
import com.ecapp.ecapp.screen.games.sopa_de_letras.gameSopaLetrasNivel3
import com.ecapp.ecapp.screen.games.rompecabezas.ScreenGameRompeCabezas

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
        //pantalla de felicitaciones de Secuencia
        composable(route = AppScreens.screenFelicitacionesGameSecuencia.route) {
            ScreenFelicitacionesSecuencia(navController)
        }

        //pantalla de game sopa de letras
        composable(route = AppScreens.screenGameSopaLetras.route) {
            ScreenGameSopaLetras(navController)
        }

        //pantalla de game over sopa de letras
        composable(route = AppScreens.screenGameOverSopaLetras.route) {
            ScreenGameOverSopaLetras(navController)
        }

        //pantalla de game over sopa de letras nivel 2
        composable(route = AppScreens.screenGameOverSopaLetrasNivel2.route) {
            gameSopaLetrasNivel2(navController)
        }

        //pantalla de game over sopa de letras nivel 3
        composable(route = AppScreens.screenGameOverSopaLetrasNivel3.route) {
            gameSopaLetrasNivel3(navController)
        }

        //pantalla de felicitaciones sopa de letras
        composable(route = AppScreens. screenFelicitacionesGameSopaLetras.route) {
            ScreenFelicitacionesSopaLetras(navController)

        }

        //pantalla de Laberinto
        composable(route = AppScreens.screenGameLaberinto.route) {
            ScreenGameLaberinto(navController)

        }


        //pantalla de RompeCabezas
        composable(route = AppScreens.screenGameRompeCabezas.route) {
            ScreenGameRompeCabezas(navController)

        }

        //pantalla de GameOver RompeCabezas
        composable(route = AppScreens.screenGameOverRompeCabezas.route) {
            ScreenGameOverRompeCabezas(navController)

        }

        //pantalla de Game RompeCabezas nivel 2
        composable(route = AppScreens.screenGameRompeCabezasNivel2.route) {
            ScreenGameRompeCabezasNivel2(navController)

        }

        //pantalla de Game RompeCabezas nivel 3
        composable(route = AppScreens.screenGameRompeCabezasNivel3.route) {
            ScreenGameRompeCabezasNivel3(navController)

        }

//pantalla de Game RompeCabezas felicitaciones
        composable(route = AppScreens.screenFelicitacionesGameRompeCabezas.route) {
            ScreenFelicitacionesRompeCabezas(navController)

        }




}
}