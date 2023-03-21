package com.alexeyyuditsky.githubrepositories.presentation.repos

data class RepoUi(
    val id: Long,
    val avatarUrl: String,
    val login: String,
    val repository: String,
    val description: String,
)