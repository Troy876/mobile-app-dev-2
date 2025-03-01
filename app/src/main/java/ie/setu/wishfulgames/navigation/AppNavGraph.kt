package ie.setu.wishfulgames.navigation

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.snapshots.SnapshotStateList
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import ie.setu.wishfulgames.data.GameModel
import ie.setu.wishfulgames.ui.screens.create.ScreenCreate
import ie.setu.wishfulgames.ui.screens.library.ScreenLibrary

@Composable
fun NavHostProvider(
    modifier: Modifier,
    navController: NavHostController,
    paddingValues: PaddingValues,
    games: SnapshotStateList<GameModel>
) {
    NavHost(
        navController = navController,
        startDestination = Library.route,
        modifier = Modifier.padding(paddingValues = paddingValues)) {

        composable(route = Create.route) {
            ScreenCreate(modifier = modifier,games = games)
        }
        composable(route = Library.route) {
            ScreenLibrary(modifier = modifier, games = games)
        }
    }
}