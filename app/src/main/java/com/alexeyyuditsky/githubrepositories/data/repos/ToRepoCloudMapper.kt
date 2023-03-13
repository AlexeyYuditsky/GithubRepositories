package com.alexeyyuditsky.githubrepositories.data.repos

import com.alexeyyuditsky.githubrepositories.data.repos.cloud.RepoCloud

interface ToRepoCloudMapper {

    fun map(reposCloud: List<RepoCloud>): List<RepoCloud>

    class Base : ToRepoCloudMapper {
        override fun map(reposCloud: List<RepoCloud>): List<RepoCloud> {
            return reposCloud
        }
    }

}