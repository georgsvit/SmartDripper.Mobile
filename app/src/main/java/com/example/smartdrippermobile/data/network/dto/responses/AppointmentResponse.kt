package com.example.smartdrippermobile.data.network.dto.responses

import com.google.gson.annotations.SerializedName
import java.time.LocalDate
import java.time.format.DateTimeFormatter
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
) {
    fun getDateTime() : String {
        val time = Date.subSequence(startIndex = 11, 19)
        val ld = LocalDate.parse(Date.subSequence(0, 19), DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss"))
        return "$time ${ld.dayOfMonth}/${ld.monthValue}/${ld.year}"
    }
}