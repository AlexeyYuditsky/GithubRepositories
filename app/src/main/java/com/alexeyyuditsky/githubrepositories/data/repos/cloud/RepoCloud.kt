package com.alexeyyuditsky.githubrepositories.data.repos.cloud

import com.alexeyyuditsky.githubrepositories.presentation.repos.RepoUi
import com.squareup.moshi.Json

/**
 * {"id":"82128465", name":"Android", "owner":"{...}" "description":"GitHub上最火的Android"}
 * */
data class RepoCloud(
    val id: Long,
    @field:Json(name = "name")
    val repository: String,
    val owner: RepoOwner,
    val description: String?,
) {

    fun toRepoUi(): RepoUi {
        return RepoUi(
            id = id,
            avatarUrl = owner.avatarUrl,
            login = owner.login,
            repository = repository,
            description = description ?: ""
        )
    }

}

