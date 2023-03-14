package com.alexeyyuditsky.githubrepositories.presentation.repos

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.alexeyyuditsky.githubrepositories.domain.repos.ReposDomainToUiMapper
import com.alexeyyuditsky.githubrepositories.domain.repos.ReposInteractor
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class ReposViewModel(
    private val interactor: ReposInteractor,
    private val mapper: ReposDomainToUiMapper,
) : ViewModel() {

    private val _reposLiveData = MutableLiveData<List<RepoUi>>()
    val reposLiveData: LiveData<List<RepoUi>> get() = _reposLiveData

    init {
        fetchRepos()
    }

    fun fetchRepos(query: String = "Android") {
        _reposLiveData.value = listOf(RepoUi.Progress)
        viewModelScope.launch(Dispatchers.IO) {
            val reposDomain = interactor.fetchRepos(query)
            val reposUi = reposDomain.map(mapper)
            withContext(Dispatchers.Main) {
                _reposLiveData.value = reposUi.map()
            }
        }
    }

}