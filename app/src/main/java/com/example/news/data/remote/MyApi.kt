package com.example.news.data.remote

import com.example.news.model.NewsResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface MyApi {
    @GET("v2/everything")
    suspend fun getArticlesData(
        @Query("q") q: String?,
        @Query("from") from: String?,
        @Query("sortBy") publishedAt: String?,
        @Query("apiKey") apiKey: String?,
    ): Response<NewsResponse>
}
