package com.alexeyyuditsky.githubrepositories.domain.repos

import androidx.lifecycle.LiveData
import androidx.paging.PagingData
import com.alexeyyuditsky.githubrepositories.data.repos.ReposData
import com.alexeyyuditsky.githubrepositories.data.repos.ReposDataToDomainMapper
import com.alexeyyuditsky.githubrepositories.data.repos.ReposRepository
import com.alexeyyuditsky.githubrepositories.data.repos.cloud.RepoCloud

interface ReposInteractor {

    suspend fun fetchRepos(query:String):LiveData<PagingData<RepoCloud>>
    // suspend fun fetchRepos(query: String): ReposDomain

    class Base(
        private val repository: ReposRepository,
        private val mapper: ReposDataToDomainMapper
    ) : ReposInteractor {

        override suspend fun fetchRepos(query: String): LiveData<PagingData<RepoCloud>> {
            return repository.fetchRepos(query)
        }

        /*override suspend fun fetchRepos(query: String): ReposDomain {
            val reposData: ReposData = repository.fetchRepos(query)
            return reposData.map(mapper)
        }*/

    }

}