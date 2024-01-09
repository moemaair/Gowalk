package com.example.gowalk.domain.location


import android.content.Context
import android.location.Location
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.gowalk.data.repository.DatastoreOperationsImpl
import com.google.accompanist.permissions.rememberMultiplePermissionsState
import com.google.android.gms.maps.model.LatLng
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class LocationViewModel @Inject constructor(
    private val locationTracker: LocationTracker
) :ViewModel(){

    //---- get Current location
    // Now we have to create a variable that will hold the current location state and it will be updated with the getCurrentLocation function.

    var currentLocation by mutableStateOf<Location?>(null)

    fun getCurrentLocation() {
        viewModelScope.launch {
            currentLocation = locationTracker.getCurrentLocation() // Location
        }
    }

}