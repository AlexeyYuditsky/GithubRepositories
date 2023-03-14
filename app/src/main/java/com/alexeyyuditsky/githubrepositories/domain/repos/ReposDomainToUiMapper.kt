package com.alexeyyuditsky.githubrepositories.domain.repos

import com.alexeyyuditsky.githubrepositories.core.Abstract
import com.alexeyyuditsky.githubrepositories.presentation.ReposUi

interface ReposDomainToUiMapper : Abstract.Mapper.DomainToUi<List<RepoDomain>, ReposUi>