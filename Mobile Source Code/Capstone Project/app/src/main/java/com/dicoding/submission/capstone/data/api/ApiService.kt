package com.dicoding.submission.capstone.data.api

import com.dicoding.submission.capstone.model.*
import org.json.JSONObject
import retrofit2.Call
import retrofit2.http.*

interface ApiService {
    @GET("news")
    fun getNews(
        @Header("Authorization") authorization: String
    ): Call<MainNewsResponse>

    @FormUrlEncoded
    @POST("user/login")
    fun login(
        @Field("username") username: String,
        @Field("password") password: String,

    ): Call<AccesResponse>
    @FormUrlEncoded
    @POST("user/register")
    fun register(
        @Field("username") username: String,
        @Field("fullName")fullName: String,
        @Field("email") email: String,
        @Field("password") password: String
    ): Call<LoginResponse>
    @GET("fishprice")
    fun getPredict(
    ): Call<PredictResponse>
    @GET("/prediction/fish")
    fun getPredict1(
        @Header("Authorization") authorization: String
    ): Call<PredictResponse>
}
