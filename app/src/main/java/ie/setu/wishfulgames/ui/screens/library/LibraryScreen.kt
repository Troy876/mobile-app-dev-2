package ie.setu.wishfulgames.ui.screens.library

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ExposedDropdownMenuBox
import androidx.compose.material3.ExposedDropdownMenuDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.derivedStateOf
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

    var expandedPriceFilter by remember { mutableStateOf(false) }
    var selectedPrice by remember { mutableStateOf("All Prices") }
    val prices by remember(games) { derivedStateOf { games.distinctBy { it.price }.map { it.price }.sorted() } }

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

            ExposedDropdownMenuBox(
                expanded = expandedPriceFilter,
                onExpandedChange = { expandedPriceFilter = !expandedPriceFilter },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 8.dp)
            ) {
                TextField(
                    readOnly = true,
                    value = selectedPrice,
                    onValueChange = { },
                    label = { Text("Filter by Price") },
                    trailingIcon = {
                        ExposedDropdownMenuDefaults.TrailingIcon(expanded = expandedPriceFilter)
                    },
                    modifier = Modifier.menuAnchor()
                )
                ExposedDropdownMenu(
                    expanded = expandedPriceFilter,
                    onDismissRequest = { expandedPriceFilter = false }
                ) {
                    DropdownMenuItem(
                        text = { Text(text = "All Prices") },
                        onClick = {
                            selectedPrice = "All Prices"
                            libraryViewModel.filterByPrice(null)
                            expandedPriceFilter = false
                        }
                    )
                    prices.forEach { price ->
                        DropdownMenuItem(
                            text = { Text(text = "€$price") },
                            onClick = {
                                selectedPrice = "€$price"
                                libraryViewModel.filterByPrice(price)
                                expandedPriceFilter = false
                            }
                        )
                    }
                }
            }

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

@OptIn(ExperimentalMaterial3Api::class)
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
            var expandedPriceFilterPreview by remember { mutableStateOf(false) }
            var selectedPricePreview by remember { mutableStateOf("All Prices") }
            val pricesPreview by remember(games) { derivedStateOf { games.distinctBy { it.price }.map { it.price }.sorted() } }

            ExposedDropdownMenuBox(
                expanded = expandedPriceFilterPreview,
                onExpandedChange = { expandedPriceFilterPreview = !expandedPriceFilterPreview },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 8.dp)
            ) {
                TextField(
                    readOnly = true,
                    value = selectedPricePreview,
                    onValueChange = { },
                    label = { Text("Filter by Price") },
                    trailingIcon = {
                        ExposedDropdownMenuDefaults.TrailingIcon(expanded = expandedPriceFilterPreview)
                    },
                    modifier = Modifier.menuAnchor()
                )
                ExposedDropdownMenu(
                    expanded = expandedPriceFilterPreview,
                    onDismissRequest = { expandedPriceFilterPreview = false }
                ) {
                    DropdownMenuItem(
                        text = { Text(text = "All Prices") },
                        onClick = {
                            selectedPricePreview = "All Prices"
                            expandedPriceFilterPreview = false
                        }
                    )
                    pricesPreview.forEach { price ->
                        DropdownMenuItem(
                            text = { Text(text = "€$price") },
                            onClick = {
                                selectedPricePreview = "€$price"
                                expandedPriceFilterPreview = false
                            }
                        )
                    }
                }
            }

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
