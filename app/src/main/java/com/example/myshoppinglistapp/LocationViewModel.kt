package com.example.myshoppinglistapp

import android.util.Log
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch

class LocationViewModel: ViewModel() {
    private val _location = mutableStateOf<LocationData?>(null)
    val location : State<LocationData?> = _location

    private val _address = mutableStateOf(listOf<GeoCodingResult>())
    val address: State<List<GeoCodingResult>> = _address


    fun updateLocation(newLocation: LocationData) {
        _location.value = newLocation
    }

    fun fetchAddress(latlng: String) {
        try{
            viewModelScope.launch {
                val result = RetrofitClient.create().getAddressFromCoordinates(
                    latlng,
                    "AIzaSyAxwrWHPjbB0E19xjNjtXn5b7dClkn95p8"
                )
                _address.value = result.results
            }
        } catch(e:Exception) {
            Log.d("res1", "${e.cause} ${e.message}")
        }
    }
}