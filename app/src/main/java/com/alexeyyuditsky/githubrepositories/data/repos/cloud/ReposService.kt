package com.alexeyyuditsky.githubrepositories.data.repos.cloud

import retrofit2.http.GET
import retrofit2.http.Query

interface ReposService {

    @GET("repositories")
    suspend fun fetchRepos(
        @Query("q") query: String,
    ): ReposResponse

}