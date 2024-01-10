package com.example.gowalk.domain.datastore.repository

import android.location.Location
import kotlinx.coroutines.flow.Flow

interface DataStoreOperations {
    suspend fun getLatitude(lat: Double?)
    fun readLatitude(): Flow<Double?>

    suspend fun getLongitude(lon: Double?)
    fun readLongitude(): Flow<Double?>

    suspend fun getUsersWalkingTitle(title: String)
    fun userWalkingTitle(): Flow<String>
}