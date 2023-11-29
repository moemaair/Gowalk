package com.example.gowalk.domain.location

import androidx.lifecycle.ViewModel
import javax.inject.Inject

class LocationViewModel @Inject constructor(
    private val locationTracker: LocationTracker
) :ViewModel()