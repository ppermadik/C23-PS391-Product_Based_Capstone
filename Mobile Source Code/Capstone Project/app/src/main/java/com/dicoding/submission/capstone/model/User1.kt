package com.dicoding.submission.capstone.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class User1(
    val name: String,
    val email: String,
    val token: String
) : Parcelable