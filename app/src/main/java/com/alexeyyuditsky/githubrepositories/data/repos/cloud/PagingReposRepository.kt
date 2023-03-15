package com.alexeyyuditsky.githubrepositories.data.repos.cloud

import androidx.lifecycle.LiveData
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.liveData

const val NETWORK_PAGE_SIZE = 15

interface PagingReposRepository {

    fun fetchRepos(query: String): LiveData<PagingData<RepoCloud>>

    class Base(
        private val service: ReposService,
    ) : PagingReposRepository {

        override fun fetchRepos(query: String): LiveData<PagingData<RepoCloud>> {
            return Pager(
                config = PagingConfig(
                    pageSize = NETWORK_PAGE_SIZE,
                    enablePlaceholders = false
                ),
                pagingSourceFactory = { PagingReposSource(service, query) }
            ).liveData
        }

    }

}