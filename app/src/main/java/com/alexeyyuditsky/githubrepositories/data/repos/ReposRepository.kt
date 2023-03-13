package com.alexeyyuditsky.githubrepositories.data.repos

import com.alexeyyuditsky.githubrepositories.data.repos.cloud.ReposCloudDataSource
import com.alexeyyuditsky.githubrepositories.data.repos.cloud.ReposResponse

interface ReposRepository {

    suspend fun fetchRepos(query: String): ReposData

    class Base(
        private val cloudDataSource: ReposCloudDataSource,
        private val mapper: ToReposDataMapper,
    ) : ReposRepository {

        override suspend fun fetchRepos(query: String): ReposData {
            return try {
                val response: ReposResponse = cloudDataSource.fetchRepos(query)
                val reposData: List<RepoData> = mapper.map(response)
                ReposData.Success(reposData)
            } catch (e: Exception) {
                ReposData.Fail(e)
            }
        }

    }

}

