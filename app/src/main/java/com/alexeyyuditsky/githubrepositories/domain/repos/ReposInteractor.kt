package com.alexeyyuditsky.githubrepositories.domain.repos

import com.alexeyyuditsky.githubrepositories.data.repos.ReposData
import com.alexeyyuditsky.githubrepositories.data.repos.ReposDataToDomainMapper
import com.alexeyyuditsky.githubrepositories.data.repos.ReposRepository

interface ReposInteractor {

    suspend fun fetchRepos(query: String): ReposDomain

    class Base(
        private val repository: ReposRepository,
        private val mapper: ReposDataToDomainMapper
    ) : ReposInteractor {

        override suspend fun fetchRepos(query: String): ReposDomain {
            val reposData: ReposData = repository.fetchRepos(query)
            return reposData.map(mapper)
        }

    }

}