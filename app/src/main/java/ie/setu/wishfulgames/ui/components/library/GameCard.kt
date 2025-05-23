package ie.setu.wishfulgames.ui.components.library

import android.net.Uri
import androidx.compose.animation.animateContentSize
import androidx.compose.animation.core.Spring
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.spring
import androidx.compose.foundation.background
import androidx.compose.foundation.gestures.detectHorizontalDragGestures
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.material.icons.filled.KeyboardArrowUp
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.FilledTonalButton
import androidx.compose.material3.FilledTonalIconButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableFloatStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.IntOffset
import androidx.compose.ui.unit.dp
import ie.setu.wishfulgames.R
import ie.setu.wishfulgames.ui.theme.WishfulgamesJPCTheme
import kotlin.math.roundToInt

@Composable
fun GameCard(
    title: String,
    description: String,
    genre: String,
    rating: Int,
    price: Int,
    onClickDelete: () -> Unit,
    onClickGameDetails: () -> Unit,
    // photoUri: Uri
) {
    val swipeOffset = remember { mutableFloatStateOf(0f) }
    val density = LocalDensity.current
    val deleteActionRevealThreshold = with(density) { 100.dp.toPx() }
    var showDeleteConfirmDialog by remember { mutableStateOf(false) }

    val offsetX by animateFloatAsState(
        targetValue = swipeOffset.floatValue,
        animationSpec = spring(
            dampingRatio = Spring.DampingRatioMediumBouncy,
            stiffness = Spring.StiffnessLow
        ),
        label = "offsetX"
    )

    Box(modifier = Modifier.fillMaxWidth()) {
        Row(
            modifier = Modifier
                .fillMaxSize()
                .padding(vertical = 4.dp, horizontal = 2.dp),
            horizontalArrangement = Arrangement.Start,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Box(
                modifier = Modifier
                    .background(Color.Red)
                    .padding(start = 16.dp)
            ) {
                Icon(Icons.Filled.Delete, contentDescription = "Delete", tint = Color.White)
            }
            Spacer(modifier = Modifier.weight(1f))
        }

        Card(
            colors = CardDefaults.cardColors(
                containerColor = MaterialTheme.colorScheme.primary
            ),
            modifier = Modifier
                .padding(vertical = 4.dp, horizontal = 2.dp)
                .offset { IntOffset(offsetX.roundToInt(), 0) }
                .pointerInput(Unit) {
                    detectHorizontalDragGestures { _, dragAmount ->
                        swipeOffset.floatValue += dragAmount
                        if (swipeOffset.floatValue > deleteActionRevealThreshold) {
                            showDeleteConfirmDialog = true
                            swipeOffset.floatValue = 0f
                        } else if (swipeOffset.floatValue < 0) {
                            swipeOffset.floatValue = 0f
                        }
                    }
                }
        ) {
            GameCardContent(
                title,
                description,
                genre,
                rating,
                price,
                onClickDelete = { showDeleteConfirmDialog = true },
                onClickGameDetails = onClickGameDetails
            )
            if (showDeleteConfirmDialog) {
                ShowDeleteAlert(
                    onDismiss = { showDeleteConfirmDialog = false },
                    onDelete = onClickDelete,
                )
            }
        }
    }
}

@Composable
private fun GameCardContent(
    title: String,
    description: String,
    genre: String,
    rating: Int,
    price: Int,
    onClickDelete: () -> Unit,
    onClickGameDetails: () -> Unit,
    // photoUri: Uri
) {
    var expanded by remember { mutableStateOf(false) }

    Row(
        modifier = Modifier
            .padding(12.dp)
            .animateContentSize(
                animationSpec = spring(
                    dampingRatio = Spring.DampingRatioMediumBouncy,
                    stiffness = Spring.StiffnessLow
                )
            )
    ) {
        Column(
            modifier = Modifier
                .weight(1f)
                .padding(4.dp)
        ) {
            Row(verticalAlignment = Alignment.CenterVertically) {
                Icon(
                    imageVector = Icons.Filled.Menu,
                    "Game Details",
                    Modifier.padding(end = 8.dp)
                )
                Text(
                    text = title,
                    style = MaterialTheme.typography.headlineMedium.copy(
                        fontWeight = FontWeight.ExtraBold
                    )
                )
                Spacer(Modifier.weight(1f))
                Text(
                    text = "$rating/10",
                    style = MaterialTheme.typography.headlineSmall.copy(
                        fontWeight = FontWeight.ExtraBold
                    )
                )
                Spacer(Modifier.weight(1f))
                Text(
                    text = "€$price",
                    style = MaterialTheme.typography.headlineSmall.copy(
                        fontWeight = FontWeight.ExtraBold
                    )
                )
            }
            if (expanded) {
                Text(modifier = Modifier.padding(vertical = 16.dp), text = description)
                Text(modifier = Modifier.padding(vertical = 16.dp), text = genre)
                Row(modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween) {
                    FilledTonalButton(onClick = onClickGameDetails) {
                        Text(text = "Show More...")
                    }

                    FilledTonalIconButton(onClick = onClickDelete) {
                        Icon(Icons.Filled.Delete, "Delete Game")
                    }
                }
            }
        }
        IconButton(onClick = { expanded = !expanded }) {
            Icon(
                imageVector = if (expanded) Icons.Filled.KeyboardArrowUp else Icons.Filled.KeyboardArrowDown,
                contentDescription = if (expanded) {
                    stringResource(R.string.show_less)
                } else {
                    stringResource(R.string.show_more)
                }
            )

//            AsyncImage(
//                model = ImageRequest.Builder(LocalContext.current)
//                    .data(photoUri)
//                    .crossfade(true)
//                    .build(),
//                contentDescription = null,
//                contentScale = ContentScale.Crop,
//                modifier = Modifier
//                    .size(50.dp)
//                    .clip(CircleShape)
//            )
        }
    }
}

@Composable
fun ShowDeleteAlert(
    onDismiss: () -> Unit,
    onDelete: () -> Unit,
) {
    AlertDialog(
        onDismissRequest = onDismiss ,
        title = { Text(stringResource(id = R.string.confirm_delete)) },
        text = { Text(stringResource(id = R.string.confirm_delete_message)) },
        confirmButton = {
            Button(
                onClick = { onDelete()
                    }
            ) { Text("Yes") }
        },
        dismissButton = {
            Button(onClick = onDismiss) { Text("No") }
        }
    )
}

@Preview
@Composable
fun CreateCardPreview() {
    WishfulgamesJPCTheme {
        GameCard(
            title = "Game",
            description = "Amazing",
            genre = "Action",
            rating = 10,
            price = 60,
            onClickDelete = {  },
            onClickGameDetails = {  }
        )
    }
}
