package com.alexeyyuditsky.githubrepositories.data.repos

sealed class ReposData {

    //abstract fun map(): ReposDomain

    data class Success(
        private val repos: List<RepoData>,
    ) : ReposData() {

    }

    data class Fail(
        private val e: Exception,
    ) : ReposData() {

    }

}
