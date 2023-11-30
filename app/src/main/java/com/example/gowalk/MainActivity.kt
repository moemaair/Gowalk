package com.example.gowalk

import android.Manifest
import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Button
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
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
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.gowalk.domain.location.LocationViewModel
import com.example.gowalk.presentation.components.AcceptOrDenieLocation
import com.example.gowalk.presentation.components.EndMarker
import com.example.gowalk.presentation.components.Homeage_nav
import com.example.gowalk.ui.theme.GowalkTheme
import com.example.gowalk.utils.RequestPermission
import com.google.accompanist.permissions.ExperimentalPermissionsApi
import com.google.accompanist.permissions.rememberMultiplePermissionsState
import com.google.android.gms.maps.model.CameraPosition
import com.google.android.gms.maps.model.LatLng
import com.google.maps.android.compose.GoogleMap
import com.google.maps.android.compose.MapProperties
import com.google.maps.android.compose.MapType
import com.google.maps.android.compose.Marker
import com.google.maps.android.compose.MarkerState
import com.google.maps.android.compose.rememberCameraPositionState
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    @OptIn(ExperimentalPermissionsApi::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            GowalkTheme {
                //RequestPermission(permission = Manifest.permission.ACCESS_FINE_LOCATION)
                // A surface container using the 'background' color from the theme
                Greeting()

            }
        }
    }
}

@OptIn(ExperimentalPermissionsApi::class)
@Composable
fun Greeting(vm:LocationViewModel = hiltViewModel()) {

    val currentLocation = vm.currentLocation
    var lat by remember{
        mutableStateOf(currentLocation?.latitude ?: 0.0)
    }

    var lon by remember{
        mutableStateOf(currentLocation?.longitude ?: 0.0)
    }

    val locationPermissions = rememberMultiplePermissionsState(
        permissions = listOf(
            android.Manifest.permission.ACCESS_COARSE_LOCATION,
            android.Manifest.permission.ACCESS_FINE_LOCATION
        )
    )



    val singapore = LatLng(lat, lon)
    val cameraPositionState = rememberCameraPositionState {
        position = CameraPosition.fromLatLngZoom(singapore, 10f)
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
            properties = MapProperties(mapType = MapType.HYBRID),
            onMapClick = {
                onMapClick = true
                locationCurr = it


            }
        ) {
            Marker(
                state = MarkerState(position = singapore),
                title = "Singapore",
                snippet = "Marker in Singapore"
            )
            if(onMapClick){
                EndMarker(current = locationCurr)
            }
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
                locationPermissions.launchMultiplePermissionRequest()
            },
            modifier = Modifier
                .fillMaxWidth()
                .padding(10.dp)
                .align(Alignment.BottomEnd)

        ) {
            Text(text = "start")
        }

    }
    LaunchedEffect(key1 = locationPermissions.allPermissionsGranted) {
        if (locationPermissions.allPermissionsGranted) {
            vm.getCurrentLocation()
        }
    }

}







