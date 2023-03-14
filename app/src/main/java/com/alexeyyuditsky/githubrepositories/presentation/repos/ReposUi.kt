package com.alexeyyuditsky.githubrepositories.presentation.repos

sealed class ReposUi {

    abstract fun map(): List<RepoUi>

    data class Success(
        private val repos: List<RepoUi>,
    ) : ReposUi() {
        override fun map(): List<RepoUi> {
            return repos
        }
    }

}
