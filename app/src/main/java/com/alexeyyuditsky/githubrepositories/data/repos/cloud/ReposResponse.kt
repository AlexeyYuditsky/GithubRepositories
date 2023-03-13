package com.alexeyyuditsky.githubrepositories.data.repos.cloud

import com.alexeyyuditsky.githubrepositories.data.repos.ToRepoCloudMapper

/**
 * {"items":"[]"}
 * */
data class ReposResponse(
    private val items: List<RepoCloud>,
) {

    fun map(mapper: ToRepoCloudMapper): List<RepoCloud> {
        return mapper.map(items)
    }

}