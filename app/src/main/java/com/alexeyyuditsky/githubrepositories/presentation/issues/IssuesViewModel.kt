package com.alexeyyuditsky.githubrepositories.presentation.issues

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class IssuesViewModel(
    // private val interactor:,
) : ViewModel() {

    /*private val _issuesLiveData = MutableLiveData<List<>>()
    val issuesLiveData: LiveData<List<>> get() = _issuesLiveData*/

    init {
        fetchIssues()
    }

    fun fetchIssues() {
        viewModelScope.launch(Dispatchers.IO) {

            withContext(Dispatchers.Main) {

            }
        }
    }

}