package com.alexeyyuditsky.githubrepositories.sl.repos

import com.alexeyyuditsky.githubrepositories.data.repos.ReposRepository
import com.alexeyyuditsky.githubrepositories.data.repos.cloud.ReposCloudDataSource
import com.alexeyyuditsky.githubrepositories.data.repos.cloud.ReposService
import com.alexeyyuditsky.githubrepositories.domain.repos.ReposInteractor
import com.alexeyyuditsky.githubrepositories.presentation.repos.ReposViewModel
import com.alexeyyuditsky.githubrepositories.sl.core.BaseModule
import com.alexeyyuditsky.githubrepositories.sl.core.CoreModule

class ReposModule(
    private val coreModule: CoreModule,
) : BaseModule<ReposViewModel> {

    override fun getViewModel(): ReposViewModel {
        return ReposViewModel(getReposInteractor())
    }

    private fun getReposService(): ReposService {
        return coreModule.makeService(ReposService::class.java)
    }

    private fun getReposCloudDataSource(): ReposCloudDataSource {
        return ReposCloudDataSource.Base(getReposService())
    }

    private fun getReposRepository(): ReposRepository {
        return ReposRepository.Base(getReposCloudDataSource())
    }

    private fun getReposInteractor(): ReposInteractor {
        return ReposInteractor.Base(getReposRepository())
    }

}