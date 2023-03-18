package com.alexeyyuditsky.githubrepositories.data.repos

import com.alexeyyuditsky.githubrepositories.core.Abstract
import com.alexeyyuditsky.githubrepositories.data.repos.cloud.RepoCloud
import com.alexeyyuditsky.githubrepositories.data.repos.cloud.ReposResponse

interface ToReposDataMapper : Abstract.Mapper {

    /*fun map(reposResponse: ReposResponse): List<RepoData>

    class Base(
        private val toRepoCloudMapper: ToRepoCloudMapper,
        private val toStringMapper: ToStringMapper,
       // private val toRepoDataMapper: ToRepoDataMapper,
    ) : ToReposDataMapper {

        override fun map(reposResponse: ReposResponse): List<RepoData> {
            val reposCloud = reposResponse.map(toRepoCloudMapper)
            val reposData: List<RepoData> = reposCloud.map { repoCloud: RepoCloud ->
                repoCloud.map(toStringMapper, toRepoDataMapper)
            }
            return reposData
        }

    }*/

}