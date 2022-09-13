package com.example.superheros.navigation

sealed class AppScreens (val route: String) {
    //Here we will add all screen that we need, for this occation we just need the spash screen in the main
    object SplashScreen : AppScreens("splash_screen")
    object MainScreen : AppScreens("main_screen")
    object SuperInfo : AppScreens("super_info")
}