package com.example.gowalk.domain.location

import android.location.Location

interface LocationTracker {
    suspend fun getCurrentLocation():Location?

    //------------------ Location       ⬆️     object from android -----------------

    // A data class representing a geographic location. A location consists of a latitude,
    // longitude, timestamp, accuracy, and other information such as bearing, altitude and velocity.
}