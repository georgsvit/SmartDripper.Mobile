package com.example.smartdrippermobile.data.network.dto.responses

import com.google.gson.annotations.SerializedName
import java.util.*

data class JWTTokenResponse(
    @SerializedName("token")
    var AccessToken: String,
    @SerializedName("expireDate")
    var ExpiresAt: Date,
    @SerializedName("name")
    var Name: String,
    @SerializedName("surname")
    var Surname: String,
    @SerializedName("Role")
    var RoleName: String
)