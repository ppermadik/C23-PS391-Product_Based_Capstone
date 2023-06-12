package com.dicoding.submission.capstone.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.dicoding.submission.capstone.data.repository.Repository
import com.dicoding.submission.capstone.model.DataItem
import com.dicoding.submission.capstone.Result

class NewsViewModel(private val repo: Repository) : ViewModel() {
    fun getListNews(): LiveData<Result<List<DataItem>>> {
        return repo.getListNews()
    }
}