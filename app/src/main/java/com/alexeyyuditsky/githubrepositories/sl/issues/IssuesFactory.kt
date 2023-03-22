package com.alexeyyuditsky.githubrepositories.sl.issues

import com.alexeyyuditsky.githubrepositories.presentation.issues.IssuesViewModel
import com.alexeyyuditsky.githubrepositories.sl.core.BaseFactory

class IssuesFactory(
    issuesModule: IssuesModule,
) : BaseFactory<IssuesViewModel>(issuesModule)