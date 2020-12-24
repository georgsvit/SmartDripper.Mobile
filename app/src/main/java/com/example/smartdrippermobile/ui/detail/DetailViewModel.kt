package com.example.smartdrippermobile.ui.detail

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.smartdrippermobile.data.SessionManager
import com.example.smartdrippermobile.data.network.ApiClient
import com.example.smartdrippermobile.data.network.ApiStatus
import com.example.smartdrippermobile.data.network.dto.responses.*
import kotlinx.coroutines.launch

class DetailViewModel(application: Application) : AndroidViewModel(application) {
    private val _appointmentId = MutableLiveData<String>()
    val appointmentId: LiveData<String> = _appointmentId

    private val _selectedAppointment = MutableLiveData<AppointmentResponse>()
    var selectedAppointment: LiveData<AppointmentResponse> = _selectedAppointment

    private val _status = MutableLiveData<ApiStatus>()
    val status: LiveData<ApiStatus> = _status

    private var sessionManager: SessionManager
    private var _token: String

    init {
        sessionManager = SessionManager(getApplication())
        _token = sessionManager.fetchAuthToken()!!

        _appointmentId.value = sessionManager.fetchAppointmentId()

        if (_appointmentId.value != "-1") {
            getAppointment(_appointmentId.value!!)
        }
    }

    private fun getAppointment(id: String) {
        viewModelScope.launch {
            _status.value = ApiStatus.LOADING
            val apiClient = ApiClient()

            try {
                _selectedAppointment.value = apiClient.getApiService().getAppointmentById(id, "Bearer $_token")
                _status.value = ApiStatus.DONE
                Log.i("API", "Procedure: GET Appointment Value: ${_selectedAppointment.value}")
            } catch (e: java.lang.Exception) {
                Log.i("API", "Procedure: GET Appointment Error: $e")
                _status.value = ApiStatus.ERROR
                _selectedAppointment.value = AppointmentResponse("-1", "undefined", false, DoctorResponse("-1", "undefined", "undefined"), MedicamentResponse("-1", "undefined", "undefined", ManufacturerResponse("-1", "undefined","undefined")), PatientResponse("-1", "undefined", "undefined"))
            }
        }
    }
}