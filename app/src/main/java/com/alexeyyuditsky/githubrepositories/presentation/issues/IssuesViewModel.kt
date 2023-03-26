package com.alexeyyuditsky.githubrepositories.presentation.issues

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.alexeyyuditsky.githubrepositories.core.ResourceProvider
import com.alexeyyuditsky.githubrepositories.domain.issues.IssuesInteractor
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class IssuesViewModel(
    private val interactor: IssuesInteractor,
    private val resourceProvider: ResourceProvider,
) : ViewModel() {

    private val _issuesFlow = MutableStateFlow<List<IssueUi>>(listOf(IssueUi.Progress))
    val issuesFlow: StateFlow<List<IssueUi>> get() = _issuesFlow

    fun fetchIssues(user: String, repo: String) {
        viewModelScope.launch(Dispatchers.IO) {
            val issuesDomain = interactor.fetchIssues(user, repo)
            val issuesUi = issuesDomain.map(resourceProvider)
            withContext(Dispatchers.Main) {
                _issuesFlow.emit(issuesUi.issues)
            }
        }
    }

}