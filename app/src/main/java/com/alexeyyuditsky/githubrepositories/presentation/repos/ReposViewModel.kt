package com.alexeyyuditsky.githubrepositories.presentation.repos

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.alexeyyuditsky.githubrepositories.data.repos.cloud.RepoCloud
import com.alexeyyuditsky.githubrepositories.domain.repos.ReposDomainToUiMapper
import com.alexeyyuditsky.githubrepositories.domain.repos.ReposInteractor
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.FlowPreview
import kotlinx.coroutines.flow.*

@OptIn(FlowPreview::class, ExperimentalCoroutinesApi::class)
class ReposViewModel(
    private val interactor: ReposInteractor,
    private val mapper: ReposDomainToUiMapper,
) : ViewModel() {

    val reposFlow: Flow<PagingData<RepoCloud>>

    val queryToRepos: (String) -> Unit

    init {
        val actionStateFlow = MutableStateFlow("Android")

        val searches: Flow<String> = actionStateFlow
            .debounce(500)
            .filter { it.isNotBlank() }

        reposFlow = searches
            .flatMapLatest { query: String -> fetchRepos(query) }
            .cachedIn(viewModelScope)

        queryToRepos = { query: String -> actionStateFlow.tryEmit(query) }
    }

    private suspend fun fetchRepos(query: String = "Android"): Flow<PagingData<RepoCloud>> {
        return interactor.fetchRepos(query)
    }

}