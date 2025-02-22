package ie.setu.wishfulgames

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.List
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import ie.setu.wishfulgames.models.GameModel
import ie.setu.wishfulgames.ui.components.general.MenuItem
import ie.setu.wishfulgames.ui.components.screens.ScreenCreate
import ie.setu.wishfulgames.ui.components.screens.ScreenLibrary
import ie.setu.wishfulgames.ui.theme.WishfulgamesJPCTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

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
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun WishfulGamesApp(modifier: Modifier = Modifier) {
    val games = remember { mutableStateListOf<GameModel>() }
    var selectedMenuItem by remember { mutableStateOf<MenuItem?>(MenuItem.Library) }

    Scaffold(
        modifier = modifier,
        topBar = {
            TopAppBar(
                title = {
                    Text(
                        text = stringResource(id = R.string.app_name),
                        color = Color.White
                    )
                },
                colors = TopAppBarDefaults.largeTopAppBarColors(
                    containerColor = MaterialTheme.colorScheme.primary
                ),
                actions = {
                    if(selectedMenuItem == MenuItem.Create) {
                        IconButton(onClick = {
                            selectedMenuItem = MenuItem.Library
                        }) {
                            Icon(
                                imageVector = Icons.AutoMirrored.Filled.List,
                                contentDescription = "Options",
                                tint = Color.White,
                                modifier = Modifier.size(40.dp)
                            )
                        }
                    }
                    else {
                        IconButton(onClick = {
                            selectedMenuItem = MenuItem.Create
                        }) {
                            Icon(
                                imageVector = Icons.Filled.Add,
                                contentDescription = "Options",
                                tint = Color.White,
                                modifier = Modifier.size(40.dp)
                            )
                        }
                    }
                }
            )
        },
        content = {
            when (selectedMenuItem) {
                MenuItem.Create -> ScreenCreate(modifier = modifier, games = games)
                MenuItem.Library -> ScreenLibrary(modifier = modifier, games = games)
                else -> {}
            }
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
