package com.example.testappsbuilding.navigation

import androidx.compose.ui.Modifier
import androidx.lifecycle.viewmodel.compose.viewModel



import com.example.testappsbuilding.AppViewModelProvider
import com.example.testappsbuilding.screen.AgeDestination
import com.example.testappsbuilding.screen.AgeScreen
import com.example.testappsbuilding.screen.HomeDestination
import com.example.testappsbuilding.screen.NameScreen
import com.example.testappsbuilding.screen.ProfileDestination
import com.example.testappsbuilding.screen.ProfileScreen

import androidx.compose.runtime.Composable
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable

import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.navigation
import com.example.testappsbuilding.screen.UserProfileScreenViewModel


@Composable
fun AppsBuldingNavHost(
    navController: NavHostController,
    modifier: Modifier = Modifier,
){
    NavHost(
        navController = navController,
        startDestination = HomeDestination.route,
        modifier = modifier
    ){
        composable(route = HomeDestination.route) {
            NameScreen(
                navigateToAge = { navController.navigate(AgeDestination.route) },)
        }


        composable(route = AgeDestination.route) {
            AgeScreen(
                navigateToProfile = { navController.navigate(ProfileDestination.route) },
                navigateBack = { navController.navigateUp() }
            )
        }

        composable(route = ProfileDestination.route) {
            val userProfileScreenViewModel: UserProfileScreenViewModel =
                viewModel(factory = AppViewModelProvider.Factory)

            ProfileScreen(
                navigateBack = { navController.navigateUp() },
                retryAction = userProfileScreenViewModel::getMarsPhoto
            )
        }
    }
}