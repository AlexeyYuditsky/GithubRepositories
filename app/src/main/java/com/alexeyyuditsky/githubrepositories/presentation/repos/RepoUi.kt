package com.alexeyyuditsky.githubrepositories.presentation.repos

sealed class RepoUi {

    open fun map(mapper: RepoUiToTextMapper) = Unit

    data class Base(
        private val name: String,
        private val fullName: String,
        private val avatarUrl: String,
        private val description: String,
    ) : RepoUi() {
        override fun map(mapper: RepoUiToTextMapper) {
            mapper.map(name, fullName, avatarUrl, description)
        }
    }

    data class Fail(
        private val message: String,
    ) : RepoUi() {
        override fun map(mapper: RepoUiToTextMapper) {
            mapper.map(message)
        }
    }

    object Progress : RepoUi()

}

