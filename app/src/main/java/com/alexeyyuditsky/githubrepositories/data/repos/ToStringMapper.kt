package com.alexeyyuditsky.githubrepositories.data.repos

interface ToStringMapper {

    fun map(avatarUrl: String): String

    class Base : ToStringMapper {
        override fun map(avatarUrl: String): String {
            return avatarUrl
        }
    }

}