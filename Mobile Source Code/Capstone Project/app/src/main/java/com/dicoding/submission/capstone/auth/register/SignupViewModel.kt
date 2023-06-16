package com.dicoding.submission.capstone.auth.register

import androidx.lifecycle.ViewModel
import com.dicoding.submission.capstone.data.repository.Repository

class SignupViewModel(private val repository: Repository) : ViewModel() {
    fun sendRegisterForm(username: String, email: String, password: String,fullname:String)=
        repository.submitRegisterForm(username, email, password,fullname)
}
