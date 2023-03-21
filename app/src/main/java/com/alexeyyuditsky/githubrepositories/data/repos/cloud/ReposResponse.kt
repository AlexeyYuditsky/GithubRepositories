package com.alexeyyuditsky.githubrepositories.data.repos.cloud

/**
 * {"items":"[{...}]"}
 * */
data class ReposResponse(
    val items: List<RepoCloud>,
)