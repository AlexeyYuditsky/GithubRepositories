package com.alexeyyuditsky.githubrepositories.domain.repos

import com.alexeyyuditsky.githubrepositories.data.repos.RepoDataToDomainMapper

class BaseRepoDataToDomainMapper : RepoDataToDomainMapper {

    override fun map(name: String, fullName: String, avatarUrl: String, description: String): RepoDomain {
        return RepoDomain.Base(name, fullName, avatarUrl, description)
    }

}