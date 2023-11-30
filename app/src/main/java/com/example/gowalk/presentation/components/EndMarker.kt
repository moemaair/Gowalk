package com.example.gowalk.presentation.components

import androidx.compose.runtime.Composable
import com.google.android.gms.maps.model.CameraPosition
import com.google.android.gms.maps.model.LatLng
import com.google.maps.android.compose.Marker
import com.google.maps.android.compose.MarkerState
import com.google.maps.android.compose.rememberCameraPositionState

@Composable
fun EndMarker(
    current: LatLng
) {

    Marker(
        state = MarkerState(position = current),
        title = "Singapore",
        snippet = "Marker in Singapore"
    )

}