package com.alexeyyuditsky.githubrepositories.presentation.repos

interface RepoUiToTextMapper {
    fun map(name: String, fullName: String? = null, avatarUrl: String? = null, description: String? = null)
}