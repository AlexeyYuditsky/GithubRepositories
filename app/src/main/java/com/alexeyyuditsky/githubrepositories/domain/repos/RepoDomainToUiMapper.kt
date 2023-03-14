package com.alexeyyuditsky.githubrepositories.domain.repos

import com.alexeyyuditsky.githubrepositories.core.Abstract
import com.alexeyyuditsky.githubrepositories.presentation.repos.RepoUi

interface RepoDomainToUiMapper : Abstract.Mapper {
    fun map(name: String, fullName: String, avatarUrl: String, description: String): RepoUi
}