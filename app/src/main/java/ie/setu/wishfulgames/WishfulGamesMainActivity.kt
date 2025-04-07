package ie.setu.wishfulgames

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import dagger.hilt.android.AndroidEntryPoint
import ie.setu.wishfulgames.ui.screens.home.HomeScreen
import ie.setu.wishfulgames.ui.theme.WishfulgamesJPCTheme

@AndroidEntryPoint
class WishfulGamesMainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            WishfulgamesJPCTheme { HomeScreen() }
        }
    }
}
