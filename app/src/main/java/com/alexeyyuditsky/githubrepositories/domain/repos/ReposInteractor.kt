package com.alexeyyuditsky.githubrepositories.domain.repos

import androidx.paging.PagingData
import com.alexeyyuditsky.githubrepositories.data.repos.ReposDataToDomainMapper
import com.alexeyyuditsky.githubrepositories.data.repos.ReposRepository
import com.alexeyyuditsky.githubrepositories.data.repos.cloud.RepoCloud
import kotlinx.coroutines.flow.Flow

interface ReposInteractor {

    suspend fun fetchRepos(query: String): Flow<PagingData<RepoCloud>>

    class Base(
        private val repository: ReposRepository,
        private val mapper: ReposDataToDomainMapper,
    ) : ReposInteractor {

        override suspend fun fetchRepos(query: String): Flow<PagingData<RepoCloud>> {
            return repository.fetchRepos(query)
        }

    }

}