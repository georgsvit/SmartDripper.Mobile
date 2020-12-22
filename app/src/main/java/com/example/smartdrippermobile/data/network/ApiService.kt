package com.example.smartdrippermobile.data.network

import com.example.smartdrippermobile.data.network.dto.requests.*
import com.example.smartdrippermobile.data.network.dto.responses.*
import retrofit2.http.*

interface ApiService {
    @POST(Constants.LOGIN_URL)
    suspend fun login(@Body request: LoginRequest) : JWTTokenResponse
}