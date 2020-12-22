package com.example.smartdrippermobile.data.network.dto.responses

import com.google.gson.annotations.SerializedName
import java.util.*

data class DoctorResponse(
    @SerializedName("id")
    var Id: String,
    @SerializedName("name")
    var Name: String,
    @SerializedName("surname")
    var Surname: String
) {
    fun getFullname() : String {
        return "$Name $Surname";
    }
}