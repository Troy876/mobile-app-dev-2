package ie.setu.wishfulgames.navigation

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.List
import androidx.compose.material.icons.filled.AddCircle
import androidx.compose.material.icons.filled.Details
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.navigation.NavType
import androidx.navigation.navArgument

interface AppDestination {
    val icon: ImageVector
    val label: String
    val route: String
}

object Library : AppDestination {
    override val icon = Icons.AutoMirrored.Filled.List
    override val label = "Library"
    override val route = "library"
}

object Create : AppDestination {
    override val icon = Icons.Filled.AddCircle
    override val label = "Create"
    override val route = "create"
}

object Details : AppDestination {
    override val icon = Icons.Filled.Details
    override val label = "Details"
    const val idArg = "id"
    override val route = "details/{$idArg}"
    val arguments = listOf(
        navArgument(idArg) { type = NavType.IntType }
    )
}

val bottomAppBarDestinations = listOf(Library, Create)
val allDestinations = listOf(Library, Create, Details)
