package com.ecapp.ecapp.navegation
import com.ecapp.ecapp.screen.HomeScreenn
sealed class AppScreens (val route: String) {

    object  screenHome:AppScreens("screenHome")
    object  screenLogin:AppScreens("loginScreen")
    object  screenUser:AppScreens("screenUser")
    object  screenRegisterUser:AppScreens("screenRegisterUser")
    object  screenBienbenida:AppScreens("screenBienbenida")
    object  screenPerfilUser:AppScreens("screenPerfilUser")
    object  screenGames:AppScreens("screenGames")
    object  screenRompecabesas:AppScreens("screenRompecabesas")
    object  screenMemoria:AppScreens("screenMemoria")
    object  screenFelicitacionesMemoria:AppScreens("screenFelicitacionesMemoria")
    object  screenGameOverMemoria:AppScreens("screenGameOverMemoria")
    object  screenGameMmeorianivel2:AppScreens("screenGameMemorianivel2")
    object  screenGameMmeorianivel3:AppScreens("screenGameMemorianivel3")
    object  screenGameSecuencia:AppScreens("screenGameSecuencia")
    object  screenGameOverSecuencia:AppScreens("screenGameOverSecuencia")
    object  screenFelicitacionesGameSecuencia:AppScreens("screenFelicitacionesGameSecuencia")
    object  screenGameSopaLetras:AppScreens("ScreenGameSopaLetras")
    object  screenGameOverSopaLetras:AppScreens("ScreenGameOverSopaLetras")
    object  screenGameOverSopaLetrasNivel2:AppScreens("ScreenGameSopaLetrasNivel2")
    object  screenGameOverSopaLetrasNivel3:AppScreens("ScreenGameSopaLetrasNivel3")
    object  screenFelicitacionesGameSopaLetras:AppScreens("ScreenFelicitacionesGameSopaLetras")
    object  screenGameLaberinto:AppScreens("ScreenGameLaberinto")
    object  screenGameRompeCabezas:AppScreens(" ScreenGameRompeCabezas")
    object  screenGameOverRompeCabezas:AppScreens("ScreenGameOverRompeCabezas")
    object  screenGameRompeCabezasNivel2:AppScreens(" ScreenGameRompeCabezasNivel2")
    object  screenGameRompeCabezasNivel3:AppScreens(" ScreenGameRompeCabezasNivel3")
    object  screenFelicitacionesGameRompeCabezas:AppScreens("ScreenGameFelicitacionesRompeCabezas")
    object  screenProgresoCognitivo:AppScreens("ScreenProgresoCognitivo")
    object  screenProgresoGames:AppScreens("ScreenProgresoCognitivo")
    object  screenProgresoGamesSopaLetras:AppScreens("ScreenProgresoGameSopaLetras")
    object  screenProgresoGamesSecuencia:AppScreens("ScreenProgresoGameSecuencia")
    object  screenProgresoGamesCancelacionObjetos:AppScreens("ScreenProgresoGameCancelacionObjetos")
    object  screenProgresoGamesRompeCabezas:AppScreens("ScreenProgresoGameRompeCabezas")
    object  screenProgresoGamesOPciones:AppScreens("ScreenProgresoGamesOpciones")
    object  screenProgresoGamesGraficos:AppScreens("ScreenProgresoGamesGraficos")
    object  screenFelicitacionesGamesLaberinto:AppScreens("ScreenFelicitacionesGamesLaberinto")
    object  screenGameOverLaberinto:AppScreens("ScreenFelicitacionesGamesSecuencia")
    object  screenConfiguraciones:AppScreens("ScreenConfiguraciones")
    object  screenProgresoLaberinto:AppScreens("ScreenProgresoLaberinto")




}