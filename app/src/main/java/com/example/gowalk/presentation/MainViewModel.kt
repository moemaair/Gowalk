package com.example.gowalk.presentation

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.State
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.text.input.TextFieldValue
import androidx.lifecycle.ViewModel

class MainViewModel: ViewModel(){


    var textFieldText by mutableStateOf("")

    // Example method to handle changes to the text value
    fun onTextFieldValueChanged(newText: String) {
        textFieldText = newText
    }
}