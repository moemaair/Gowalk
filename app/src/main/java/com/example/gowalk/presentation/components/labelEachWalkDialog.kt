package com.example.gowalk.presentation.components

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.BottomSheetScaffold
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.gowalk.presentation.MainViewModel


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun LabelEachWalkDialog(
    onClick: Boolean,
    goToStartWalkingScreen : () -> Unit

) {
    var  onClickBtn by remember {
        mutableStateOf(false)
    }
    var viewModel: MainViewModel = hiltViewModel()
    val state = rememberModalBottomSheetState()
    val scope = rememberCoroutineScope()
    var showRequiredText by remember {
        mutableStateOf(false)
    }
    var isButtonClicked by remember { mutableStateOf(false) }
    ModalBottomSheet(
        onDismissRequest = {},
        sheetState = state,

    ){

        Column(modifier = Modifier
            .fillMaxSize()
            .padding(20.dp), horizontalAlignment = Alignment.CenterHorizontally) {
            Text(
                text = "Map Label required!",
                style = TextStyle(
                    fontSize = MaterialTheme.typography.headlineMedium.fontSize,
                    fontWeight = FontWeight.Bold
                ),
                modifier = Modifier.fillMaxWidth()
            )
            Text(text = "Great walk! Would you like to label this walk? Adding a label can help you remember the details later.")

            Spacer(modifier = Modifier.height(30.dp))

            Column(Modifier.fillMaxWidth()) {

                OutlinedTextField(
                    value = viewModel.textFieldText,
                    onValueChange = { newText ->
                        viewModel.onTextFieldValueChanged(newText)
                    },
                    label = {
                        Text(text = "Walk Title", style = TextStyle(
                            fontSize = MaterialTheme.typography.labelSmall.fontSize,
                            fontWeight = FontWeight.Bold
                        ))
                    },
                    modifier = Modifier.fillMaxWidth()
                )
//                if(showRequiredText){
//                    Text("Required", color = Color.Red)
//                }
                if (isButtonClicked && viewModel.textFieldText.isEmpty()) {
                    Text("Required",
                        color = Color.Red,
                        fontSize = 12.sp
                    )
                }
                Button(
                    onClick = {
                        isButtonClicked = true
                       // to other screen
                        if (isButtonClicked && !viewModel.textFieldText.isEmpty()) {
                            goToStartWalkingScreen()

                        }

                    },
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(10.dp)


                ) {
                    Text(text = "Continue")
                }

            }

        }

    }

    
}