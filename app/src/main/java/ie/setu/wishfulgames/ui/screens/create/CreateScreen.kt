package ie.setu.wishfulgames.ui.screens.create

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.material3.Slider
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.runtime.snapshots.SnapshotStateList
import androidx.compose.runtime.toMutableStateList
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import ie.setu.wishfulgames.data.GameModel
import ie.setu.wishfulgames.data.libraryList
import ie.setu.wishfulgames.ui.components.create.CreateButton
import ie.setu.wishfulgames.ui.components.create.CreateHeader
import ie.setu.wishfulgames.ui.components.create.PricePicker
import ie.setu.wishfulgames.ui.theme.WishfulgamesJPCTheme
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.hilt.navigation.compose.hiltViewModel

@Composable
fun CreateScreen(
    modifier: Modifier = Modifier,
    createViewModel: CreateViewModel = hiltViewModel()
) {
    var title by remember { mutableStateOf("") }
    var description by remember { mutableStateOf("") }
    var genre by remember { mutableStateOf("") }
    var rating by remember { mutableIntStateOf(1) }
    var price by remember { mutableIntStateOf(10) }
    val scrollState = rememberScrollState()
    var onFieldChange by rememberSaveable { mutableStateOf(false) }
    val errorEmpty = "Field cannot be empty..."
    val errorShort = "Field must be at least 2 characters"
    var isTitleEmpty by rememberSaveable { mutableStateOf(false) }
    var isTitleShort by rememberSaveable { mutableStateOf(false) }
    var isDescriptionEmpty by rememberSaveable { mutableStateOf(false) }
    var isDescriptionShort by rememberSaveable { mutableStateOf(false) }
    var isGenreEmpty by rememberSaveable { mutableStateOf(false) }
    var isGenreShort by rememberSaveable { mutableStateOf(false) }

    fun validateAllFields() {
        val titleIsEmpty = title.trim().isEmpty()
        val titleIsShort = title.length < 2
        val descriptionIsEmpty = description.trim().isEmpty()
        val descriptionIsShort = description.length < 2
        val genreIsEmpty = genre.trim().isEmpty()
        val genreIsShort = genre.length < 2

        isTitleEmpty = titleIsEmpty
        isTitleShort = titleIsShort
        isDescriptionEmpty = descriptionIsEmpty
        isDescriptionShort = descriptionIsShort
        isGenreEmpty = genreIsEmpty
        isGenreShort = genreIsShort

        onFieldChange = !(titleIsEmpty || titleIsShort || descriptionIsEmpty || descriptionIsShort || genreIsEmpty || genreIsShort)
    }

    fun validate(text: String, field: String) {
        val isEmpty = text.trim().isEmpty()
        val isShort = text.length < 2
        when(field){
            "title" -> {
                isTitleEmpty = isEmpty
                isTitleShort = isShort
            }
            "description" -> {
                isDescriptionEmpty = isEmpty
                isDescriptionShort = isShort
            }
            "genre" -> {
                isGenreEmpty = isEmpty
                isGenreShort = isShort
            }
        }
        validateAllFields()
    }

    Column {
        Column(
            modifier = modifier.padding(
                top = 48.dp,
                start = 24.dp,
                end = 24.dp
            )
                .verticalScroll(scrollState),
            verticalArrangement = Arrangement.spacedBy(20.dp),
        ) {
            CreateHeader()

            TextField(
                value = title,
                onValueChange = {
                    title = it
                    validate(title, "title")
                },
                maxLines = 2,
                label = { Text("Title") },
                isError = isTitleEmpty || isTitleShort,
                supportingText = {
                    if (isTitleEmpty) {
                        Text(
                            modifier = Modifier.fillMaxWidth(),
                            text = errorEmpty,
                            color = MaterialTheme.colorScheme.error
                        )
                    } else if (isTitleShort) {
                        Text(
                            modifier = Modifier.fillMaxWidth(),
                            text = errorShort,
                            color = MaterialTheme.colorScheme.error
                        )
                    }
                }
            )

            TextField(
                value = description,
                onValueChange = {
                    description = it
                    validate(description, "description")
                },
                maxLines = 2,
                label = { Text("Description") },
                isError = isDescriptionEmpty || isDescriptionShort,
                supportingText = {
                    if (isDescriptionEmpty) {
                        Text(
                            modifier = Modifier.fillMaxWidth(),
                            text = errorEmpty,
                            color = MaterialTheme.colorScheme.error
                        )
                    } else if (isDescriptionShort) {
                        Text(
                            modifier = Modifier.fillMaxWidth(),
                            text = errorShort,
                            color = MaterialTheme.colorScheme.error
                        )
                    }
                }
            )

            TextField(
                value = genre,
                onValueChange = {
                    genre = it
                    validate(genre, "genre")
                },
                maxLines = 2,
                label = { Text("Genre") },
                isError = isGenreEmpty || isGenreShort,
                supportingText = {
                    if (isGenreEmpty) {
                        Text(
                            modifier = Modifier.fillMaxWidth(),
                            text = errorEmpty,
                            color = MaterialTheme.colorScheme.error
                        )
                    } else if (isGenreShort) {
                        Text(
                            modifier = Modifier.fillMaxWidth(),
                            text = errorShort,
                            color = MaterialTheme.colorScheme.error
                        )
                    }
                }
            )

            Text(text = "Rating: $rating")
            Slider(
                value = rating.toFloat(),
                onValueChange = { rating = it.toInt() },
                valueRange = 1f..10f,
                steps = 8,
            )

            Row(
                verticalAlignment = Alignment.CenterVertically,
            )
            {
                PricePicker(
                    onPriceAmountChange = { price = it }
                )
            }
            CreateButton(
                modifier = modifier,
                game = GameModel(
                    title = title,
                    description = description,
                    genre = genre,
                    rating = rating,
                    price = price
                ),
                enabled = onFieldChange,
                onClick = {
                    createViewModel.insert(
                        GameModel(
                            title = title,
                            description = description,
                            genre = genre,
                            rating = rating,
                            price = price
                        )
                    )
                }
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun CreateScreenPreview() {
    WishfulgamesJPCTheme {
        PreviewCreateScreen(modifier = Modifier,
            games = libraryList.toMutableStateList())
    }
}

@Composable
fun PreviewCreateScreen(modifier: Modifier = Modifier,
                        games: SnapshotStateList<GameModel>
) {

    var title by remember { mutableStateOf("") }
    var description by remember { mutableStateOf("") }
    var genre by remember { mutableStateOf("") }
    var rating by remember { mutableIntStateOf(0) }
    var price by remember { mutableIntStateOf(0) }
    val scrollState = rememberScrollState()

    Column {
        Column(
            modifier = modifier.padding(
                top = 48.dp,
                start = 24.dp,
                end = 24.dp
            )
                .verticalScroll(scrollState),
            verticalArrangement = Arrangement.spacedBy(20.dp),
        ) {
            CreateHeader()

            TextField(
                value = title,
                onValueChange = { title = it },
                label = { Text("Title") }
            )

            TextField(
                value = description,
                onValueChange = { description = it },
                label = { Text("Description") }
            )

            TextField(
                value = genre,
                onValueChange = { genre = it },
                label = { Text("Genre") }
            )

            Text(text = "Rating: $rating")
            Slider(
                value = rating.toFloat(),
                onValueChange = { rating = it.toInt() },
                valueRange = 1f..10f,
                steps = 8,
            )

            Row(
                verticalAlignment = Alignment.CenterVertically,
            )
            {
                PricePicker(
                    onPriceAmountChange = { price = it }
                )
            }
            CreateButton(
                modifier = modifier,
                game = GameModel(
                    title = title,
                    description = description,
                    genre = genre,
                    rating = rating,
                    price = price
                ),
                enabled = true,
                onClick = {}
            )
        }
    }
}
