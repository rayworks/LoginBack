package com.rayworks.example.login.ui.login

import android.util.Patterns
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope

import com.rayworks.example.login.Event
import com.rayworks.example.login.R
import com.rayworks.example.login.data.LoginRepository
import com.rayworks.example.login.data.Result

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import timber.log.Timber

class LoginViewModel(private val loginRepository: LoginRepository) : ViewModel() {

    private val _loginForm = MutableLiveData<Event<LoginFormState>>()
    val loginFormState: LiveData<Event<LoginFormState>> = _loginForm

    private val _loginResult = MutableLiveData<Event<LoginResult>>()
    val loginResult: LiveData<Event<LoginResult>> = _loginResult

    fun login(username: String, password: String) {
        viewModelScope.launch {

            withContext(Dispatchers.IO) {
                // simulate the time consuming login
                Timber.d(">>> login in progress...")
                delay(5000)
                val result = loginRepository.login(username, password)

                withContext(Dispatchers.Main) {
                    Timber.d(">>> about to call login callback ")
                    if (result is Result.Success) {
                        _loginResult.value =
                            Event(LoginResult(success = LoggedInUserView(displayName = result.data.displayName)))
                    } else {
                        _loginResult.value = Event(LoginResult(error = R.string.login_failed))
                    }
                }

            }
        }
    }

    fun loginDataChanged(username: String, password: String) {
        if (!isUserNameValid(username)) {
            _loginForm.value = Event(LoginFormState(usernameError = R.string.invalid_username))
        } else if (!isPasswordValid(password)) {
            _loginForm.value = Event(LoginFormState(passwordError = R.string.invalid_password))
        } else {
            _loginForm.value = Event(LoginFormState(isDataValid = true))
        }
    }

    // A placeholder username validation check
    private fun isUserNameValid(username: String): Boolean {
        return if (username.contains('@')) {
            Patterns.EMAIL_ADDRESS.matcher(username).matches()
        } else {
            username.isNotBlank()
        }
    }

    // A placeholder password validation check
    private fun isPasswordValid(password: String): Boolean {
        return password.length > 5
    }
}
