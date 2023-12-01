package com.example.gowalk.presentation.screens

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
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonColors
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.VerticalDivider
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.google.android.gms.maps.model.CameraPosition
import com.google.android.gms.maps.model.LatLng
import com.google.maps.android.compose.Circle
import com.google.maps.android.compose.GoogleMap
import com.google.maps.android.compose.MapProperties
import com.google.maps.android.compose.MapType
import com.google.maps.android.compose.Marker
import com.google.maps.android.compose.MarkerState
import com.google.maps.android.compose.rememberCameraPositionState

@Composable

fun StartWalkScreen() {
// 4.0435 39.6682
    val singapore = LatLng(-4.0435, 39.6682)
    val cameraPositionState = rememberCameraPositionState {
        position = CameraPosition.fromLatLngZoom(singapore, 18f)
    }

    //---- 3 section

    // --- map
    // --- title + start button
    // --- timer

    Column(modifier = Modifier
        .fillMaxSize(),

    ){
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .height(300.dp)


        ) {
            GoogleMap(
                modifier = Modifier
                    .fillMaxSize()
                    .height(300.dp),
                cameraPositionState = cameraPositionState,
                properties = MapProperties(mapType = MapType.TERRAIN),
                onMapClick = {

                }
            ) {
                Circle(
                    center = singapore,
                    fillColor = MaterialTheme.colorScheme.primary
                )

                Marker(
                    state = MarkerState(position = singapore),
                    title = "Singapore",
                    snippet = "Marker in Singapore"
                )

            }
        }

        // title + button
        Surface(
            modifier = Modifier
                .fillMaxWidth()
                .height(200.dp)
                .padding(10.dp)
                .shadow(20.dp),
            shape = RoundedCornerShape(10.dp)


        )
        {
            Row(modifier = Modifier
                .fillMaxSize()
                .background(Color.White),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween
            ) {

                Column(modifier = Modifier
                    .fillMaxHeight(),
                    verticalArrangement = Arrangement.Center

                ) {
                    Text(
                        text = "Running 20mil today 🇰🇪",
                        modifier = Modifier.padding(vertical = 5.dp, horizontal = 10.dp),
                        textAlign = TextAlign.Center,
                        style = TextStyle(
                            fontSize = MaterialTheme.typography.bodyLarge.fontSize,
                            fontWeight = FontWeight.Medium
                        ),

                        )

                    Text(
                        text = "Goal incomplete",
                        modifier = Modifier.padding(vertical = 5.dp, horizontal = 10.dp),
                        textAlign = TextAlign.Center,
                        style = TextStyle(
                            fontSize = MaterialTheme.typography.bodySmall.fontSize,
                            fontWeight = FontWeight.Thin
                        ),

                        )
                }

                VerticalDivider(modifier = Modifier.padding(vertical = 20.dp))

                Column (modifier = Modifier
                    .fillMaxHeight()
                    .background(Color.White),
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
        // timer

        Column(
            modifier = Modifier
                .fillMaxSize(),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        )
        {

            Text(
                text = "0:1:57",
                textAlign = TextAlign.Center,
                style = TextStyle(
                    fontSize = 40.sp,
                    fontWeight = FontWeight.Bold
                ),

                modifier = Modifier.fillMaxWidth()
            )

        }

    }
}