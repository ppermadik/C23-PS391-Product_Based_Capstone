package com.dicoding.logintest2

import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.POST

interface ApiService {
    @POST("user/register")
    fun registerUser(@Body user: User): Call<ResponseBody>

    @POST("user/login")
    fun loginUser(@Body credentials: Credentials): Call<ResponseBody>
}