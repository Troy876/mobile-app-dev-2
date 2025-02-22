package ie.setu.wishfulgames

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import ie.setu.wishfulgames.ui.theme.WishfulgamesJPCTheme
import timber.log.Timber
import timber.log.Timber.i
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material.icons.filled.Warning
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp

class MainActivity : ComponentActivity() {
    @OptIn(ExperimentalMaterial3Api::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        Timber.plant(Timber.DebugTree())
        i("Wishfulgames MainActivity started..")
        setContent {
            WishfulgamesJPCTheme {
                Scaffold(
                    modifier = Modifier.fillMaxSize(),
                    topBar = {
                        TopAppBar(
                            title = {
                                Text(
                                    text = stringResource(id = R.string.app_name),
                                    color = Color.White
                                )
                            },
                            colors = TopAppBarDefaults.largeTopAppBarColors(
                                containerColor = MaterialTheme.colorScheme.primary
                            ),
                            navigationIcon = {
                                Icon(imageVector = Icons.Filled.Menu,
                                    contentDescription = "Menu",
                                    tint = Color.White)
                            }
                        )
                    }
                )
                { innerPadding ->
                    AddGame(
                        modifier = Modifier.padding(innerPadding)
                    )
                }
            }
        }
    }
}

@Composable
fun ShowSupportText(isError : Boolean)
{
    if (isError)
        Text(
            text = "This field is required",
            style = MaterialTheme.typography.labelMedium,
            color = MaterialTheme.colorScheme.error,
        )
    else Text(text = "")
}

@Composable
fun AddGame(modifier: Modifier = Modifier) {
    var title by remember { mutableStateOf("") }
    var showError by remember { mutableStateOf(false) }
    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(all = 8.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        OutlinedTextField(
            colors = OutlinedTextFieldDefaults.colors(
                focusedBorderColor = MaterialTheme.colorScheme.primary,
                unfocusedBorderColor = MaterialTheme.colorScheme.primary,
                cursorColor = MaterialTheme.colorScheme.primary
            ),
            value = title,
            onValueChange = {
                title = it
                showError = false
            },
            isError = showError,
            modifier = Modifier.fillMaxWidth(),
            label = { Text(stringResource(id = R.string.text_gameTitleHint)) },
            trailingIcon = {
                if (showError)
                    Icon(
                        Icons.Filled.Warning, "error",
                        tint = MaterialTheme.colorScheme.error
                    )
                else
                    Icon(
                        Icons.Default.Edit, contentDescription = "add/edit",
                        tint = Color.Black
                    )
            } ,
            supportingText = { ShowSupportText(showError) }
        )
        Button(
            onClick = {
                if (title.isEmpty()) {
                    showError = true
                } else {
                    addGame(title)
                }
            },
            modifier = Modifier.align(Alignment.CenterHorizontally),
            elevation = ButtonDefaults.buttonElevation(20.dp)
        ) {
            Icon(Icons.Default.Add, contentDescription = "Add")
            Spacer(modifier = Modifier.width(width = 4.dp))
            Text(stringResource(id = R.string.button_addGame))
        }
    }}

@OptIn(ExperimentalMaterial3Api::class)
@Preview(showBackground = true)
@Composable
fun AddGamePreview() {
    WishfulgamesJPCTheme {
        Scaffold(
            modifier = Modifier.fillMaxSize(),
            topBar = {
                TopAppBar(
                    title = {
                        Text(
                            text = stringResource(id = R.string.app_name),
                            color = Color.White
                        )
                    },
                    colors = TopAppBarDefaults.largeTopAppBarColors(
                        containerColor = MaterialTheme.colorScheme.primary
                    ),
                    navigationIcon = {
                        Icon(imageVector = Icons.Filled.Menu,
                            contentDescription = "Menu",
                            tint = Color.White)
                    }
                )
            },

            )
        { innerPadding ->
            AddGame(
                modifier = Modifier.padding(innerPadding)
            )
        }
    }
}

fun addGame(title: String) {
    i("Game title entered is : $title")
}