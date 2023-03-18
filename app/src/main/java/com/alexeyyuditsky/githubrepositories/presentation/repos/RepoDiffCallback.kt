package com.alexeyyuditsky.githubrepositories.presentation.repos

import androidx.recyclerview.widget.DiffUtil
import com.alexeyyuditsky.githubrepositories.data.repos.cloud.RepoCloud

object RepoDiffCallback : DiffUtil.ItemCallback<RepoCloud>() {
    override fun areItemsTheSame(oldItem: RepoCloud, newItem: RepoCloud): Boolean = oldItem.id == newItem.id
    override fun areContentsTheSame(oldItem: RepoCloud, newItem: RepoCloud): Boolean = oldItem == newItem
}