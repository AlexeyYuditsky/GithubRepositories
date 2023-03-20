package com.alexeyyuditsky.githubrepositories.data.issues.cloud

interface IssuesCloudDataSource {

    suspend fun fetchIssues(user: String, repo: String): List<IssueCloud>

    class Base(
        private val service: IssuesService
    ) : IssuesCloudDataSource {

        override suspend fun fetchIssues(user: String, repo: String): List<IssueCloud> {
            return service.fetchIssues(user, repo)
        }

    }

}