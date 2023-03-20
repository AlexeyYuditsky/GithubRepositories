package com.alexeyyuditsky.githubrepositories.domain.issues

import com.alexeyyuditsky.githubrepositories.data.issues.IssuesRepository

interface IssuesInteractor {

    suspend fun fetchIssues(user: String, repo: String): IssuesDomain

    class Base(
        private val repository: IssuesRepository,
    ) : IssuesInteractor {

        override suspend fun fetchIssues(user: String, repo: String): IssuesDomain {
            val issuesData = repository.fetchIssues(user, repo)
            return issuesData.map()
        }

    }

}