package com.dicoding.submission.capstone

import android.content.Context
import com.dicoding.submission.capstone.data.api.ApiConfig
import com.dicoding.submission.capstone.data.repository.Repository

object Injection {
    fun provideRepository(context: Context): Repository {
        val apiService = ApiConfig.getApiService()

        return Repository.getInstance(apiService)
    }

}