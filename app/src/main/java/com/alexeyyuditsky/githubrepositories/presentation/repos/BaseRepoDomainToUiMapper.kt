package com.alexeyyuditsky.githubrepositories.presentation.repos

import com.alexeyyuditsky.githubrepositories.domain.repos.RepoDomainToUiMapper

class BaseRepoDomainToUiMapper : RepoDomainToUiMapper {

    override fun map(name: String, fullName: String, avatarUrl: String, description: String): RepoUi {
        return RepoUi.Base(name, fullName, avatarUrl, description)
    }

}