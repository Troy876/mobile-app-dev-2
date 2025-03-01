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
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material.icons.filled.Save
import androidx.compose.material.icons.filled.Warning
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
    var onMessageChanged by rememberSaveable { mutableStateOf(false) }
    var text by rememberSaveable { mutableStateOf("") }

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
            text = game.title
            OutlinedTextField(
                modifier = Modifier.fillMaxWidth(),
                value = text,
                onValueChange = {
                    text = it
                    game.title = text
                    onMessageChanged = true
                },
                label = { Text(text = "Title") },
            )
            text = game.description
            OutlinedTextField(
                modifier = Modifier.fillMaxWidth(),
                value = text,
                onValueChange = {
                    text = it
                    game.description = text
                    onMessageChanged = true
                },
                label = { Text(text = "Description") },
            )
            text = game.genre
            OutlinedTextField(
                modifier = Modifier.fillMaxWidth(),
                value = text,
                onValueChange = {
                    text = it
                    game.genre = text
                    onMessageChanged = true
                },
                label = { Text(text = "Genre") },
            )

            Spacer(modifier.height(height = 48.dp))
            Button(
                onClick = {
                    detailViewModel.updateGame(game)
                    onMessageChanged = false
                },
                elevation = ButtonDefaults.buttonElevation(20.dp),
                enabled = onMessageChanged
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
