package com.example.gowalk.presentation.navigation

sealed class Screen (val route: String){
    object Home: Screen(route = "Home")
    object StartWalking : Screen(route = "StartWalking")
}