package com.alexeyyuditsky.githubrepositories.data.repos

import com.alexeyyuditsky.githubrepositories.core.Abstract
import com.alexeyyuditsky.githubrepositories.domain.repos.RepoDomain

data class RepoData(
    private val name: String,
    private val fullName: String,
    private val avatarUrl: String,
    private val description: String,
) : Abstract.Object<RepoDataToDomainMapper, RepoDomain> {

    override fun map(mapper: RepoDataToDomainMapper): RepoDomain {
        return mapper.map(name, fullName, avatarUrl, description)
    }

}