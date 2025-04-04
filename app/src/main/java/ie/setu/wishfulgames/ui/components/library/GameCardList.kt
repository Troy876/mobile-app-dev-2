package ie.setu.wishfulgames.ui.components.library

import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.runtime.toMutableStateList
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.Wallpapers
import ie.setu.wishfulgames.data.GameModel
import ie.setu.wishfulgames.data.libraryList
import ie.setu.wishfulgames.ui.theme.WishfulgamesJPCTheme

@Composable
internal fun GameCardList(
    games: List<GameModel>,
    modifier: Modifier = Modifier,
    onDeleteGame: (GameModel) -> Unit,
    onClickGameDetails: (Int) -> Unit,
    onRefreshList: () -> Unit
) {
    LazyColumn {
        items(
            items = games,
            key = { game -> game._id }
        ) { game ->
            GameCard(
                title = game.title,
                description = game.description,
                genre = game.genre,
                rating = game.rating,
                price = game.price,
                onClickDelete = { onDeleteGame(game) },
                onClickGameDetails = { onClickGameDetails(game.id) },
                onRefreshList = onRefreshList
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
        GameCardList(
            libraryList.toMutableStateList(),
            onDeleteGame = {},
            onClickGameDetails = {},
            onRefreshList = { }
        )
    }
}
