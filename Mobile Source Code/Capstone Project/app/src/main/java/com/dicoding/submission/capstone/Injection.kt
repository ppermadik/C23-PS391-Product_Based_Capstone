package com.dicoding.submission.capstone

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.preferencesDataStore
import com.dicoding.submission.capstone.data.api.ApiConfig
import com.dicoding.submission.capstone.data.api.ApiConfigAuth
import com.dicoding.submission.capstone.data.api.ApiConfigPredict
import com.dicoding.submission.capstone.data.repository.Repository
import com.dicoding.submission.capstone.model.UserPreference
private val Context.dataStore: DataStore<Preferences> by preferencesDataStore(name = "userPreference")
object Injection {
    fun provideRepository(context: Context): Repository {
        val apiService = ApiConfig.getApiService()
        val apiservice1 = ApiConfigPredict.getApiService()
        val apiConfigAuth = ApiConfigAuth.getApiService()
        val pref = UserPreference.getInstance(context.dataStore)

        return Repository.getInstance(apiService,apiservice1,apiConfigAuth)
    }
    fun providePreference(context: Context): UserPreference {
        return UserPreference.getInstance(context.dataStore)
    }

}