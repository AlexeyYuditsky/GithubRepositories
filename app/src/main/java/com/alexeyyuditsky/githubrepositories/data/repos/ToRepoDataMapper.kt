package com.alexeyyuditsky.githubrepositories.data.repos

import com.alexeyyuditsky.githubrepositories.core.Abstract

interface ToRepoDataMapper : Abstract.Mapper {

    fun map(name: String, fullName: String, avatarUrl: String, description: String): RepoData

    class Base : ToRepoDataMapper {
        override fun map(name: String, fullName: String, avatarUrl: String, description: String): RepoData {
            return RepoData(name, fullName, avatarUrl, description)
        }
    }

}