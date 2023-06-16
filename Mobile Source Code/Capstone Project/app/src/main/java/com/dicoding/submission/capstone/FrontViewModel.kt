package com.dicoding.submission.capstone

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.dicoding.submission.capstone.model.UserPreference
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class FrontViewModel(private val pref: UserPreference) : ViewModel() {
    private val _sessionLiveData = MutableLiveData<Boolean>()
    val sessionLiveData: LiveData<Boolean> = _sessionLiveData

    fun getSession() {
        viewModelScope.launch {
            val session = withContext(Dispatchers.IO) {
                pref.getSession()
            }
            _sessionLiveData.postValue(session)
        }
    }


}