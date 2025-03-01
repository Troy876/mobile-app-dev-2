package ie.setu.wishfulgames.ui.screens.library

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.snapshots.SnapshotStateList
import androidx.compose.runtime.toMutableStateList
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import ie.setu.wishfulgames.R
import ie.setu.wishfulgames.data.GameModel
import ie.setu.wishfulgames.data.libraryList
import ie.setu.wishfulgames.ui.components.general.Centre
import ie.setu.wishfulgames.ui.components.library.GameCardList
import ie.setu.wishfulgames.ui.components.library.LibraryHeader
import ie.setu.wishfulgames.ui.theme.WishfulgamesJPCTheme

@Composable
fun ScreenLibrary(modifier: Modifier = Modifier,
                 games: SnapshotStateList<GameModel>
) {

    Column {
        Column(
            modifier = modifier.padding(
                top = 48.dp,
                start = 24.dp,
                end = 24.dp
            ),
        ) {
            LibraryHeader()
            if(games.isEmpty())
                Centre(Modifier.fillMaxSize()) {
                    Text(color = MaterialTheme.colorScheme.secondary,
                        fontWeight = FontWeight.Bold,
                        fontSize = 30.sp,
                        lineHeight = 34.sp,
                        textAlign = TextAlign.Center,
                        text = stringResource(R.string.empty_library)
                    )
                }
            else
                GameCardList(
                    games = games
                )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun LibraryScreenPreview() {
    WishfulgamesJPCTheme {
        ScreenLibrary( modifier = Modifier,
            games = libraryList.toMutableStateList()
        )
    }
}