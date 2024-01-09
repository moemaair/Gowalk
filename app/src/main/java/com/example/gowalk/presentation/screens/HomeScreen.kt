package com.example.gowalk.presentation.screens

import android.Manifest
import android.annotation.SuppressLint
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.gowalk.domain.location.LocationViewModel
import com.example.gowalk.presentation.components.Homeage_nav
import com.example.gowalk.presentation.components.LabelEachWalkDialog
import com.google.accompanist.permissions.ExperimentalPermissionsApi
import com.google.accompanist.permissions.rememberMultiplePermissionsState
import com.google.android.gms.maps.model.CameraPosition
import com.google.android.gms.maps.model.LatLng
import com.google.maps.android.compose.Circle
import com.google.maps.android.compose.GoogleMap
import com.google.maps.android.compose.MapProperties
import com.google.maps.android.compose.MapType
import com.google.maps.android.compose.Marker
import com.google.maps.android.compose.MarkerState
import com.google.maps.android.compose.rememberCameraPositionState

@SuppressLint("PermissionLaunchedDuringComposition")
@OptIn(ExperimentalPermissionsApi::class)
@Composable
fun HomeScreen(
    vm: LocationViewModel = hiltViewModel(),
    goToStartWalkingScreen : () -> Unit
) {
    val locationPermissions = rememberMultiplePermissionsState(
        permissions = listOf(
            Manifest.permission.ACCESS_COARSE_LOCATION,
            Manifest.permission.ACCESS_FINE_LOCATION
        )
    )
//
//    LaunchedEffect(true){
//        locationPermissions.launchMultiplePermissionRequest()
//    }

    val scope = rememberCoroutineScope()
    val c = LocalContext.current

    var showCurrenMapLableDialog: Boolean by remember {
        mutableStateOf(false)
    }


    val singapore2 = LatLng(0.0, 0.0)
    val cameraPositionState = rememberCameraPositionState {
        position = CameraPosition.fromLatLngZoom(singapore2, 10f)
    }

    var onMapClick by remember {
        mutableStateOf(false)
    }

    var locationCurr by remember {
        mutableStateOf(LatLng(0.0,0.0))
    }


    Box(
        modifier = Modifier
            .fillMaxSize()
    ) {
        GoogleMap(
            modifier = Modifier.fillMaxSize(),
            cameraPositionState = cameraPositionState,
            properties = MapProperties(mapType = MapType.NORMAL),
            onMapClick = {
                onMapClick = true
                locationCurr = it
            }
        ) {
            Circle(
                center = singapore2,
                fillColor = MaterialTheme.colorScheme.primary
            )

            Marker(
                state = MarkerState(position = singapore2),
                title = "Singapore",
                snippet = "Marker in Singapore"
            )

        }

        // home navigation and menu
        Surface(modifier = Modifier
            ,
            shadowElevation = 32.dp
        ) {
            Homeage_nav()
        }
        // button to other screen
        Button(
            onClick = {
                showCurrenMapLableDialog = true
            },
            modifier = Modifier
                .fillMaxWidth()
                .padding(10.dp)
                .align(Alignment.BottomEnd)

        ) {
            Text(text = "start")
        }
        if(showCurrenMapLableDialog){
            LabelEachWalkDialog(
                onClick = true,
                goToStartWalkingScreen = goToStartWalkingScreen
            )
        }

    }
}
