package com.example.smartdrippermobile.data.network

import com.example.smartdrippermobile.data.network.dto.requests.*
import com.example.smartdrippermobile.data.network.dto.responses.*
import okhttp3.ResponseBody
import retrofit2.http.*

interface ApiService {
    @POST(Constants.LOGIN_URL)
    suspend fun login(@Body request: LoginRequest) : JWTTokenResponse

    @GET(Constants.APPOINTMENTS_URL)
    suspend fun getAppointments(@Header("Authorization") token: String) : List<AppointmentResponse>

    @GET(Constants.APPOINTMENTS_URL + Constants.APPOINTMENT_ID_URL)
    suspend fun getAppointmentById(@Path("appointmentId") unitId: String, @Header("Authorization") token: String) : AppointmentResponse

    @POST(Constants.APPOINTMENTS_URL + Constants.DONE_URL)
    suspend fun setAppointmentDone(@Path("appointmentId") appointmentId: String, @Header("Authorization") token: String) : ResponseBody
}