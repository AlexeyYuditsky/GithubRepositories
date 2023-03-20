package com.alexeyyuditsky.githubrepositories.data.issues

import com.alexeyyuditsky.githubrepositories.core.log
import com.alexeyyuditsky.githubrepositories.data.issues.cloud.IssuesCloudDataSource

interface IssuesRepository {

    suspend fun fetchIssues(user: String, repo: String): IssuesData

    class Base(
        private val cloudDataSource: IssuesCloudDataSource,
    ) : IssuesRepository {

        override suspend fun fetchIssues(user: String, repo: String): IssuesData {
            return try {
                val issues = cloudDataSource.fetchIssues(user, repo)
                if (issues.isEmpty()) {
                    IssuesData.Empty
                } else {
                    IssuesData.Base(issues)
                }
            } catch (e: Exception) {
                IssuesData.Fail(e)
            }
        }

    }

}