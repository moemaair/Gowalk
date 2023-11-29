package com.example.gowalk.presentation.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowForward
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material3.Card
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.VerticalDivider
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

@Composable
fun Homeage_nav() {

    Column (modifier = Modifier.fillMaxWidth().height(60.dp).background(color = MaterialTheme.colorScheme.primary)){
        Surface(shape = CircleShape) {
            Box(modifier = Modifier
                .fillMaxWidth()
                .background(Color.White)
                .padding(12.dp)
                .height(30.dp),
                contentAlignment = Alignment.Center
            ){

                // menu
                Box (modifier = Modifier.align(Alignment.TopStart)){

                    Surface(shape = CircleShape, shadowElevation = 7.dp){
                        IconButton(onClick = { }) {
                            Icon(
                                imageVector = Icons.Filled.Menu,
                                contentDescription = "",

                            )
                        }
                    }

                }
                //distance
                Box (modifier = Modifier.align(Alignment.Center)){
                    Row (modifier = Modifier
                        .fillMaxHeight()
                        .align(Alignment.Center),
                        verticalAlignment = Alignment.CenterVertically
                    )
                    {
                        VerticalDivider()
                        Text(text = "7,300km",
                            modifier = Modifier

                                .padding(horizontal = 70.dp),
                            textAlign = TextAlign.Center
                        )
                        VerticalDivider()
                    }



                }
                //arrow
                Box (modifier = Modifier.align(Alignment.TopEnd)){
                    Surface(shape = CircleShape, shadowElevation = 7.dp){
                        IconButton(onClick = { }) {
                            Icon(
                                imageVector = Icons.Filled.ArrowForward,
                                contentDescription = ""
                            )
                        }
                    }
                }
            }
        }
    }


}

