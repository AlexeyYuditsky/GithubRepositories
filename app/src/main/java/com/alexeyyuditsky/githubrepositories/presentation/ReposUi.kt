package com.alexeyyuditsky.githubrepositories.presentation

sealed class ReposUi {

    class Success(
        private val repos:List<RepoUi>
    ) : ReposUi() {

    }

    class Fail(
        private val exception: String
    ) : ReposUi() {

    }

}
