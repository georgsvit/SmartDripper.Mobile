package com.example.smartdrippermobile.ui.login

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.smartdrippermobile.R
import com.example.smartdrippermobile.data.SessionManager
import com.example.smartdrippermobile.data.network.ApiClient
import com.example.smartdrippermobile.data.network.ApiStatus
import com.example.smartdrippermobile.data.network.dto.requests.LoginRequest
import com.example.smartdrippermobile.data.network.dto.responses.JWTTokenResponse
import com.example.smartdrippermobile.util.isEmailValid
import com.example.smartdrippermobile.util.isPasswordValid
import kotlinx.coroutines.launch
import java.lang.Exception
import java.util.*

class LoginViewModel(application: Application) : AndroidViewModel(application) {

    private val _status = MutableLiveData<ApiStatus>()
    val status: LiveData<ApiStatus>
        get() = _status

    private val _loginForm = MutableLiveData<LoginFormState>()
    val loginFormState: LiveData<LoginFormState> = _loginForm

    private val _jwtTokenResponse = MutableLiveData<JWTTokenResponse>()
    private val _tokenDate = MutableLiveData<Date>()
    val tokenDate: LiveData<Date>
        get() = _tokenDate

    private var sessionManager: SessionManager

    init {
        _jwtTokenResponse.value = null
        sessionManager = SessionManager(context = getApplication())
        _tokenDate.value = sessionManager.fetchDate("token_date")
    }

    fun loginDataChanged(email: String, password: String) {
        if (!isEmailValid(email)) {
            _loginForm.value = LoginFormState(emailError = R.string.invalid_email)
        } else if (!isPasswordValid(password)) {
            _loginForm.value = LoginFormState(passwordError = R.string.invalid_password)
        } else {
            _loginForm.value = LoginFormState(isDataValid = true)
        }
    }

    fun onLoginClick(email: String, password: String) {
        viewModelScope.launch {
            _status.value = ApiStatus.LOADING

            val user = LoginRequest(Email = email, Password = password)
            val apiClient = ApiClient()
            val sessionManager = SessionManager(context = getApplication())

            try {
                _jwtTokenResponse.value = apiClient.getApiService().login(request = user)
                Log.i("API", "Procedure: Login Token: ${_jwtTokenResponse.value}")
                if (_jwtTokenResponse.value?.AccessToken != null) {
                    sessionManager.saveAllData(_jwtTokenResponse.value!!)
                    _status.value = ApiStatus.DONE
                    Log.i("API", "Procedure: Login Success: Token saved")
                }
            } catch (e: Exception) {
                Log.i("API", "Procedure: Login Error: ${e}")
                _status.value = ApiStatus.ERROR
            }
        }
    }
}