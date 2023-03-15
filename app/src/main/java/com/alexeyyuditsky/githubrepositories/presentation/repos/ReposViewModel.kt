package com.alexeyyuditsky.githubrepositories.presentation.repos

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.alexeyyuditsky.githubrepositories.core.log
import com.alexeyyuditsky.githubrepositories.data.repos.cloud.RepoCloud
import com.alexeyyuditsky.githubrepositories.domain.repos.ReposDomainToUiMapper
import com.alexeyyuditsky.githubrepositories.domain.repos.ReposInteractor
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class ReposViewModel(
    private val interactor: ReposInteractor,
    private val mapper: ReposDomainToUiMapper,
) : ViewModel() {

    private val pagingDataLiveData = MutableLiveData<PagingData<RepoCloud>>()

    /*private val _reposLiveData = MutableLiveData<List<RepoUi>>()
    val reposLiveData: LiveData<List<RepoUi>> get() = _reposLiveData*/

    init {
        viewModelScope.launch {
            fetchRepos()
        }
    }

    suspend fun fetchRepos(query: String = "Android"): LiveData<PagingData<RepoCloud>> {
        val list = interactor.fetchRepos(query).cachedIn(viewModelScope)
        pagingDataLiveData.value = list.value
        return list
    }

    /*_reposLiveData.value = listOf(RepoUi.Progress)
    viewModelScope.launch(Dispatchers.IO) {
        val reposDomain = interactor.fetchRepos(query)
        val reposUi = reposDomain.map(mapper)
        withContext(Dispatchers.Main) {
            _reposLiveData.value = reposUi.map()
        }
    }*/

}