package com.example.smartdrippermobile.ui.appointments

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.smartdrippermobile.data.SessionManager
import com.example.smartdrippermobile.data.network.ApiClient
import com.example.smartdrippermobile.data.network.ApiStatus
import com.example.smartdrippermobile.data.network.dto.responses.AppointmentResponse
import kotlinx.coroutines.launch
import java.lang.Exception

class AppointmentsViewModel(application: Application) : AndroidViewModel(application) {
    private val _appointments = MutableLiveData<List<AppointmentResponse>>()
    val appointments: LiveData<List<AppointmentResponse>> = _appointments

    private val _status = MutableLiveData<ApiStatus>()
    val status: LiveData<ApiStatus> = _status

    private var apiClient: ApiClient
    private var sessionManager: SessionManager
    private var _token: String

    init {
        _appointments.value = null
        apiClient = ApiClient()
        sessionManager = SessionManager(getApplication())
        _token = sessionManager.fetchAuthToken()!!

        getAppointments()
    }

    fun globalGetAppointments() {
        getAppointments()
    }

    private fun getAppointments() {
        viewModelScope.launch {
            _status.value = ApiStatus.LOADING
            val apiClient = ApiClient()

            try {
                _appointments.value = apiClient.getApiService().getAppointments("Bearer $_token")
                _status.value = ApiStatus.DONE
                Log.i("API", "Procedure: GET Appointments Value: ${_appointments.value}")
            } catch (e: Exception) {
                Log.i("API", "Procedure: GET Appointments Error: $e")
                _status.value = ApiStatus.ERROR
                _appointments.value = ArrayList()
            }
        }
    }

    fun saveAppointmentIdToSP(id: String) {
        sessionManager.saveAppointmentId(id)
    }
}