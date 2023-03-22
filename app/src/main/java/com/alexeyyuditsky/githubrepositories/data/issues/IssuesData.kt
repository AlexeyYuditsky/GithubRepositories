package com.alexeyyuditsky.githubrepositories.data.issues

import com.alexeyyuditsky.githubrepositories.core.Abstract
import com.alexeyyuditsky.githubrepositories.data.issues.cloud.IssueCloud
import com.alexeyyuditsky.githubrepositories.domain.issues.IssuesDomain

sealed class IssuesData : Abstract.DataToDomain<IssuesDomain> {

    data class Base(
        private val issues: List<IssueCloud>,
    ) : IssuesData() {
        override fun map(): IssuesDomain {
            return IssuesDomain.Base(issues)
        }
    }

    data class Fail(
        private val e: Exception,
    ) : IssuesData() {
        override fun map(): IssuesDomain {
            return IssuesDomain.Fail(errorType(e))
        }
    }

    object Empty : IssuesData() {
        override fun map(): IssuesDomain {
            return IssuesDomain.Empty
        }
    }

}
