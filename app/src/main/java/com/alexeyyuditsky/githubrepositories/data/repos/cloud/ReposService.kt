package com.alexeyyuditsky.githubrepositories.data.repos.cloud

import retrofit2.http.GET
import retrofit2.http.Query

interface ReposService {

    @GET("search/repositories")
    suspend fun fetchRepos(
        @Query("q") query: String,
        @Query("page") start: Int = QUERY_PAGE_START,
        @Query("per_page") limit: Int = QUERY_PAGE_SIZE,
    ): ReposResponse

    private companion object {
        const val QUERY_PAGE_START = 1
        const val QUERY_PAGE_SIZE = 20
    }

}