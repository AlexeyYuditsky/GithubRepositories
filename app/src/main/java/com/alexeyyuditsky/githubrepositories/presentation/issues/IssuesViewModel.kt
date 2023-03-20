package com.alexeyyuditsky.githubrepositories.presentation.issues

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.alexeyyuditsky.githubrepositories.core.ResourceProvider
import com.alexeyyuditsky.githubrepositories.core.log
import com.alexeyyuditsky.githubrepositories.domain.issues.IssuesInteractor
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class IssuesViewModel(
    private val interactor: IssuesInteractor,
    private val resourceProvider: ResourceProvider,
) : ViewModel() {

    private val _issuesLiveData = MutableLiveData<List<IssueUi>>()
    val issuesLiveData: LiveData<List<IssueUi>> get() = _issuesLiveData

    fun fetchIssues(user: String, repo: String) {
        _issuesLiveData.value = listOf(IssueUi.Progress)
        viewModelScope.launch(Dispatchers.IO) {
            val issuesDomain = interactor.fetchIssues(user, repo)
            val issuesUi = issuesDomain.map(resourceProvider)
            withContext(Dispatchers.Main) {
                _issuesLiveData.value = issuesUi.issues
            }
        }
    }

}