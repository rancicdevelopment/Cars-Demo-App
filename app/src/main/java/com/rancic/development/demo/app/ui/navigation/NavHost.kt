package com.rancic.development.demo.app.ui.navigation

import android.annotation.SuppressLint
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.rancic.development.demo.app.model.Car
import com.rancic.development.demo.app.ui.screen.detailscreen.DetailScreen
import com.rancic.development.demo.app.ui.screen.home.HomeScreen


private const val CARS_ARG = "cars"

@SuppressLint("StateFlowValueCalledInComposition")
@Composable
fun MyAppNavHost(
    modifier: Modifier = Modifier,
    navController: NavHostController = rememberNavController(),
    startDestination: String = NavigationItem.Home.route,
) {
    NavHost(
        modifier = modifier,
        navController = navController,
        startDestination = startDestination
    ) {
        composable(
            route = NavigationItem.Home.route
        ) {
            HomeScreen(navController = {
                navController.currentBackStackEntry?.savedStateHandle?.set(CARS_ARG, it)
                navController.navigate(State.DETAILS.name)
            })
        }

        composable(
            route = NavigationItem.Details.route
        ) {
            val cars = navController.previousBackStackEntry?.savedStateHandle?.get<Car>(CARS_ARG)
            cars?.let {
                DetailScreen(navController = navController, it)
            }
        }
    }
}

//@Composable
//inline fun <reified T : ViewModel> NavBackStackEntry.sharedViewModel(navController: NavController): T {
//    val navGraphRoute = destination.parent?.route ?: return viewModel()
//    val parentEntry = remember(this) {
//        navController.getBackStackEntry(navGraphRoute)
//    }
//    return viewModel(parentEntry)
//}
