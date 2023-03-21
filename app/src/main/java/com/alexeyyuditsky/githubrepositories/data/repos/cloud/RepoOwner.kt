package com.alexeyyuditsky.githubrepositories.data.repos.cloud

import com.squareup.moshi.Json

/**
 * {"login":"open-android", "avatar_url":"https://avatars.githubusercontent.com/u/23095877?v=4"}
 * */
data class RepoOwner(
    val login: String,
    @field:Json(name = "avatar_url")
    val avatarUrl: String,
)

