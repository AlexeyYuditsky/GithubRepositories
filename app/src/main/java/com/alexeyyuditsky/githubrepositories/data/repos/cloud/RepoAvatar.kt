package com.alexeyyuditsky.githubrepositories.data.repos.cloud

import com.alexeyyuditsky.githubrepositories.data.repos.ToStringMapper
import com.squareup.moshi.Json

/**
 * {"avatar_url":"https://avatars.githubusercontent.com/u/23095877?v=4"}
 * */
data class RepoAvatar(
    @field:Json(name = "avatar_url")
    private val avatarUrl: String,
) {

    fun map(mapper: ToStringMapper): String {
        return mapper.map(avatarUrl)
    }

}

