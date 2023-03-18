package com.alexeyyuditsky.githubrepositories.presentation.repos

import androidx.recyclerview.widget.RecyclerView
import com.alexeyyuditsky.githubrepositories.data.repos.cloud.RepoCloud
import com.alexeyyuditsky.githubrepositories.databinding.ItemReposBinding
import com.bumptech.glide.Glide

class RepoViewHolder(
    private val itemClickListener: ItemClickListener,
    private val binding: ItemReposBinding,
) : RecyclerView.ViewHolder(binding.root) {

    init {
        itemView.setOnClickListener {
            val temp = itemView.tag as Temp
            itemClickListener(temp.avatar, temp.login, temp.repository, temp.description)
        }
    }

    fun bind(repo: RepoCloud) = with(binding) {
        Glide.with(itemView).load(repo.owner.avatarUrl).into(avatar)
        login.text = repo.owner.login
        repository.text = repo.repository
        description.text = repo.description
        itemView.tag = Temp(repo.owner.avatarUrl, repo.owner.login, repo.repository, repo.description ?: "")
    }

    private class Temp(
        val avatar: String,
        val login: String,
        val repository: String,
        val description: String,
    )

}