package com.alexeyyuditsky.githubrepositories.domain.issues

import com.alexeyyuditsky.githubrepositories.core.Abstract
import com.alexeyyuditsky.githubrepositories.core.ErrorType
import com.alexeyyuditsky.githubrepositories.core.ResourceProvider
import com.alexeyyuditsky.githubrepositories.data.issues.cloud.IssueCloud
import com.alexeyyuditsky.githubrepositories.presentation.issues.IssueUi
import com.alexeyyuditsky.githubrepositories.presentation.issues.IssuesUi

sealed class IssuesDomain : Abstract.DomainToUi<IssuesUi> {

    data class Base(
        private val issues: List<IssueCloud>,
    ) : IssuesDomain() {

        override fun map(resourceProvider: ResourceProvider): IssuesUi {
            val issuesUi = issues.map {
                IssueUi.Base(it.id, it.title, it.user.user, it.createdAt, it.updatedAt)
            }
            return IssuesUi(issuesUi)
        }
    }

    data class Fail(
        private val e: ErrorType,
    ) : IssuesDomain() {
        override fun map(resourceProvider: ResourceProvider): IssuesUi {
            val errorMessage = errorMessage(e, resourceProvider)
            return IssuesUi(listOf(IssueUi.Fail(errorMessage)))
        }
    }

    object Empty : IssuesDomain() {
        override fun map(resourceProvider: ResourceProvider): IssuesUi {
            return IssuesUi(listOf(IssueUi.Empty))
        }
    }

}
