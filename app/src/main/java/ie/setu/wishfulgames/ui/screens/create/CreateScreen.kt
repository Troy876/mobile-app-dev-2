package ie.setu.wishfulgames.ui.screens.create

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
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

@Composable
fun CreateScreen(
    modifier: Modifier = Modifier
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
                )
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
                )
            )
        }
    }
}
