package com.example.smartdrippermobile.data.network.dto.responses

import com.google.gson.annotations.SerializedName
import java.util.*

data class AppointmentResponse(
    @SerializedName("id")
    var Id: String,
    @SerializedName("date")
    var Date: String,
    @SerializedName("isDone")
    var IsDone: Boolean,
    @SerializedName("doctor")
    var Doctor: DoctorResponse,
    @SerializedName("medicament")
    var Medicament: MedicamentResponse,
    @SerializedName("patient")
    var Patient: PatientResponse
)