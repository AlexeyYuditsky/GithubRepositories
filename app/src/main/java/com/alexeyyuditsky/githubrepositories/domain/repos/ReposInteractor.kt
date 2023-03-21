package com.alexeyyuditsky.githubrepositories.domain.repos

import androidx.paging.PagingData
import androidx.paging.map
import com.alexeyyuditsky.githubrepositories.data.repos.ReposRepository
import com.alexeyyuditsky.githubrepositories.presentation.repos.RepoUi
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

interface ReposInteractor {

    suspend fun fetchRepos(query: String): Flow<PagingData<RepoUi>>

    class Base(
        private val repository: ReposRepository,
    ) : ReposInteractor {

        override suspend fun fetchRepos(query: String): Flow<PagingData<RepoUi>> {
            val reposUiFlow = repository.fetchRepos(query).map { pagingData ->
                pagingData.map { repoCloud ->
                    repoCloud.toRepoUi()
                }
            }
            return reposUiFlow
        }

    }

}