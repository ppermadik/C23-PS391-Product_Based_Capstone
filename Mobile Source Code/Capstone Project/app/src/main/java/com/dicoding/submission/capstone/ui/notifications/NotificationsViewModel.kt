package com.dicoding.submission.capstone.ui.notifications

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.dicoding.submission.capstone.model.UserPreference
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class NotificationsViewModel (private val pref:UserPreference): ViewModel() {

    private val _text = MutableLiveData<String>().apply {
        value = "This is notifications Fragment"
    }
    val text: LiveData<String> = _text
    fun logout(){
        viewModelScope.launch {
            withContext(Dispatchers.IO){
                pref.logout()
            }
            getSession()
        }
    }
    private val _session = MutableLiveData<Boolean>()
    val session: LiveData<Boolean> = _session
    fun getSession() {
        viewModelScope.launch {
            val session = withContext(Dispatchers.IO) {
                pref.getSession()
            }
            _session.value = session
        }
    }
}