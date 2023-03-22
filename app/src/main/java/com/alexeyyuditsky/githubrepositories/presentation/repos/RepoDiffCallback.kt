package com.alexeyyuditsky.githubrepositories.presentation.repos

import androidx.recyclerview.widget.DiffUtil

object RepoDiffCallback : DiffUtil.ItemCallback<RepoUi>() {

    override fun areItemsTheSame(oldItem: RepoUi, newItem: RepoUi): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: RepoUi, newItem: RepoUi): Boolean {
        return oldItem == newItem
    }

}