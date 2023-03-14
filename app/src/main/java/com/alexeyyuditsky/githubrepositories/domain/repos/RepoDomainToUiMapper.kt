package com.alexeyyuditsky.githubrepositories.domain.repos

import com.alexeyyuditsky.githubrepositories.core.Abstract

interface RepoDomainToUiMapper : Abstract.Mapper {
    fun map(name: String, fullName: String, avatarUrl: String, description: String): RepoDomain
}