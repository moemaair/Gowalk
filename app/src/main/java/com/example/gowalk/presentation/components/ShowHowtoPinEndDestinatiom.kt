package com.example.gowalk.presentation.components

import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight

@Composable
fun ShowHowtoPinEndDestination() {
    val openDialog = remember { mutableStateOf(false) }
    AlertDialog(
        onDismissRequest = {
            openDialog.value = false
        },
        title = {
            Text(
                text = "Pinning Instructions",
                style = TextStyle(
                    fontSize = MaterialTheme.typography.headlineLarge.fontSize,
                    fontWeight = FontWeight.Bold
                )
            )
        },
        text = {
            Text("locate the place you want to stop your running or walking by clicking on the map, a pin will appear on that location")
        },
        confirmButton = {},
        dismissButton = {
            Button(onClick = {openDialog.value = false}) {
                Text("Done")
            }
        }
    )


}
