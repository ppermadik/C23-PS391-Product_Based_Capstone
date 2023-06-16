package com.dicoding.submission.capstone.model

import com.google.gson.annotations.SerializedName

data class AccesResponse(

	@field:SerializedName("messageL")
	val messageL: String,

	@field:SerializedName("data")
	val data: Data,

	@field:SerializedName("status")
	val status: String
)

data class User(

	@field:SerializedName("createdAt")
	val createdAt: String,

	@field:SerializedName("password")
	val password: String,

	@field:SerializedName("fullName")
	val fullName: String,

	@field:SerializedName("id")
	val id: Int,

	@field:SerializedName("email")
	val email: String,

	@field:SerializedName("username")
	val username: String,

	@field:SerializedName("updatedAt")
	val updatedAt: String
)

data class Data(

	@field:SerializedName("user")
	val user: User,

	@field:SerializedName("accesToken")
	val accesToken: String
)
