package ie.setu.wishfulgames.navigation

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import ie.setu.wishfulgames.ui.screens.create.CreateScreen
import ie.setu.wishfulgames.ui.screens.details.DetailsScreen
import ie.setu.wishfulgames.ui.screens.home.HomeScreen
import ie.setu.wishfulgames.ui.screens.library.LibraryScreen
import ie.setu.wishfulgames.ui.screens.login.LoginScreen
import ie.setu.wishfulgames.ui.screens.profile.ProfileScreen
import ie.setu.wishfulgames.ui.screens.register.RegisterScreen

@Composable
fun NavHostProvider(
    modifier: Modifier = Modifier,
    navController: NavHostController,
    startDestination: AppDestination,
    paddingValues: PaddingValues,
) {
    NavHost(
        navController = navController,
        startDestination = startDestination.route,
        modifier = Modifier.padding(paddingValues = paddingValues)) {

        composable(route = Create.route) {
            CreateScreen(modifier = modifier)
        }
        composable(route = Library.route) {
            LibraryScreen(modifier = modifier,
                onClickGameDetails = {
                    gameId : Int ->
                    navController.navigateToGameList(gameId)
                })
        }
        composable(route = Home.route) {
            HomeScreen(modifier = modifier)
        }
        composable(route = Login.route) {
            LoginScreen(
                navController = navController,
                onLogin = { navController.popBackStack() }
            )
        }
        composable(route = Register.route) {
            RegisterScreen(
                navController = navController,
                onRegister = { navController.popBackStack() }
            )
        }
        composable(
            route = Details.route,
            arguments = Details.arguments
        )
        { navBackStackEntry ->
            val id = navBackStackEntry.arguments?.getString(Details.idArg)
            if (id != null) {
                DetailsScreen()
            }
        }
        composable(route = Profile.route) {
            ProfileScreen(
                onSignOut = {
                    navController.popBackStack()
                    navController.navigate(Login.route) {
                        popUpTo(Home.route) { inclusive = true }
                    }
                },
            )
        }
    }
}

private fun NavHostController.navigateToGameList(gameId: Int) {
    this.navigate("details/$gameId")
}
