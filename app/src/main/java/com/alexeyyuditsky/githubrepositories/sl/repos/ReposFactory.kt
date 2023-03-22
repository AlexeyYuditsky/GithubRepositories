package com.alexeyyuditsky.githubrepositories.sl.repos

import com.alexeyyuditsky.githubrepositories.presentation.repos.ReposViewModel
import com.alexeyyuditsky.githubrepositories.sl.core.BaseFactory

class ReposFactory(
    reposModule: ReposModule,
) : BaseFactory<ReposViewModel>(reposModule)