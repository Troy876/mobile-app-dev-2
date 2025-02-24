package ie.setu.wishfulgames.ui.components.create

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.snapshots.SnapshotStateList
import androidx.compose.runtime.toMutableStateList
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import ie.setu.wishfulgames.models.GameModel
import ie.setu.wishfulgames.models.libraryList
import ie.setu.wishfulgames.ui.theme.WishfulgamesJPCTheme
import ie.setu.wishfulgames.R
import timber.log.Timber

@Composable
fun CreateButton(
    modifier: Modifier = Modifier,
    game: GameModel,
    games: SnapshotStateList<GameModel>
) {
    Row {
        Button(
            onClick = {
                games.add(game)
                Timber.i("Game info : $game")
                Timber.i("Game library list info : ${games.toList()}")
            },
            elevation = ButtonDefaults.buttonElevation(20.dp)
        ) {
            Icon(Icons.Default.Add, contentDescription = "Add")
            Spacer(modifier.width(width = 4.dp))
            Text(
                text = stringResource(R.string.button_addGame),
                fontWeight = FontWeight.Bold,
                fontSize = 20.sp,
                color = Color.White
            )
        }

        Spacer(modifier.weight(1f))
        Text(
            buildAnnotatedString {
                withStyle(
                    style = SpanStyle(
                        fontWeight = FontWeight.Bold,
                        fontSize = 20.sp,
                        color = Color.Black
                    )
                ) {}


                withStyle(
                    style = SpanStyle(
                        fontWeight = FontWeight.Bold,
                        fontSize = 20.sp,
                        color = MaterialTheme.colorScheme.secondary)
                ) {
                }
            })
    }
}

@Preview(showBackground = true)
@Composable
fun CreateButtonPreview() {
    WishfulgamesJPCTheme {
        CreateButton(
            Modifier,
            GameModel(),
            games = libraryList.toMutableStateList()
        )
    }
}
