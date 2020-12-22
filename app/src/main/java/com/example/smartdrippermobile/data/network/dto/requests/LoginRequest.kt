package com.example.smartdrippermobile.data.network.dto.requests

import com.google.gson.annotations.SerializedName

class LoginRequest(
    @SerializedName("Login")
    var Email: String,
    @SerializedName("Password")
    var Password: String
)