package com.alexeyyuditsky.githubrepositories.domain.repos

import com.alexeyyuditsky.githubrepositories.data.repos.RepoData
import com.alexeyyuditsky.githubrepositories.data.repos.RepoDataToDomainMapper
import com.alexeyyuditsky.githubrepositories.data.repos.ReposDataToDomainMapper

class BaseReposDataToDomainMapper(
    private val mapper: RepoDataToDomainMapper,
) : ReposDataToDomainMapper {

    override fun map(data: List<RepoData>): ReposDomain {
        val repos: List<RepoDomain> = data.map { repoData: RepoData ->
            repoData.map(mapper)
        }
        return ReposDomain.Success(repos)
    }

    override fun map(e: Exception): ReposDomain {
        val errorType = errorType(e)
        return ReposDomain.Fail(errorType)
    }

}