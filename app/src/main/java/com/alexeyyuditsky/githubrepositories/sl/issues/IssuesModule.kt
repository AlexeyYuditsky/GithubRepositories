package com.alexeyyuditsky.githubrepositories.sl.issues

import com.alexeyyuditsky.githubrepositories.data.issues.IssuesRepository
import com.alexeyyuditsky.githubrepositories.data.issues.cloud.IssuesCloudDataSource
import com.alexeyyuditsky.githubrepositories.data.issues.cloud.IssuesService
import com.alexeyyuditsky.githubrepositories.domain.issues.IssuesInteractor
import com.alexeyyuditsky.githubrepositories.presentation.issues.IssuesViewModel
import com.alexeyyuditsky.githubrepositories.sl.core.BaseModule
import com.alexeyyuditsky.githubrepositories.sl.core.CoreModule

class IssuesModule(
    private val coreModule: CoreModule,
) : BaseModule<IssuesViewModel> {

    override fun getViewModel(): IssuesViewModel {
        return IssuesViewModel(getIssuesInteractor(), coreModule.resourceProvider)
    }

    private fun getIssuesService(): IssuesService {
        return coreModule.makeService(IssuesService::class.java)
    }

    private fun getIssuesCloudDataSource(): IssuesCloudDataSource {
        return IssuesCloudDataSource.Base(getIssuesService())
    }

    private fun getIssuesRepository(): IssuesRepository {
        return IssuesRepository.Base(getIssuesCloudDataSource())
    }

    private fun getIssuesInteractor(): IssuesInteractor {
        return IssuesInteractor.Base(getIssuesRepository())
    }

}