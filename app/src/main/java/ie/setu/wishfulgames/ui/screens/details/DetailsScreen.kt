package ie.setu.wishfulgames.ui.screens.details

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Save
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import ie.setu.wishfulgames.data.GameModel
import ie.setu.wishfulgames.ui.components.details.DetailsScreenText
import ie.setu.wishfulgames.ui.theme.WishfulgamesJPCTheme

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun DetailsScreen(
    modifier: Modifier = Modifier,
    detailViewModel: DetailsViewModel = hiltViewModel()
) {
    var game = detailViewModel.game.value
    var onFieldChange by rememberSaveable { mutableStateOf(false) }
    var titleText by rememberSaveable { mutableStateOf(game.title) }
    var descriptionText by rememberSaveable { mutableStateOf(game.description) }
    var genreText by rememberSaveable { mutableStateOf(game.genre) }
    val errorEmpty = "Field cannot be empty..."
    val errorShort = "Field must be at least 2 characters"
    var isTitleEmpty by rememberSaveable { mutableStateOf(false) }
    var isTitleShort by rememberSaveable { mutableStateOf(false) }
    var isDescriptionEmpty by rememberSaveable { mutableStateOf(false) }
    var isDescriptionShort by rememberSaveable { mutableStateOf(false) }
    var isGenreEmpty by rememberSaveable { mutableStateOf(false) }
    var isGenreShort by rememberSaveable { mutableStateOf(false) }

    fun validate(text: String, field: String) {
        val isEmpty = text.trim().isEmpty()
        val isShort = text.length < 2
        when (field) {
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
        onFieldChange = !(isTitleEmpty || isTitleShort || isDescriptionEmpty || isDescriptionShort || isGenreEmpty || isGenreShort)
    }

    Column(
        modifier = modifier.padding(
            start = 24.dp,
            end = 24.dp,
        ),
        verticalArrangement = Arrangement.spacedBy(10.dp),
    ) {
        DetailsScreenText()
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier
                .fillMaxSize()
                .padding(
                    start = 10.dp,
                    end = 10.dp,
                ),
        ) {
            OutlinedTextField(
                modifier = Modifier.fillMaxWidth(),
                value = titleText,
                onValueChange = {
                    titleText = it
                    validate(titleText, "title")
                    game.title = titleText
                },
                maxLines = 2,
                label = { Text(text = "Title") },
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
            OutlinedTextField(
                modifier = Modifier.fillMaxWidth(),
                value = descriptionText,
                onValueChange = {
                    descriptionText = it
                    validate(descriptionText, "description")
                    game.description = descriptionText
                },
                maxLines = 2,
                label = { Text(text = "Description") },
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
            OutlinedTextField(
                modifier = Modifier.fillMaxWidth(),
                value = genreText,
                onValueChange = {
                    genreText = it
                    validate(genreText, "genre")
                    game.genre = genreText
                },
                maxLines = 2,
                label = { Text(text = "Genre") },
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
            Spacer(modifier.height(height = 48.dp))
            Button(
                onClick = {
                    detailViewModel.updateGame(game)
                    onFieldChange = false
                },
                elevation = ButtonDefaults.buttonElevation(20.dp),
                enabled = onFieldChange
            ) {
                Icon(Icons.Default.Save, contentDescription = "Save")
                Spacer(modifier.width(width = 8.dp))
                Text(
                    text = "Save",
                    fontWeight = FontWeight.Bold,
                    fontSize = 20.sp,
                    color = Color.White
                )
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun DetailScreenPreview() {
    WishfulgamesJPCTheme {
        PreviewDetailScreen(modifier = Modifier)
    }
}

@Composable
fun PreviewDetailScreen(modifier: Modifier) {

    var game by rememberSaveable { mutableStateOf(GameModel()) }
    var onMessageChanged by rememberSaveable { mutableStateOf(false) }

    Column(
        modifier = modifier.padding(
            start = 10.dp,
            end = 10.dp,
        ),
        verticalArrangement = Arrangement.spacedBy(10.dp),
    ) {
        DetailsScreenText()
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier
                .fillMaxSize()
                .padding(
                    start = 10.dp,
                    end = 10.dp,
                ),
        )
        {
            OutlinedTextField(modifier = Modifier.fillMaxWidth(),
                value = game.title,
                onValueChange = { game = game.copy(title = it); onMessageChanged = true },
                label = { Text(text = "Game Title") },
                colors = OutlinedTextFieldDefaults.colors(
                    unfocusedBorderColor = MaterialTheme.colorScheme.secondary,
                )
            )
            OutlinedTextField(modifier = Modifier.fillMaxWidth(),
                value = game.description,
                onValueChange = { game = game.copy(description = it); onMessageChanged = true },
                label = { Text(text = "Description") },
                colors = OutlinedTextFieldDefaults.colors(
                    unfocusedBorderColor = MaterialTheme.colorScheme.secondary,
                )
            )
            OutlinedTextField(modifier = Modifier.fillMaxWidth(),
                value = game.genre,
                onValueChange = {game = game.copy(genre = it); onMessageChanged = true },
                label = { Text(text = "Genre") },
                colors = OutlinedTextFieldDefaults.colors(
                    unfocusedBorderColor = MaterialTheme.colorScheme.secondary
                )
            )
            Spacer(modifier.height(height = 48.dp))
            Button(
                onClick = {
                    onMessageChanged = false
                },
                elevation = ButtonDefaults.buttonElevation(20.dp),
                enabled = onMessageChanged
            ){
                Icon(Icons.Default.Save, contentDescription = "Save")
                Spacer(modifier.width(width = 8.dp))
                Text(
                    text = "Save",
                    fontWeight = FontWeight.Bold,
                    fontSize = 20.sp,
                    color = Color.White
                )
            }
        }
    }
}
