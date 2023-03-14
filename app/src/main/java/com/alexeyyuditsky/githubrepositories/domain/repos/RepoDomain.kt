package com.alexeyyuditsky.githubrepositories.domain.repos

import com.alexeyyuditsky.githubrepositories.core.Abstract
import com.alexeyyuditsky.githubrepositories.presentation.repos.RepoUi

sealed class RepoDomain : Abstract.Object<RepoDomainToUiMapper, RepoUi> {

    data class Base(
        private val name: String,
        private val fullName: String,
        private val avatarUrl: String,
        private val description: String,
    ) : RepoDomain() {

        override fun map(mapper: RepoDomainToUiMapper): RepoUi {
            return mapper.map(name, fullName, avatarUrl, description)
        }

    }

}
