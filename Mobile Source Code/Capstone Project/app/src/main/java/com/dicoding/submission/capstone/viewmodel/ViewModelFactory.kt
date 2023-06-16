package com.dicoding.submission.capstone.viewmodel

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.dicoding.submission.capstone.FrontViewModel
import com.dicoding.submission.capstone.Injection
import com.dicoding.submission.capstone.auth.login.LoginViewModel
import com.dicoding.submission.capstone.auth.register.SignupViewModel
import com.dicoding.submission.capstone.data.repository.Repository
import com.dicoding.submission.capstone.model.UserPreference
import com.dicoding.submission.capstone.preditc.predictViewmodel
import com.dicoding.submission.capstone.ui.notifications.NotificationsViewModel

class ViewModelFactory private constructor(
    private val repository: Repository,
    private val pref: UserPreference,
) : ViewModelProvider.NewInstanceFactory() {
    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return when {
            modelClass.isAssignableFrom(SignupViewModel::class.java) -> {
                SignupViewModel(repository) as T
            }
            modelClass.isAssignableFrom(LoginViewModel::class.java) -> {
                LoginViewModel(pref,repository) as T
            }
            modelClass.isAssignableFrom(NewsViewModel::class.java) -> {
                NewsViewModel(repository,pref) as T
            }
            modelClass.isAssignableFrom(FrontViewModel::class.java) -> {
                FrontViewModel(pref) as T
            }
            modelClass.isAssignableFrom(predictViewmodel::class.java)->{
                predictViewmodel(repository,pref) as T
            }
            modelClass.isAssignableFrom(NotificationsViewModel::class.java) -> {
                NotificationsViewModel(pref) as T
            }

            else -> throw IllegalArgumentException("Unknown ViewModel class: " + modelClass.name)
        }
    }
    companion object {
        @Volatile
        private var instance: ViewModelFactory? = null

        fun getInstance(context: Context): ViewModelFactory =
            instance ?: synchronized(this) {
                instance ?: ViewModelFactory(
                    Injection.provideRepository(context),
                    Injection.providePreference(context)
                )
            }.also { instance = it }
    }
}