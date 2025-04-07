package ie.setu.wishfulgames.ui.components.create

import android.widget.Toast
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
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.snapshots.SnapshotStateList
import androidx.compose.runtime.toMutableStateList
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import ie.setu.wishfulgames.R
import ie.setu.wishfulgames.data.model.GameModel
import ie.setu.wishfulgames.data.model.libraryList
import ie.setu.wishfulgames.ui.components.general.ShowLoader
import ie.setu.wishfulgames.ui.screens.create.CreateViewModel
import ie.setu.wishfulgames.ui.screens.library.LibraryViewModel
import ie.setu.wishfulgames.ui.theme.WishfulgamesJPCTheme
import timber.log.Timber

@Composable
fun CreateButton(
    modifier: Modifier = Modifier,
    game: GameModel,
    createViewModel: CreateViewModel = hiltViewModel(),
    libraryViewModel: LibraryViewModel = hiltViewModel(),
    enabled: Boolean
) {
    val context = LocalContext.current
    val isError = createViewModel.isErr.value

    Row {
        Button(
            onClick = {
                createViewModel.insert(game)
            },
            elevation = ButtonDefaults.buttonElevation(20.dp),
            enabled = enabled
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
    if(isError)
        Toast.makeText(context,"Unable to create at this time...",
            Toast.LENGTH_SHORT).show()
}

@Preview(showBackground = true)
@Composable
fun CreateButtonPreview() {
    WishfulgamesJPCTheme {
        PreviewCreateButton(
            Modifier,
            GameModel(),
            games = libraryList.toMutableStateList()
        )
    }
}

@Composable
fun PreviewCreateButton(
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
