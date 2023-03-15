package com.alexeyyuditsky.githubrepositories.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.alexeyyuditsky.githubrepositories.domain.repos.ReposDomainToUiMapper
import com.alexeyyuditsky.githubrepositories.domain.repos.ReposInteractor
import com.alexeyyuditsky.githubrepositories.presentation.repos.ReposViewModel

class ViewModelFactory(
    private val interactor: ReposInteractor,
    private val mapper: ReposDomainToUiMapper,
) : ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        @Suppress("UNCHECKED_CAST")
        return ReposViewModel(interactor, mapper) as T
    }

}