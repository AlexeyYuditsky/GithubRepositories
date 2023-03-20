package com.alexeyyuditsky.githubrepositories.presentation.issues

interface ToTextMapper {
    fun map(title: String, user: String, created: String, updated: String)
}