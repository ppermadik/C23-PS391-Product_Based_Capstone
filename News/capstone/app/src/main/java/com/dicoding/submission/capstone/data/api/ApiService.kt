package com.dicoding.submission.capstone.data.api

import com.dicoding.submission.capstone.model.MainNewsResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Path

interface ApiService {
    @GET("detik")
    fun getNews(): Call<MainNewsResponse>

    @GET("detik/{username}")
    fun getDetaiUser(
        @Path("username") username: String,
    ): Call<MainNewsResponse>
}
