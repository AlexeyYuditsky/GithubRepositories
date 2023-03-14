package com.alexeyyuditsky.githubrepositories.domain.repos

sealed class RepoDomain {

    data class Base(
        private val name: String,
        private val fullName: String,
        private val avatarUrl: String,
        private val description: String,
    ) : RepoDomain() {
        
    }

}
