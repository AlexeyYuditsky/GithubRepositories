package com.alexeyyuditsky.githubrepositories.data.repos.cloud

import androidx.paging.*
import com.alexeyyuditsky.githubrepositories.data.repos.cloud.PagingReposSource.Companion.NETWORK_PAGE_SIZE
import kotlinx.coroutines.flow.Flow

interface PagingReposRepository {

    fun fetchRepos(query: String): Flow<PagingData<RepoCloud>>

    class Base(
        private val service: ReposService,
    ) : PagingReposRepository {

        override fun fetchRepos(query: String): Flow<PagingData<RepoCloud>> {
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