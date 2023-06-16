package com.dicoding.submission.capstone.model

import com.google.gson.annotations.SerializedName

data class MainNewsResponse(

	@field:SerializedName("data")
	val data: List<DataItem>
)

data class DataItem(

	@field:SerializedName("date")
	val date: String,

	@field:SerializedName("imageUrl")
	val imageUrl: String,

	@field:SerializedName("title")
	val title: String,

	@field:SerializedName("newsLink")
	val newsLink: String,

	@field:SerializedName("content")
	val content: String
)
