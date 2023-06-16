package com.dicoding.submission.capstone.model

import com.google.gson.annotations.SerializedName

data class PredictResponse(

	@field:SerializedName("data")
	val data: List<List<List<Any>>>
)
