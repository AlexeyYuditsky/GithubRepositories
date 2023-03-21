package com.alexeyyuditsky.githubrepositories.presentation.repos

import androidx.recyclerview.widget.RecyclerView
import com.alexeyyuditsky.githubrepositories.data.repos.cloud.RepoCloud
import com.alexeyyuditsky.githubrepositories.databinding.ItemReposBinding
import com.bumptech.glide.Glide

class RepoViewHolder(
    private val binding: ItemReposBinding,
    itemClickListener: ItemClickListener,
) : RecyclerView.ViewHolder(binding.root) {

    init {
        itemView.setOnClickListener {
            val repoUi = itemView.tag as RepoUi
            itemClickListener(repoUi)
        }
    }

    fun bind(repo: RepoUi) = with(binding) {
        Glide.with(itemView).load(repo.avatarUrl).into(avatar)
        login.text = repo.login
        repository.text = repo.repository
        description.text = repo.description
        itemView.tag = repo
    }

}