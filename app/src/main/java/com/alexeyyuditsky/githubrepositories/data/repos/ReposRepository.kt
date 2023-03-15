package com.alexeyyuditsky.githubrepositories.data.repos

import androidx.lifecycle.LiveData
import androidx.paging.PagingData
import com.alexeyyuditsky.githubrepositories.data.repos.cloud.RepoCloud
import com.alexeyyuditsky.githubrepositories.data.repos.cloud.ReposCloudDataSource
import com.alexeyyuditsky.githubrepositories.data.repos.cloud.ReposResponse

interface ReposRepository {

    suspend fun fetchRepos(query: String): LiveData<PagingData<RepoCloud>>

    class Base(
        private val cloudDataSource: ReposCloudDataSource,
        private val mapper: ToReposDataMapper,
    ) : ReposRepository {

        override suspend fun fetchRepos(query: String): LiveData<PagingData<RepoCloud>> {
            val response: LiveData<PagingData<RepoCloud>> = cloudDataSource.fetchRepos(query)
            return response
        }

            /*return try {
                val response: ReposResponse = cloudDataSource.fetchRepos(query)
                val reposData: List<RepoData> = mapper.map(response)
                ReposData.Success(reposData)
            } catch (e: Exception) {
                ReposData.Fail(e)
            }
        }*/

    }

}

