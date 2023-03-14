package com.alexeyyuditsky.githubrepositories.data.repos

import com.alexeyyuditsky.githubrepositories.core.Abstract
import com.alexeyyuditsky.githubrepositories.domain.repos.ReposDomain

interface ReposDataToDomainMapper : Abstract.Mapper.DataToDomain<List<RepoData>, ReposDomain>