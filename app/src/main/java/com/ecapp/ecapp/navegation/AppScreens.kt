package com.ecapp.ecapp.navegation
import com.ecapp.ecapp.screen.HomeScreenn
sealed class AppScreens (val route: String) {

object  screenHome:AppScreens("screenHome")
    object  screenLogin:AppScreens("loginScreen")
    object  screenUser:AppScreens("screenUser")
    object  screenRegisterUser:AppScreens("screenRegisterUser")
    object  screenBienbenida:AppScreens("screenBienbenida")
    object  screenPerfilUser:AppScreens("screenPerfilUser")

}