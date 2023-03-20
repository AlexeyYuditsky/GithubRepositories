package com.alexeyyuditsky.githubrepositories.data.issues.cloud

import retrofit2.http.GET
import retrofit2.http.Path

interface IssuesService {

    @GET("repos/{user}/{repo}/issues")
    suspend fun fetchIssues(
        @Path("user") user: String,
        @Path("repo") repo: String,
    ): List<IssueCloud>

}