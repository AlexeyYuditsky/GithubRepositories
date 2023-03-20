package com.alexeyyuditsky.githubrepositories.domain.issues

import com.alexeyyuditsky.githubrepositories.R
import com.alexeyyuditsky.githubrepositories.core.ErrorType
import com.alexeyyuditsky.githubrepositories.core.ResourceProvider
import com.alexeyyuditsky.githubrepositories.data.issues.cloud.IssueCloud
import com.alexeyyuditsky.githubrepositories.presentation.issues.IssueUi
import com.alexeyyuditsky.githubrepositories.presentation.issues.IssuesUi

sealed class IssuesDomain {

    abstract fun map(resourceProvider: ResourceProvider): IssuesUi

    class Base(
        private val issues: List<IssueCloud>,
    ) : IssuesDomain() {
        override fun map(resourceProvider: ResourceProvider): IssuesUi {
            val issuesUi = issues.map { issueCloud ->
                IssueUi.Base(issueCloud.id, issueCloud.title, issueCloud.user.user, issueCloud.createdAt, issueCloud.updatedAt)
            }
            return IssuesUi(issuesUi)
        }
    }

    class Fail(
        private val e: ErrorType,
    ) : IssuesDomain() {
        override fun map(resourceProvider: ResourceProvider): IssuesUi {
            val resource = when (e) {
                ErrorType.NO_CONNECTION -> R.string.no_connection_message
                ErrorType.SERVICE_UNAVAILABLE -> R.string.service_unavailable_message
                else -> R.string.something_went_wrong
            }
            val message = resourceProvider.getString(resource)
            return IssuesUi(listOf(IssueUi.Fail(message)))
        }
    }

    object Empty : IssuesDomain() {
        override fun map(resourceProvider: ResourceProvider): IssuesUi {
            return IssuesUi(listOf(IssueUi.Empty))
        }
    }

}
