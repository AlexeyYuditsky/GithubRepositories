package com.alexeyyuditsky.githubrepositories.data.repos.cloud

import retrofit2.http.GET
import retrofit2.http.Query

interface ReposService {

    @GET("repositories")
    suspend fun fetchRepos(
        @Query("q") query: String,
    ): ReposResponse

    @GET("repositories")
    suspend fun fetchRepos2(
        @Query("q") query: String,
        @Query("page") pageNumber: Int = 1,
        @Query("per_page") pageSize: Int = QUERY_PAGE_SIZE,
    ): ReposResponse

    companion object {
        const val QUERY_PAGE_SIZE = 20
    }

}