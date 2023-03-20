package com.alexeyyuditsky.githubrepositories.data.issues.cloud

import com.squareup.moshi.Json

/**
 * { "id":"1628832658","title":"Create JSONPROJECT", "user":{...}, "created_at":"2022-11-28T22:39:59Z", "updated_at":"2023-02-25T08:43:10Z" }
 * */
data class IssueCloud(
    val id: Long,
    val title: String,
    val user: IssueOwner,
    @field:Json(name = "created_at")
    val createdAt: String,
    @field:Json(name = "updated_at")
    val updatedAt: String,
)