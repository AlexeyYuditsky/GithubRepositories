package com.alexeyyuditsky.githubrepositories.data.issues

import com.alexeyyuditsky.githubrepositories.core.ErrorType
import com.alexeyyuditsky.githubrepositories.data.issues.cloud.IssueCloud
import com.alexeyyuditsky.githubrepositories.domain.issues.IssuesDomain
import retrofit2.HttpException
import java.net.UnknownHostException

sealed class IssuesData {

    abstract fun map(): IssuesDomain

    class Base(
        private val issues: List<IssueCloud>,
    ) : IssuesData() {
        override fun map(): IssuesDomain {
            return IssuesDomain.Base(issues)
        }
    }

    class Fail(
        private val e: Exception,
    ) : IssuesData() {
        override fun map(): IssuesDomain {
            val errorType = when (e) {
                is UnknownHostException -> ErrorType.NO_CONNECTION
                is HttpException -> ErrorType.SERVICE_UNAVAILABLE
                else -> ErrorType.GENERIC_ERROR
            }
            return IssuesDomain.Fail(errorType)
        }
    }

    object Empty : IssuesData() {
        override fun map(): IssuesDomain {
            return IssuesDomain.Empty
        }
    }

}
