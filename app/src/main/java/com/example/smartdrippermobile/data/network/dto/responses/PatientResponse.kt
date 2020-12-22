package com.example.smartdrippermobile.data.network.dto.responses

import com.google.gson.annotations.SerializedName

data class PatientResponse(
    @SerializedName("id")
    var Id: String,
    @SerializedName("name")
    var Name: String,
    @SerializedName("surname")
    var Surname: String
)