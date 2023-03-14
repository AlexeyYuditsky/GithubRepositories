package com.alexeyyuditsky.githubrepositories.data.repos

import com.alexeyyuditsky.githubrepositories.core.Abstract
import com.alexeyyuditsky.githubrepositories.domain.repos.RepoDomain

interface RepoDataToDomainMapper : Abstract.Mapper {
    fun map(name: String, fullName: String, avatarUrl: String, description: String): RepoDomain
}