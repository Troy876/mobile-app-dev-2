package ie.setu.wishfulgames.navigation

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.List
import androidx.compose.material.icons.filled.AddCircle
import androidx.compose.ui.graphics.vector.ImageVector

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

val bottomAppBarDestinations = listOf(Library, Create)
val allDestinations = listOf(Library, Create)
