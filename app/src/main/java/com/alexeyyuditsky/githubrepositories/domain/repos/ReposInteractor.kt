package com.alexeyyuditsky.githubrepositories.domain.repos

import com.alexeyyuditsky.githubrepositories.core.log
import com.alexeyyuditsky.githubrepositories.data.repos.ReposData
import com.alexeyyuditsky.githubrepositories.data.repos.ReposRepository

interface ReposInteractor {

    suspend fun fetchRepos(query: String)

    class Base(
        private val repository: ReposRepository,
    ) : ReposInteractor {

        override suspend fun fetchRepos(query: String) {
            val reposData: ReposData = repository.fetchRepos(query)
            log(reposData)
        }

    }

}