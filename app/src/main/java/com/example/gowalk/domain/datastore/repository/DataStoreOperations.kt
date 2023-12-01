package com.example.gowalk.domain.datastore.repository

import android.location.Location
import kotlinx.coroutines.flow.Flow

interface DataStoreOperations {
    suspend fun getLocationCordinates(cordinate: Location)
    fun readGetLocationCordinates(): Flow<String>
}