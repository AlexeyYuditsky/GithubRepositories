package com.alexeyyuditsky.githubrepositories.presentation.repos

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import com.alexeyyuditsky.githubrepositories.databinding.ItemReposBinding

typealias ItemClickListener = (repoUi: RepoUi) -> Unit

class ReposAdapterPaging(
    private val itemClickListener: ItemClickListener,
) : PagingDataAdapter<RepoUi, RepoViewHolder>(RepoDiffCallback) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RepoViewHolder {
        val binding = ItemReposBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return RepoViewHolder(binding, itemClickListener)
    }

    override fun onBindViewHolder(holder: RepoViewHolder, position: Int) {
        val repo = getItem(position) ?: return
        holder.bind(repo)
    }

}