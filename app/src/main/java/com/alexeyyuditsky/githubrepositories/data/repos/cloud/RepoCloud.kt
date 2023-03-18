package com.alexeyyuditsky.githubrepositories.data.repos.cloud

import com.alexeyyuditsky.githubrepositories.data.repos.RepoData
import com.alexeyyuditsky.githubrepositories.data.repos.ToRepoDataMapper
import com.alexeyyuditsky.githubrepositories.data.repos.ToStringMapper
import com.squareup.moshi.Json

/**
 * {"id":"82128465", name":"Android", "owner":"{...}" "description":"GitHub上最火的Android"}
 * */
data class RepoCloud(
    val id: Long,
    @field:Json(name = "name")
    val repository: String,
    val owner: RepoOwner,
    val description: String?,
) {

    fun map(toStringMapper: ToStringMapper, toRepoDataMapper: ToRepoDataMapper): RepoData {
        val avatarUrl = owner.map(toStringMapper)
        return toRepoDataMapper.map(repository, avatarUrl, description ?: "")
    }

}