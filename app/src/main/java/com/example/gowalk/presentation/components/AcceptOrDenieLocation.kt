package com.example.gowalk.presentation.components

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.gowalk.domain.location.LocationViewModel
import com.google.accompanist.permissions.ExperimentalPermissionsApi
import com.google.accompanist.permissions.rememberMultiplePermissionsState

@OptIn(ExperimentalPermissionsApi::class)
@Composable
fun AcceptOrDenieLocation() {
    val vm: LocationViewModel = viewModel()
//    val currentLocation = vm.currentLocation
    val locationPermissions = rememberMultiplePermissionsState(
        permissions = listOf(
            android.Manifest.permission.ACCESS_COARSE_LOCATION,
            android.Manifest.permission.ACCESS_FINE_LOCATION
        )
    )

//    LaunchedEffect(key1 = locationPermissions.allPermissionsGranted) {
//        if (locationPermissions.allPermissionsGranted) {
//            vm.getCurrentLocation()
//        }
//    }
    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ){

        AlertDialog(
            onDismissRequest = {},
            title = {
                Text(
                    text = "Permission Request",
                    style = TextStyle(
                        fontSize = MaterialTheme.typography.headlineLarge.fontSize,
                        fontWeight = FontWeight.Bold
                    )
                )
            },
            text = {
                Text(text = "To use this app's functionalities, you need to give us the permission.")
            },
            confirmButton = {
                Button(onClick = {
                    locationPermissions.launchMultiplePermissionRequest()
                    //vm.getCurrentLocation()

                    //Log.i("cor", "${vm.currentLocation}")
                }
                ) {
                    Text("Give Permission")
                }
            }
        )

//        AnimatedContent(
//            targetState = locationPermissions.allPermissionsGranted, label = ""
//        ) { areGranted ->
//            Column(
//                horizontalAlignment = Alignment.CenterHorizontally,
//                verticalArrangement = Arrangement.spacedBy(10.dp)
//            ) {
//                if (areGranted) {
//                    // go to home
//                    Text(text = "${currentLocation?.latitude ?: 0.0} ${currentLocation?.longitude ?: 0.0}")
//                    Button(
//                        onClick = { vm.getCurrentLocation() }
//                    ) {
//                        Text(text = "Get current location")
//                    }
//                } else {
//                    Text(text = "We need location permissions for this application.")
//                    Button(
//                        onClick = { locationPermissions.launchMultiplePermissionRequest() }
//                    ) {
//                        Text(text = "Accept")
//                    }
//                }
//            }
//        }
    }

    //Greeting()
}