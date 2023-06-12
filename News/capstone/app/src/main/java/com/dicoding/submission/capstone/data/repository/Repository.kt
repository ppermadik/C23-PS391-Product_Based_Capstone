package com.dicoding.submission.capstone.data.repository

import androidx.lifecycle.LiveData
import androidx.lifecycle.MediatorLiveData
import com.dicoding.submission.capstone.data.api.ApiService
import com.dicoding.submission.capstone.model.DataItem
import com.dicoding.submission.capstone.Result
import com.dicoding.submission.capstone.model.MainNewsResponse
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class Repository(private val apiService: ApiService) {
    private val listNewsResult = MediatorLiveData<Result<List<DataItem>>>()
    private val getNewsResult = MediatorLiveData<Result<List<DataItem>>>()

    fun getListNews(): LiveData<Result<List<DataItem>>> {
        listNewsResult.value = Result.Loading

        val client = apiService.getNews()
        client.enqueue(object : Callback<MainNewsResponse> {
            override fun onResponse(
                call: Call<MainNewsResponse>,
                response: Response<MainNewsResponse>
            ) {
                if (response.isSuccessful) {
                    val newsData = response.body()?.data
                    if (newsData != null) {
                        listNewsResult.value = Result.Success(newsData)
                    } else {
                        listNewsResult.value = Result.Error("Empty news data")
                    }
                } else {
                    listNewsResult.value = Result.Error("Failed to fetch news")
                }
            }

            override fun onFailure(call: Call<MainNewsResponse>, t: Throwable) {
                listNewsResult.value = Result.Error(t.message ?: "Unknown error occurred")
            }
        })

        return listNewsResult
    }
    fun getNews(username:String): MediatorLiveData<Result<List<DataItem>>> {
        getNewsResult.value = Result.Loading
        val client = apiService.getDetaiUser(username)
        client.enqueue(object :Callback<MainNewsResponse>{
            override fun onResponse(
                call: Call<MainNewsResponse>,
                response: Response<MainNewsResponse>
            ) {
                if (response.isSuccessful) {
                    val newsData = response.body()?.data
                    if (newsData != null) {
                        getNewsResult.value = Result.Success(newsData)
                    } else {
                        getNewsResult.value = Result.Error("Empty news data")
                    }
                } else {
                    getNewsResult.value = Result.Error("Failed to fetch news")
                }

            }

            override fun onFailure(call: Call<MainNewsResponse>, t: Throwable) {
                getNewsResult.value = Result.Error(t.message ?: "Unknown error occurred")
            }

        })
        return getNewsResult
    }

    companion object {
        @Volatile
        private var INSTANCE: Repository? = null

        fun getInstance(apiService: ApiService): Repository =
            INSTANCE ?: synchronized(this) {
                INSTANCE ?: Repository(apiService)
            }.also { INSTANCE = it }
    }
}
