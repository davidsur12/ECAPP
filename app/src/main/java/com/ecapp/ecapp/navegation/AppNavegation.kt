package com.ecapp.ecapp.navegation

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.ecapp.ecapp.screen.usuario.Bienbenida
import com.ecapp.ecapp.screen.usuario.HomeScreen2
import com.ecapp.ecapp.screen.HomeScreenn
import com.ecapp.ecapp.screen.usuario.PerfilUser
import com.ecapp.ecapp.screen.RegistroUser
import com.ecapp.ecapp.screen.ScreenConfiguracion
import com.ecapp.ecapp.screen.usuario.ScreenGames
import com.ecapp.ecapp.screen.games.cancelacioObjetos.ScreenFelicitacionesMemoria
import com.ecapp.ecapp.screen.games.cancelacioObjetos.ScreenGameMemoria
import com.ecapp.ecapp.screen.games.cancelacioObjetos.ScreenGameOverMemoria
import com.ecapp.ecapp.screen.games.rompecabezas.ScreenGameRompeCabezasNivel2
import com.ecapp.ecapp.screen.games.rompecabezas.ScreenGameRompeCabezasNivel3
import com.ecapp.ecapp.screen.games.rompecabezas.ScreenRompecabesas
import com.ecapp.ecapp.screen.games.laberinto.ScreenGameLaberinto
import com.ecapp.ecapp.screen.games.rompecabezas.ScreenFelicitacionesRompeCabezas
import com.ecapp.ecapp.screen.games.rompecabezas.ScreenGameOverRompeCabezas
import com.ecapp.ecapp.screen.games.cancelacioObjetos.screenGameMemoriaNivel2
import com.ecapp.ecapp.screen.games.cancelacioObjetos.screenGameMemoriaNivel3
import com.ecapp.ecapp.screen.games.laberinto.ScreenFelicitacionesLaberinbto
import com.ecapp.ecapp.screen.games.laberinto.ScreenGameOverLaberinto
import com.ecapp.ecapp.screen.games.secuencia.ScreenFelicitacionesSecuencia
import com.ecapp.ecapp.screen.games.secuencia.ScreenGameOverSecuencia
import com.ecapp.ecapp.screen.games.secuencia.ScreenGameSecuencia
import com.ecapp.ecapp.screen.login.loginScreen
import com.ecapp.ecapp.screen.games.sopa_de_letras.ScreenFelicitacionesSopaLetras
import com.ecapp.ecapp.screen.games.sopa_de_letras.ScreenGameOverSopaLetras
import com.ecapp.ecapp.screen.games.sopa_de_letras.ScreenGameSopaLetras
import com.ecapp.ecapp.screen.games.sopa_de_letras.gameSopaLetrasNivel2
import com.ecapp.ecapp.screen.games.sopa_de_letras.gameSopaLetrasNivel3
import com.ecapp.ecapp.screen.progreso.ScreenProgreso
import com.ecapp.ecapp.screen.progreso.ScreenProgreso.ScreenProgresoCancelacionObjetos
import com.ecapp.ecapp.screen.progreso.ScreenProgreso.ScreenProgresoRompeCabezas
import com.ecapp.ecapp.screen.progreso.ScreenProgreso.ScreenProgresoSecuencia
import com.ecapp.ecapp.screen.progreso.ScreenProgreso.ScreenProgresoSopaLetras
import com.ecapp.ecapp.screen.progreso.ScreenProgreso.progresoLaberinto


import com.ecapp.ecapp.screen.progreso.ScreenProgresoGamesOpciones
import com.ecapp.ecapp.screen.progreso.graficos.ScreenGraficos


@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun AppNavegation() {
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
        composable(route = AppScreens.screenFelicitacionesGameSopaLetras.route) {
            ScreenFelicitacionesSopaLetras(navController)

        }

        //pantalla de Laberinto
        composable(route = AppScreens.screenGameLaberinto.route) {
            ScreenGameLaberinto(navController)

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

        //pantalla de progreso cognitivo
        composable(route = AppScreens.screenProgresoCognitivo.route) {
            ScreenProgreso(navController)

        }


        //pantalla de  progreso de los juegos sopa de letras
        composable(route = AppScreens.screenProgresoGamesSopaLetras.route) {
            ScreenProgresoSopaLetras(navController)

        }

        //pantalla de  progreso de los juegos secuencia
        composable(route = AppScreens.screenProgresoGamesSecuencia.route) {
            ScreenProgresoSecuencia(navController)

        }

        //pantalla de  progreso de los cancelacion objetos
        composable(route = AppScreens.screenProgresoGamesCancelacionObjetos.route) {
            ScreenProgresoCancelacionObjetos(navController)

        }

        //pantalla de  progreso de los cancelacion objetos
        composable(route = AppScreens.screenProgresoGamesRompeCabezas.route) {
            ScreenProgresoRompeCabezas(navController)

        }

        //pantalla de  progreso cognitivo
        composable(route = AppScreens.screenProgresoGames.route) {
            ScreenProgreso(navController)

        }

//pantalla de  progreso cognitivo
        composable(route = AppScreens.screenProgresoGamesOPciones.route) {
            ScreenProgresoGamesOpciones(navController)

        }

        //pantalla de  progreso cognitivo
        composable(route = AppScreens.screenProgresoGamesGraficos.route) {
            ScreenGraficos(navController)

        }

        //pantalla de  felicitaciones Game laberinto
        composable(route = AppScreens.screenFelicitacionesGamesLaberinto.route) {
            ScreenFelicitacionesLaberinbto(navController)

        }

        //pantalla de  game over Game  laberinto
        composable(route = AppScreens.screenGameOverLaberinto.route) {
            ScreenGameOverLaberinto(navController)

        }

        //pantalla configuracion
        composable(route = AppScreens.screenConfiguraciones.route) {
            ScreenConfiguracion(navController)

        }

        //pantalla progreso laberinto
        composable(route = AppScreens.screenProgresoLaberinto.route) {
            progresoLaberinto(navController)

        }


    }
}