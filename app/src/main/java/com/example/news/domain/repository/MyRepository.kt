package com.example.news.domain.repository

import com.example.news.model.NewsResponse
import retrofit2.Response

interface MyRepository {
    suspend fun doNetworkCal(): Response<NewsResponse>
}
