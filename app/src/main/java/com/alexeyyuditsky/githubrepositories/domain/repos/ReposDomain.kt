package com.alexeyyuditsky.githubrepositories.domain.repos

import com.alexeyyuditsky.githubrepositories.core.Abstract
import com.alexeyyuditsky.githubrepositories.core.ErrorType
import com.alexeyyuditsky.githubrepositories.presentation.repos.ReposUi

sealed class ReposDomain : Abstract.Object<ReposDomainToUiMapper, ReposUi> {

    data class Success(
        private val repos: List<RepoDomain>,
    ) : ReposDomain() {

        override fun map(mapper: ReposDomainToUiMapper): ReposUi {
            return mapper.map(repos)
        }

    }

    data class Fail(
        private val errorType: ErrorType,
    ) : ReposDomain() {

        override fun map(mapper: ReposDomainToUiMapper): ReposUi {
           return mapper.map(errorType)
        }

    }

}