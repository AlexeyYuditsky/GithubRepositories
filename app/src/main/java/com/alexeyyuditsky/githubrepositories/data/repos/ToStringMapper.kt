package com.alexeyyuditsky.githubrepositories.data.repos

import com.alexeyyuditsky.githubrepositories.core.Abstract

interface ToStringMapper : Abstract.Mapper {

    fun map(avatarUrl: String): String

    class Base : ToStringMapper {
        override fun map(avatarUrl: String): String {
            return avatarUrl
        }
    }

}