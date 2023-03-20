package com.alexeyyuditsky.githubrepositories.data.issues.cloud

import com.squareup.moshi.Json

/**
 * { "login":"Shriraj29" }
 * */
data class IssueOwner(
    @field:Json(name = "login")
    val user: String,
)
