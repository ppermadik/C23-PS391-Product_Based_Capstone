package com.dicoding.submission.capstone.preditc

import androidx.lifecycle.*
import com.dicoding.submission.capstone.Result
import com.dicoding.submission.capstone.data.repository.Repository
import com.dicoding.submission.capstone.model.PredictResponse
import com.dicoding.submission.capstone.model.UserPreference
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class predictViewmodel(
    private val repository: Repository,
    private val pref: UserPreference
) : ViewModel() {

    fun getListPredict(token: String): LiveData<Result<List<PredictResponse>>> {
        return repository.getPredict1(token)
    }

    private fun getListPredict1(token: String): LiveData<Result<List<PredictResponse>>> {
        return repository.getPredict1(token)
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

    private fun getToken(): LiveData<String> {
        return pref.getToken1().asLiveData()
    }

    val listStoryByToken: LiveData<Result<List<PredictResponse>>> =
        getToken().switchMap { token ->
            getListPredict1("Bearer $token")
        }
}
