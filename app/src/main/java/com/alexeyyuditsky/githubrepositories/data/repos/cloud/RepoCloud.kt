package com.alexeyyuditsky.githubrepositories.data.repos.cloud

import com.alexeyyuditsky.githubrepositories.data.repos.RepoData
import com.alexeyyuditsky.githubrepositories.data.repos.ToRepoDataMapper
import com.alexeyyuditsky.githubrepositories.data.repos.ToStringMapper
import com.squareup.moshi.Json

/**
 * {"name":"Android", "full_name":"open-android/Android", "owner":"{...}" "description":"GitHub上最火的Android"}
 * */
data class RepoCloud(
    private val name: String,
    @field:Json(name = "full_name")
    private val fullName: String,
    private val owner: RepoAvatar,
    private val description: String?,
) {

    fun map(toStringMapper: ToStringMapper, toRepoDataMapper: ToRepoDataMapper): RepoData {
        val avatarUrl = owner.map(toStringMapper)
        return toRepoDataMapper.map(name, fullName, avatarUrl, description ?: "")
    }

}