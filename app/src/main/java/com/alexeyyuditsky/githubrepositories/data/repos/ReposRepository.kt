package com.alexeyyuditsky.githubrepositories.data.repos

import androidx.paging.PagingData
import com.alexeyyuditsky.githubrepositories.data.repos.cloud.RepoCloud
import com.alexeyyuditsky.githubrepositories.data.repos.cloud.ReposCloudDataSource
import kotlinx.coroutines.flow.Flow

interface ReposRepository {

    suspend fun fetchRepos(query: String): Flow<PagingData<RepoCloud>>

    class Base(
        private val cloudDataSource: ReposCloudDataSource,
       // private val mapper: ToReposDataMapper,
    ) : ReposRepository {

        override suspend fun fetchRepos(query: String): Flow<PagingData<RepoCloud>> {
            val response: Flow<PagingData<RepoCloud>> = cloudDataSource.fetchRepos(query)
            return response
        }

    }

}

