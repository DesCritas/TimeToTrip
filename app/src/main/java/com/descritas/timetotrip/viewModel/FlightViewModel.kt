package com.descritas.timetotrip.viewModel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.descritas.timetotrip.dto.Flight
import com.descritas.timetotrip.model.FlightModel
import com.descritas.timetotrip.model.FlightModelState
import com.descritas.timetotrip.repository.FlightRepository
import com.descritas.timetotrip.repository.FlightRepositoryImpl
import kotlinx.coroutines.launch

private val empty = Flight(
    departure = "SVO",
    arrival = "FRA",
    startDate = "",
    endDate = "",
    price = "",
    startCity = "",
    endCity = "",
    liked = false
)

class FlightViewModel(application: Application) : AndroidViewModel(application) {
    private val repository: FlightRepository = FlightRepositoryImpl()
    private val _data1 = MutableLiveData(FlightModel())
    val data1: LiveData<FlightModel>
        get() = _data1

    private val _state = MutableLiveData<FlightModelState>(FlightModelState.Idle)
    val state: LiveData<FlightModelState>
        get() = _state



    init {
        getFlights()
    }

    fun getFlights() {
        viewModelScope
            .launch {

                try {
                    _state.value = FlightModelState.Loading
                    val flightList = repository.getFlights()

                    _data1.postValue(FlightModel(flights = flightList))
                    _state.value = FlightModelState.Idle
                } catch (e: Exception) {
                    _state.value = FlightModelState.Error
                }
            }
    }

    fun likeById(flight: Flight) {
        viewModelScope
            .launch {
                try {
                    repository.likeById(flight)
                    _state.value = FlightModelState.Idle

                } catch (e: Exception) {
                    _state.value =FlightModelState.Error
                }
            }


    }
    fun refresh() {
        viewModelScope.launch {
            try {
                _state.value = FlightModelState.Refresh
                val flightList = repository.getFlights()
                _data1.postValue(FlightModel(flights = flightList))
                _state.value = FlightModelState.Idle
            } catch (e: Exception) {
                _state.value = FlightModelState.Error
            }
        }
    }
}