package com.dicoding.submission.capstone.model

import com.google.gson.annotations.SerializedName

data class LoginResponse(

	@field:SerializedName("message")
	val message: String,

	@field:SerializedName("status")
	val status: String,

	@field:SerializedName("Authorization")
	val Authorization: String
)

