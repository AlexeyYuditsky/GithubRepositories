package com.alexeyyuditsky.githubrepositories.presentation.repos

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.alexeyyuditsky.githubrepositories.domain.repos.ReposInteractor
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.FlowPreview
import kotlinx.coroutines.flow.*

@OptIn(FlowPreview::class, ExperimentalCoroutinesApi::class)
class ReposViewModel(
    private val interactor: ReposInteractor,
) : ViewModel() {

    val reposFlow: Flow<PagingData<RepoUi>>

    val queryToRepos: (String) -> Unit

    init {
        val actionStateFlow = MutableStateFlow(DEFAULT_QUERY)

        val searches: Flow<String> = actionStateFlow
            .debounce(500)
            .filter { it.isNotBlank() }

        reposFlow = searches
            .flatMapLatest { query: String -> fetchRepos(query) }
            .cachedIn(viewModelScope)

        queryToRepos = { query: String -> actionStateFlow.tryEmit(query) }
    }

    private suspend fun fetchRepos(query: String = DEFAULT_QUERY): Flow<PagingData<RepoUi>> {
        return interactor.fetchRepos(query)
    }

    private companion object {
        const val DEFAULT_QUERY = "Android"
    }

}