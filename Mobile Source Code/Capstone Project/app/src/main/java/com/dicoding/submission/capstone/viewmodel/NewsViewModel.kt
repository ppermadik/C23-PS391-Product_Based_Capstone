package com.dicoding.submission.capstone.viewmodel

import androidx.lifecycle.*
import com.dicoding.submission.capstone.Result
import com.dicoding.submission.capstone.data.repository.Repository
import com.dicoding.submission.capstone.model.DataItem
import com.dicoding.submission.capstone.model.UserPreference
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class NewsViewModel(private val repo: Repository, private val pref:UserPreference) : ViewModel() {
    private val _session = MutableLiveData<Boolean>()
    val session: LiveData<Boolean> = _session
    private val _listStory = MutableLiveData<List<DataItem>>()
    val listStory: LiveData<List<DataItem>> = _listStory
    private fun getListNews(token:String): LiveData<Result<List<DataItem>>> {
        return repo.getListNews(token)
    }
    private fun getToken(): LiveData<String> {
        return pref.getToken1().asLiveData()
    }
    fun getListStoryByToken(): LiveData<Result<List<DataItem>>> =
        getToken().switchMap {token->
            getListNews("Bearer $token")
        }
    fun getSession() {
        viewModelScope.launch {
            val session = withContext(Dispatchers.IO) {
                pref.getSession()
            }
            _session.value = session
        }
    }
    fun logout(){
        viewModelScope.launch {
            withContext(Dispatchers.IO){
                pref.logout()
            }
            getSession()
        }
    }
    fun setListStory(listStory: List<DataItem>) {
        _listStory.value = listStory
    }
}