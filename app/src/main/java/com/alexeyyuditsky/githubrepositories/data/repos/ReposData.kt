package com.alexeyyuditsky.githubrepositories.data.repos

import com.alexeyyuditsky.githubrepositories.domain.repos.ReposDomain

sealed class ReposData {

    abstract fun map(mapper: ReposDataToDomainMapper): ReposDomain

    data class Success(
        private val repos: List<RepoData>,
    ) : ReposData() {
        override fun map(mapper: ReposDataToDomainMapper): ReposDomain {
            return mapper.map(repos)
        }
    }

    data class Fail(
        private val e: Exception,
    ) : ReposData() {
        override fun map(mapper: ReposDataToDomainMapper): ReposDomain {
            return mapper.map(e)
        }
    }

}
