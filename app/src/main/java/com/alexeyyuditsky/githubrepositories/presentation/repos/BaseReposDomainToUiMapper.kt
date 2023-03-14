package com.alexeyyuditsky.githubrepositories.presentation.repos

import com.alexeyyuditsky.githubrepositories.core.ErrorType
import com.alexeyyuditsky.githubrepositories.core.ResourceProvider
import com.alexeyyuditsky.githubrepositories.domain.repos.RepoDomain
import com.alexeyyuditsky.githubrepositories.domain.repos.RepoDomainToUiMapper
import com.alexeyyuditsky.githubrepositories.domain.repos.ReposDomainToUiMapper

class BaseReposDomainToUiMapper(
    private val mapper: RepoDomainToUiMapper,
    resourceProvider: ResourceProvider,
) : ReposDomainToUiMapper(resourceProvider) {

    override fun map(data: List<RepoDomain>): ReposUi {
        val repos: List<RepoUi> = data.map { repoDomain: RepoDomain ->
            repoDomain.map(mapper)
        }
        return ReposUi.Success(repos)
    }

    override fun map(errorType: ErrorType): ReposUi {
        val message = errorMessage(errorType)
        val repoUi = RepoUi.Fail(message)
        return ReposUi.Success(listOf(repoUi))
    }

}