package ie.setu.wishfulgames.ui.components.library

import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.runtime.snapshots.SnapshotStateList
import androidx.compose.runtime.toMutableStateList
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.Wallpapers
import ie.setu.wishfulgames.models.GameModel
import ie.setu.wishfulgames.models.libraryList
import ie.setu.wishfulgames.ui.theme.WishfulgamesJPCTheme

@Composable
internal fun GameCardList(
    games: SnapshotStateList<GameModel>,
    modifier: Modifier = Modifier
) {
    LazyColumn {
        items(
            items = games,
        ) { game ->
            GameCard(
                title = game.title,
                description = game.description,
                genre = game.genre,
                rating = game.rating,
                price = game.price
            )
        }
    }
}

@Preview(showBackground = true,
    wallpaper = Wallpapers.BLUE_DOMINATED_EXAMPLE
)
@Composable
fun GameCardListPreview() {
    WishfulgamesJPCTheme {
        GameCardList(libraryList.toMutableStateList())
    }
}