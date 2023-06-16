package com.dicoding.submission.capstone.auth.login

import android.widget.Toast
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.dicoding.submission.capstone.data.repository.Repository
import com.dicoding.submission.capstone.model.UserPreference
import kotlinx.coroutines.launch

class LoginViewModel(private val pref: UserPreference, private val repository: Repository) : ViewModel() {

    fun sendLoginForm(username: String, password: String) =
        repository.submitLoginForm(username, password)

    fun login(token: String?) {
        if (token != null) {
            viewModelScope.launch {
                pref.saveTokenSession(true, token)
                saveLoginStatus(true, token)
            }
        }
    }
    private fun saveLoginStatus(isLoggedIn: Boolean,token: String) {
        viewModelScope.launch {
            pref.saveTokenSession(isLoggedIn, token)
        }
    }

}
