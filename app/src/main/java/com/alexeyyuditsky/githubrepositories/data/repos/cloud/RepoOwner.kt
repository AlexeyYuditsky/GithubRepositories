package com.alexeyyuditsky.githubrepositories.data.repos.cloud

import com.alexeyyuditsky.githubrepositories.core.Abstract
import com.alexeyyuditsky.githubrepositories.data.repos.ToStringMapper
import com.squareup.moshi.Json

/**
 * {"login":"open-android", "avatar_url":"https://avatars.githubusercontent.com/u/23095877?v=4"}
 * */
data class RepoOwner(
    val login: String,
    @field:Json(name = "avatar_url")
    val avatarUrl: String,
) : Abstract.Object<ToStringMapper, String> {

    override fun map(mapper: ToStringMapper): String {
        return mapper.map(avatarUrl)
    }

}

