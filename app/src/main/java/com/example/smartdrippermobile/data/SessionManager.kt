package com.example.smartdrippermobile.data

import android.content.Context
import android.content.SharedPreferences
import com.example.smartdrippermobile.R
import com.example.smartdrippermobile.data.network.dto.responses.AppointmentResponse
import com.example.smartdrippermobile.data.network.dto.responses.JWTTokenResponse
import java.text.SimpleDateFormat
import java.util.*

class SessionManager(context: Context) {
    private var prefs: SharedPreferences = context.getSharedPreferences(
        context.getString(R.string.app_name),
        Context.MODE_PRIVATE
    )

    companion object {
        const val USER_TOKEN = "user_token"
        const val USER_NAME = "user_name"
        const val USER_SURNAME = "user_surname"
        const val USER_ROLE = "user_role"
        const val TOKEN_DATE = "token_date"
    }

    fun saveAllData(jwtTokenResponse: JWTTokenResponse) {
        val editor = prefs.edit()
        editor.putString(USER_TOKEN, jwtTokenResponse.AccessToken)
        editor.putString(USER_NAME, jwtTokenResponse.Name)
        editor.putString(USER_SURNAME, jwtTokenResponse.Surname)
        editor.putString(USER_ROLE, jwtTokenResponse.RoleName)

        editor.putString(TOKEN_DATE, jwtTokenResponse.ExpiresAt.toString())
        editor.apply()
    }

    fun saveAppointmentId(id: String) {
        val editor = prefs.edit()
        editor.putString("appointmentId", id)
        editor.apply()
    }

    fun fetchAuthToken(): String? {
        return prefs.getString(USER_TOKEN, null)
    }

    fun fetchUserRole(): String {
        return prefs.getString(USER_ROLE, null).toString()
    }

    fun fetchDate(dateName: String): Date? {
        val formatter = SimpleDateFormat("EEE MMM d HH:mm:ss Z yyyy", Locale.ENGLISH)
        val dateInString = prefs.getString(dateName, null)
        return if (dateInString != null) formatter.parse(dateInString) else null
    }

    fun fetchAppointmentId() : String {
        return prefs.getString("appointmentId", "-1")!!
    }
}