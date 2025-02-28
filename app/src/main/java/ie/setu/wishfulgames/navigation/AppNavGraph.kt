package ie.setu.wishfulgames.navigation

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.snapshots.SnapshotStateList
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import ie.setu.wishfulgames.models.GameModel
import ie.setu.wishfulgames.ui.components.screens.ScreenCreate
import ie.setu.wishfulgames.ui.components.screens.ScreenLibrary

@Composable
fun NavHostProvider(
    modifier: Modifier,
    navController: NavHostController,
    paddingValues: PaddingValues,
    games: SnapshotStateList<GameModel>
) {
    NavHost(
        navController = navController,
        startDestination = Create.route,
        modifier = Modifier.padding(paddingValues = paddingValues)) {

        composable(route = Library.route) {
            ScreenLibrary(modifier = modifier,games = games)
        }
        composable(route = Create.route) {
            ScreenCreate(modifier = modifier, games = games)
        }
    }
}