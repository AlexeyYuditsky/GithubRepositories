package com.alexeyyuditsky.githubrepositories.domain.repos

import com.alexeyyuditsky.githubrepositories.core.ErrorType

sealed class ReposDomain {

    data class Success(
        private val repos: List<RepoDomain>,
    ) : ReposDomain() {

    }

    data class Fail(
        private val errorType: ErrorType,
    ) : ReposDomain() {

    }

}