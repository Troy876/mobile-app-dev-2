package ie.setu.wishfulgames.ui.screens.library

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.runtime.snapshots.SnapshotStateList
import androidx.compose.runtime.toMutableStateList
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import ie.setu.wishfulgames.R
import ie.setu.wishfulgames.data.model.GameModel
import ie.setu.wishfulgames.data.model.libraryList
import ie.setu.wishfulgames.ui.components.general.Centre
import ie.setu.wishfulgames.ui.components.general.ShowError
import ie.setu.wishfulgames.ui.components.general.ShowLoader
import ie.setu.wishfulgames.ui.components.library.GameCardList
import ie.setu.wishfulgames.ui.components.library.LibraryHeader
import ie.setu.wishfulgames.ui.theme.WishfulgamesJPCTheme

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun LibraryScreen(modifier: Modifier = Modifier,
                  onClickGameDetails: (String) -> Unit,
                  libraryViewModel: LibraryViewModel = hiltViewModel()
) {
    val games by libraryViewModel.uiGames.collectAsState()
    val isError by libraryViewModel.isErr
    val isLoading by libraryViewModel.isLoading
    val error by libraryViewModel.error
    var searchQuery by remember { mutableStateOf("") }

    Column {
        Column(
            modifier = modifier.padding(
                top = 48.dp,
                start = 24.dp,
                end = 24.dp
            ),
        ) {
            LibraryHeader()
            TextField(
                value = searchQuery,
                onValueChange = {
                    searchQuery = it
                    libraryViewModel.searchGames(it)
                },
                leadingIcon = { Icon(Icons.Filled.Search, contentDescription = "Search") },
                label = { Text("Search Games") },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 8.dp)
            )

            if (isLoading) ShowLoader("Loading Games...")
            if(games.isEmpty() && !isError)
                Centre(Modifier.fillMaxSize()) {
                    Text(color = MaterialTheme.colorScheme.secondary,
                        fontWeight = FontWeight.Bold,
                        fontSize = 30.sp,
                        lineHeight = 34.sp,
                        textAlign = TextAlign.Center,
                        text = stringResource(R.string.empty_library)
                    )
                }
            if (!isError) {
                GameCardList(
                    games = games,
                    onClickGameDetails = onClickGameDetails,
                    onDeleteGame = { game: GameModel ->
                        libraryViewModel.deleteGame(game)
                    }
                )
            }

            if (isError) {
                ShowError(headline = error.message!! + " error...",
                    subtitle = error.toString(),
                    onClick = { libraryViewModel.getGames() })
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun LibraryScreenPreview() {
    WishfulgamesJPCTheme {
        PreviewLibraryScreen( modifier = Modifier,
            games = libraryList.toMutableStateList()
        )
    }
}

@Composable
fun PreviewLibraryScreen(modifier: Modifier = Modifier,
                        games: SnapshotStateList<GameModel>
) {

    Column {
        Column(
            modifier = modifier.padding(
                start = 24.dp,
                end = 24.dp
            ),
        ) {
            LibraryHeader()
            val searchQuery = remember { mutableStateOf("") }
            TextField(
                value = searchQuery.value,
                onValueChange = { searchQuery.value = it },
                leadingIcon = { Icon(Icons.Filled.Search, contentDescription = "Search") },
                label = { Text("Search Games") },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 8.dp)
            )
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
                    games = games,
                    onDeleteGame = {},
                    onClickGameDetails = { },
                )
        }
    }
}
