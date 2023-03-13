package com.alexeyyuditsky.githubrepositories.data.repos.cloud

interface ReposCloudDataSource {

    suspend fun fetchRepos(query: String): ReposResponse

    class Base(
        private val service: ReposService,
    ) : ReposCloudDataSource {

        override suspend fun fetchRepos(query: String): ReposResponse {
            return service.fetchRepos(query)
        }

    }

}
