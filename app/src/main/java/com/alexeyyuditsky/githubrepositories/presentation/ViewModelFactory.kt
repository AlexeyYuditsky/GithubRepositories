package com.alexeyyuditsky.githubrepositories.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.alexeyyuditsky.githubrepositories.core.ResourceProvider
import com.alexeyyuditsky.githubrepositories.domain.issues.IssuesInteractor
import com.alexeyyuditsky.githubrepositories.domain.repos.ReposInteractor
import com.alexeyyuditsky.githubrepositories.presentation.issues.IssuesViewModel
import com.alexeyyuditsky.githubrepositories.presentation.repos.ReposViewModel

class ViewModelFactoryRepos(
    private val interactor: ReposInteractor,
) : ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        @Suppress("UNCHECKED_CAST")
        return ReposViewModel(interactor) as T
    }

}

class ViewModelFactoryIssues(
    private val interactor: IssuesInteractor,
    private val resourceProvider: ResourceProvider
) : ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        @Suppress("UNCHECKED_CAST")
        return IssuesViewModel(interactor, resourceProvider) as T
    }

}