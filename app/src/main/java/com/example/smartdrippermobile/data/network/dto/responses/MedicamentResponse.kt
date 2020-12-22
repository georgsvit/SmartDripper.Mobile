package com.example.smartdrippermobile.data.network.dto.responses

import com.google.gson.annotations.SerializedName

data class MedicamentResponse(
    @SerializedName("id")
    var Id: String,
    @SerializedName("title")
    var Title: String,
    @SerializedName("description")
    var Description: String,
    @SerializedName("manufacturer")
    var Manufacturer: ManufacturerResponse
)