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

}