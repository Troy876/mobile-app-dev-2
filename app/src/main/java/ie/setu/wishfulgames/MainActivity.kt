package ie.setu.wishfulgames

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import dagger.hilt.android.AndroidEntryPoint
import ie.setu.wishfulgames.navigation.Library
import ie.setu.wishfulgames.navigation.NavHostProvider
import ie.setu.wishfulgames.navigation.allDestinations
import ie.setu.wishfulgames.ui.components.general.BottomAppBarProvider
import ie.setu.wishfulgames.ui.components.general.TopAppBarProvider
import ie.setu.wishfulgames.ui.theme.WishfulgamesJPCTheme

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            WishfulgamesJPCTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    WishfulGamesApp(modifier = Modifier)
                }
            }
        }
    }
}

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun WishfulGamesApp(modifier: Modifier = Modifier,
                    navController: NavHostController = rememberNavController()) {
    val currentNavBackStackEntry by navController.currentBackStackEntryAsState()
    val currentDestination = currentNavBackStackEntry?.destination
    val currentBottomScreen =
        allDestinations.find { it.route == currentDestination?.route } ?: Library

    Scaffold(
        modifier = modifier,
        topBar = {
            TopAppBarProvider(
                currentScreen = currentBottomScreen,
                canNavigateBack = navController.previousBackStackEntry != null
            ) { navController.navigateUp() }
        },
        content = { paddingValues ->
            NavHostProvider(
                modifier = modifier,
                navController = navController,
                paddingValues = paddingValues
            )
        },
        bottomBar = {
            BottomAppBarProvider(navController,
                currentScreen = currentBottomScreen,)
        }
    )
}

@Preview(showBackground = true)
@Composable
fun MyAppPreview() {
    WishfulgamesJPCTheme {
        WishfulGamesApp(modifier = Modifier)
    }
}
