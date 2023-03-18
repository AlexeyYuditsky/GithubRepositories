package com.alexeyyuditsky.githubrepositories.presentation.repos

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import com.alexeyyuditsky.githubrepositories.data.repos.cloud.RepoCloud
import com.alexeyyuditsky.githubrepositories.databinding.ItemReposBinding

typealias ItemClickListener = (avatar: String, login: String, repository: String, description: String) -> Unit

class ReposAdapterPaging(
    private val itemClickListener: ItemClickListener,
) : PagingDataAdapter<RepoCloud, RepoViewHolder>(RepoDiffCallback) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RepoViewHolder {
        val binding = ItemReposBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return RepoViewHolder(itemClickListener, binding)
    }

    override fun onBindViewHolder(holder: RepoViewHolder, position: Int) {
        val repo = getItem(position) ?: return
        holder.bind(repo)
    }

}