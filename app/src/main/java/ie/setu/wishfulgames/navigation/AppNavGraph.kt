package ie.setu.wishfulgames.navigation

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import ie.setu.wishfulgames.ui.screens.create.CreateScreen
import ie.setu.wishfulgames.ui.screens.library.LibraryScreen

@Composable
fun NavHostProvider(
    modifier: Modifier,
    navController: NavHostController,
    paddingValues: PaddingValues
) {
    NavHost(
        navController = navController,
        startDestination = Library.route,
        modifier = Modifier.padding(paddingValues = paddingValues)) {

        composable(route = Create.route) {
            CreateScreen(modifier = modifier)
        }
        composable(route = Library.route) {
            LibraryScreen(modifier = modifier)
        }
    }
}
