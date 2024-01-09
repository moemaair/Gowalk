package com.example.gowalk.presentation.navigation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable

import com.example.gowalk.MainActivity
import com.example.gowalk.presentation.components.LabelEachWalkDialog
import com.example.gowalk.presentation.screens.HomeScreen
import com.example.gowalk.presentation.screens.StartWalkScreen
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

@Composable
fun ScreenNavGraph(
    startDestination: String,
    navController: NavHostController
) {
    NavHost(
        startDestination = startDestination,
        navController = navController
    ){
        home(
            goToStartWalkingScreen = {
                navController.popBackStack()
                navController.navigate(Screen.StartWalking.route)
            }

        )
        startwalking(

        )

    }
}
fun NavGraphBuilder.home(
    goToStartWalkingScreen: () -> Unit
){
    composable(route = Screen.Home.route){
        val scope = rememberCoroutineScope()
        HomeScreen(
            goToStartWalkingScreen  = goToStartWalkingScreen

        )
    }
}

fun NavGraphBuilder.startwalking(

){
    composable(route = Screen.StartWalking.route){
        val scope = rememberCoroutineScope()
        StartWalkScreen(
            takeUserToPin = false

        )
    }
}