package com.dicoding.submission.capstone.model

import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.booleanPreferencesKey
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.withContext

class UserPreference private constructor(private val dataStore: DataStore<Preferences>) {

    suspend fun getToken(): String {
        return withContext(Dispatchers.IO) {
            val preferences = dataStore.data.first()
            preferences[TOKEN] ?: ""
        }
    }


    suspend fun getSession(): Boolean {
        return withContext(Dispatchers.IO) {
            val preferences = dataStore.data.first()
            preferences[SESSION] ?: false
        }
    }


    suspend fun saveTokenSession(session: Boolean, token: String) {
        withContext(Dispatchers.IO) {
            dataStore.edit { preferences ->
                preferences[TOKEN] = token
                preferences[SESSION] = session
            }
        }
    }

    suspend fun logout() {
        withContext(Dispatchers.IO) {
            dataStore.edit { preferences ->
                preferences[SESSION] = false
                preferences[TOKEN] = ""
            }
        }
    }
    fun getUser(): Flow<User1> {
        return dataStore.data.map { preferences ->
            User1(
                preferences[NAME_KEY] ?: "",
                preferences[EMAIL_KEY] ?: "",
                preferences[TOKEN_KEY] ?: ""
            )
        }
    }

    fun getToken1(): Flow<String> {
        return dataStore.data.map { preference ->
            preference[TOKEN] ?: ""
        }
    }

    companion object {
        @Volatile
        private var INSTANCE: UserPreference? = null
        private val NAME_KEY = stringPreferencesKey("name")
        private val EMAIL_KEY = stringPreferencesKey("email")
        private val TOKEN_KEY = stringPreferencesKey("token")


        private val SESSION = booleanPreferencesKey("state")
        private val TOKEN = stringPreferencesKey("token")

        fun getInstance(dataStore: DataStore<Preferences>): UserPreference {
            return INSTANCE ?: synchronized(this) {
                val instance = UserPreference(dataStore)
                INSTANCE = instance
                instance
            }
        }
    }
}
