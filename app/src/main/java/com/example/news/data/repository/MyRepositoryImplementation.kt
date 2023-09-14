package com.example.news.data.repository

import com.example.news.data.remote.MyApi
import com.example.news.domain.repository.MyRepository
import com.example.news.model.NewsResponse
import retrofit2.Response

class MyRepositoryImplementation(
    private val api: MyApi,
) : MyRepository {
    override suspend fun doNetworkCal(): Response<NewsResponse> {
        return api.getArticlesData(
            "tesla",
            "2023-08-13",
            "publishedAt",
            "9f91aae6098f40989aa0538afd46d9c1",
        )
    }
}
