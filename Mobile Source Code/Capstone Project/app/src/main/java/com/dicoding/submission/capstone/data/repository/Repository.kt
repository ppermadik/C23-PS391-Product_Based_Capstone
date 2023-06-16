package com.dicoding.submission.capstone.data.repository

import androidx.lifecycle.LiveData
import androidx.lifecycle.MediatorLiveData
import androidx.lifecycle.MutableLiveData
import com.dicoding.submission.capstone.Result
import com.dicoding.submission.capstone.data.api.ApiService
import com.dicoding.submission.capstone.model.*
import org.json.JSONObject
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class Repository(
    private val apiService: ApiService,
    private val apiservice1: ApiService,
    private val apiservice2: ApiService
) {
    private val registerResult = MediatorLiveData<Result<LoginResponse>>()
    private val loginResult = MediatorLiveData<Result<AccesResponse>>()
    private val _isLoading = MutableLiveData<Boolean>()
    private val listNewsResult = MediatorLiveData<Result<List<DataItem>>>()
    private val listResultPredict = MediatorLiveData<Result<List<PredictResponse>>>()


    fun getListNews(token: String): LiveData<Result<List<DataItem>>> {
        listNewsResult.value = Result.Loading

        val client = apiService.getNews(token)
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
    fun submitLoginForm(email: String, password: String): MediatorLiveData<Result<AccesResponse>> {
        loginResult.value = Result.Loading
        val client = apiservice2.login(email, password)
        client.enqueue(object : Callback<AccesResponse> {
            override fun onResponse(
                call: Call<AccesResponse>,
                response: Response<AccesResponse>
            ) {
                if (response.isSuccessful && response.body() != null) {
                    loginResult.value = Result.Success(response.body()!!)
                } else if (!response.isSuccessful) {
                    val errorResponse = response.errorBody()?.string()
                    val message = JSONObject(errorResponse.toString()).getString("message")
                    loginResult.value = Result.Error(message)
                }
            }

            override fun onFailure(call: Call<AccesResponse>, t: Throwable) {
                loginResult.value = Result.Error(t.message.toString())
            }
        })
        return loginResult
    }
    fun submitRegisterForm(username: String, email: String, password: String,fullname:String): MediatorLiveData<Result<LoginResponse>> {
        _isLoading.value = true
        val response = apiservice2.register(username,email,password,fullname)
        response.enqueue(object : Callback<LoginResponse>{
            override fun onResponse(
                call: Call<LoginResponse>,
                response: Response<LoginResponse>
            ) {
                if (response.isSuccessful && response.body() != null){
                    registerResult.value = Result.Success(response.body()!!)
                }else if (!response.isSuccessful){
                    val errorResponse = response.errorBody()?.string()
                    val message = JSONObject(errorResponse.toString()).getString("message")
                    registerResult.value = Result.Error(message)
                }
            }

            override fun onFailure(call: Call<LoginResponse>, t: Throwable) {
                registerResult.value = Result.Error(t.message.toString())
            }

        })
        return registerResult
    }

    fun getPredict1(token: String): LiveData<Result<List<PredictResponse>>> {
        val listResultPredict = MutableLiveData<Result<List<PredictResponse>>>()
        listResultPredict.value = Result.Loading

        val client = apiservice1.getPredict1(token)
        client.enqueue(object : Callback<PredictResponse> {
            override fun onResponse(
                call: Call<PredictResponse>,
                response: Response<PredictResponse>
            ) {
                if (response.isSuccessful) {
                    val predictResponse = response.body()
                    if (predictResponse != null) {
                        val predictionList = predictResponse.data

                        if (predictionList.isNotEmpty()) {
                            val firstLevelList = predictionList[0]

                            if (firstLevelList.isNotEmpty()) {
                                val secondLevelList = firstLevelList[0]

                                if (secondLevelList.isNotEmpty()) {
                                    val value = secondLevelList[0]

                                    if (value is Double) {
                                        val specificData = value
                                        listResultPredict.value = Result.Success(listOf(predictResponse))
                                    } else {
                                        listResultPredict.value = Result.Error("Invalid data format")
                                    }
                                } else {
                                    listResultPredict.value = Result.Error("Empty second level list")
                                }
                            } else {
                                listResultPredict.value = Result.Error("Empty first level list")
                            }
                        } else {
                            listResultPredict.value = Result.Error("Empty prediction list")
                        }
                    } else {
                        listResultPredict.value = Result.Error("Empty response body")
                    }
                } else {
                    listResultPredict.value = Result.Error("Failed to fetch news")
                }
            }

            override fun onFailure(call: Call<PredictResponse>, t: Throwable) {
                // Handle the network failure
                listResultPredict.value = Result.Error("Network request failed: ${t.message}")
            }
        })

        return listResultPredict
    }


    companion object {
        @Volatile
        private var INSTANCE: Repository? = null

        fun getInstance(apiService: ApiService, apiservice1: ApiService, apiConfigAuth: ApiService): Repository =
            INSTANCE ?: synchronized(this) {
                INSTANCE ?: Repository(apiService,apiservice1,apiConfigAuth)
            }.also { INSTANCE = it }
    }
}
