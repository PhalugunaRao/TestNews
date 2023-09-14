package com.example.news

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.news.domain.repository.MyRepository
import com.example.news.model.NewsResponse
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import retrofit2.Response
import javax.inject.Inject


@HiltViewModel
class MyViewModel @Inject constructor(
    private val repository: MyRepository
):ViewModel() {
    val articleListData : MutableLiveData<Response<NewsResponse>> = MutableLiveData()
    fun getArticleViewModel(){
        viewModelScope.launch {
            articleListData.value = repository.doNetworkCal()
        }
    }
}