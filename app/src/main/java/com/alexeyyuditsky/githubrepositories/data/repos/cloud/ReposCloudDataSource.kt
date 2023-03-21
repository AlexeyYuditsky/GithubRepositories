package com.alexeyyuditsky.githubrepositories.data.repos.cloud

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.alexeyyuditsky.githubrepositories.data.repos.cloud.PagingReposSource.Companion.NETWORK_PAGE_SIZE
import kotlinx.coroutines.flow.Flow

interface ReposCloudDataSource {

    suspend fun fetchRepos(query: String): Flow<PagingData<RepoCloud>>

    class Base(
        private val service: ReposService,
    ) : ReposCloudDataSource {

        override suspend fun fetchRepos(query: String): Flow<PagingData<RepoCloud>> {
            return Pager(
                config = PagingConfig(
                    pageSize = NETWORK_PAGE_SIZE,
                    enablePlaceholders = false
                ),
                pagingSourceFactory = { PagingReposSource(service, query) }
            ).flow
        }

    }

}
