package com.ecapp.ecapp.navegation
import com.ecapp.ecapp.screen.HomeScreenn
sealed class AppScreens (val route: String) {

object  screenHome:AppScreens("screenHome")
    object  screenLogin:AppScreens("loginScreen")

}