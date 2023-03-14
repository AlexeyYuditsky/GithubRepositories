package com.alexeyyuditsky.githubrepositories.domain.repos

import com.alexeyyuditsky.githubrepositories.core.Abstract
import com.alexeyyuditsky.githubrepositories.core.ResourceProvider
import com.alexeyyuditsky.githubrepositories.presentation.repos.ReposUi

abstract class ReposDomainToUiMapper(resourceProvider: ResourceProvider) :
    Abstract.Mapper.DomainToUi<List<RepoDomain>, ReposUi>(resourceProvider)