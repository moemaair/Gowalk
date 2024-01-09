package com.example.gowalk.presentation.screens

import android.Manifest
import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Star
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonColors
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.VerticalDivider
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.gowalk.data.DefaultLocationTracker
import com.example.gowalk.data.repository.DatastoreOperationsImpl
import com.example.gowalk.domain.location.LocationViewModel
import com.example.gowalk.presentation.components.EndMarker
import com.example.gowalk.presentation.components.ShowHowtoPinEndDestination
import com.example.gowalk.utils.HandleRequest
import com.example.gowalk.utils.PermissionDeniedContent
import com.google.accompanist.permissions.ExperimentalPermissionsApi
import com.google.accompanist.permissions.rememberMultiplePermissionsState
import com.google.accompanist.permissions.rememberPermissionState
import com.google.android.gms.maps.model.CameraPosition
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.Marker
import com.google.maps.android.compose.Circle
import com.google.maps.android.compose.GoogleMap
import com.google.maps.android.compose.MapProperties
import com.google.maps.android.compose.MapType
import com.google.maps.android.compose.Marker
import com.google.maps.android.compose.MarkerState
import com.google.maps.android.compose.rememberCameraPositionState



@OptIn(ExperimentalPermissionsApi::class)
@Composable
fun StartWalkScreen(
    vm: LocationViewModel = hiltViewModel(),
    takeUserToPin: Boolean
) {
    // -4.0435 39.6682
    val scope = rememberCoroutineScope()
    val context = LocalContext.current
    var takeUserToPin by remember {
        mutableStateOf(!takeUserToPin)
    }

    val locationPermissions = rememberMultiplePermissionsState(
        permissions = listOf(
            Manifest.permission.ACCESS_COARSE_LOCATION,
            Manifest.permission.ACCESS_FINE_LOCATION
        )
    )

    if (takeUserToPin){
        ShowHowtoPinEndDestination()
    }
//
//    LaunchedEffect(key1 = locationPermissions.allPermissionsGranted ){
//
//
//        DatastoreOperationsImpl(context).getLatitude(vm.currentLocation?.latitude)
//        DatastoreOperationsImpl(context).getLongitude(vm.currentLocation?.longitude)
//    }
//
//
////    LaunchedEffect(key1 = locationPermissions.allPermissionsGranted) {
////        if (locationPermissions.allPermissionsGranted) {
////            vm.getCurrentLocation()
////            DatastoreOperationsImpl(context).getLatitude(latitude)
////            DatastoreOperationsImpl(context).getLongitude(longitude)
////        }
////    }
//    val lat by DatastoreOperationsImpl(context).readLatitude().collectAsState(initial = 0.0)
//    val lon by DatastoreOperationsImpl(context).readLongitude().collectAsState(initial = 0.0)
//

    val cameraPositionState = rememberCameraPositionState {
        position = CameraPosition.fromLatLngZoom(LatLng(-4.0435 ,39.6682), 18f)
    }


    //---- 3 section

    // --- map
    // --- title + start button
    // --- timer

    var onMapClick by remember {
        mutableStateOf(false)
    }
    var locationCurr by remember {
        mutableStateOf(LatLng(0.0, 0.0))
    }

    Box(modifier = Modifier){
        // Google map
        Box(
            modifier = Modifier
                .fillMaxSize()

        ) {
            GoogleMap(
                modifier = Modifier
                    .fillMaxSize()
                    .fillMaxHeight(),
                cameraPositionState = cameraPositionState,
                properties = MapProperties(mapType = MapType.NORMAL),
                onMapClick = {
                    onMapClick = true
                    locationCurr = it

                }
            ) {
                Marker(
                    state = MarkerState(position = LatLng(-4.0435, 39.6682)),
                    title = "Start destination",
                    snippet = "start point"
                )
                if(onMapClick){
                    EndMarker(locationCurr)
                }

            }

        }

        // title + button
        Surface(
            modifier = Modifier

                .fillMaxWidth()
                .height(150.dp)
                .padding(15.dp)
                .shadow(50.dp),
            shape = RoundedCornerShape(10.dp)

        )
        {
            Row(modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween
            ) {

                Column(modifier = Modifier
                    .fillMaxHeight(),
                    verticalArrangement = Arrangement.Center

                ) {
                    Text(
                        text = "Running 20mil today 🇰🇪",
                        modifier = Modifier.padding(vertical = 10.dp, horizontal = 10.dp),
                        textAlign = TextAlign.Center,
                        style = TextStyle(
                            fontSize = MaterialTheme.typography.bodyLarge.fontSize,
                            fontWeight = FontWeight.Medium
                        ),

                        )


                    Row {
                        Icon(imageVector = Icons.Filled.Star, contentDescription = "",
                            modifier = Modifier.padding(start = 10.dp),
                            tint = MaterialTheme.colorScheme.outline)

                        Text(
                            text = "Goal incomplete",
                            modifier = Modifier.padding(vertical = 5.dp, horizontal = 10.dp),
                            textAlign = TextAlign.Center,
                            style = TextStyle(
                                fontSize = MaterialTheme.typography.bodyMedium.fontSize,
                                fontWeight = FontWeight.Thin
                            ),

                            )
                    }
                }

                VerticalDivider(modifier = Modifier.padding(vertical = 20.dp))

                Column (modifier = Modifier
                    .fillMaxHeight(),
                    verticalArrangement = Arrangement.Center
                ){
                    Button(
                        onClick = {


                        },
                        modifier = Modifier
                            .padding(10.dp)
                    ) {
                        Text(text = "start Walking")
                    }
                }
            }

        }
    }
}

