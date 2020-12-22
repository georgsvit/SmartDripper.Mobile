package com.example.smartdrippermobile.data.network.dto.responses

import com.google.gson.annotations.SerializedName

data class ManufacturerResponse(
    @SerializedName("id")
    var Id: String,
    @SerializedName("name")
    var Name: String,
    @SerializedName("country")
    var Country: String
)