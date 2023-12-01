package com.example.gowalk.data.repository

import android.content.Context
import android.location.Location
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.doublePreferencesKey
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.emptyPreferences
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import com.example.gowalk.domain.datastore.repository.DataStoreOperations
import com.example.gowalk.utils.Constants
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.map
import java.io.IOException

val Context.dataStore: DataStore<Preferences> by preferencesDataStore(name = Constants.PREFERENCE_NAME) // name of datastore
class DatastoreOperationsImpl(context: Context) : DataStoreOperations{

    private object PreferencesKey {
        val lat= doublePreferencesKey(name = Constants.LATITUDE)
        val lon = doublePreferencesKey(name = Constants.LONGITUDE)
    }
    private val dataStore = context.dataStore
    override suspend fun getLatitude(lat: Double?) {
        dataStore.edit { preferences ->
            if (lat != null) {
                preferences[PreferencesKey.lat] = lat.toDouble()
            }
        }
    }

    override fun readLatitude(): Flow<Double?> {
        return dataStore.data
            .catch { exception ->
                if(exception is IOException){
                    emit(emptyPreferences())
                } else{
                    throw exception
                }
            }
            .map { preferences ->
                val lat = preferences[PreferencesKey.lat]
                lat

            }
    }

    override suspend fun getLongitude(lon: Double?) {
        dataStore.edit { preferences ->
            if (lon != null) {
                preferences[PreferencesKey.lon] = lon.toDouble()
            }
        }
    }

    override fun readLongitude(): Flow<Double?> {
        return dataStore.data
            .catch { exception ->
                if(exception is IOException){
                    emit(emptyPreferences())
                } else{
                    throw exception
                }
            }
            .map { preferences ->
                val lon = preferences[PreferencesKey.lat]
                lon

            }
    }


}