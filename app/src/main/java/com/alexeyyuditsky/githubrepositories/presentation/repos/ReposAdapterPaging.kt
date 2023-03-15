package com.alexeyyuditsky.githubrepositories.presentation.repos

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.alexeyyuditsky.githubrepositories.R
import com.alexeyyuditsky.githubrepositories.data.repos.cloud.RepoCloud
import com.bumptech.glide.Glide

class ReposAdapterPaging : PagingDataAdapter<RepoCloud, RepoViewHolder>(RepoDiffCallback) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RepoViewHolder {
        return RepoViewHolder(inflateLayout(R.layout.item_repos, parent))
    }

    override fun onBindViewHolder(holder: RepoViewHolder, position: Int) {
        val repo = getItem(position) ?: return
        holder.bind(repo)
    }

    private fun inflateLayout(id: Int, parent: ViewGroup): View {
        return LayoutInflater.from(parent.context).inflate(id, parent, false)
    }

}

class RepoViewHolder(
    view: View,
) : RecyclerView.ViewHolder(view) {

    private val avatarImageView = itemView.findViewById<ImageView>(R.id.avatar)
    private val nameTextView = itemView.findViewById<TextView>(R.id.name)
    private val fullNameTextView = itemView.findViewById<TextView>(R.id.fullName)
    private val descriptionTextView = itemView.findViewById<TextView>(R.id.description)

    fun bind(repo: RepoCloud) {
        Glide.with(itemView).load(repo.owner.avatarUrl).into(avatarImageView)
        nameTextView.text = repo.name
        fullNameTextView.text = repo.fullName
        descriptionTextView.text = repo.description
    }

}

object RepoDiffCallback : DiffUtil.ItemCallback<RepoCloud>() {
    override fun areItemsTheSame(oldItem: RepoCloud, newItem: RepoCloud): Boolean = oldItem.fullName == newItem.fullName
    override fun areContentsTheSame(oldItem: RepoCloud, newItem: RepoCloud): Boolean = oldItem == newItem
}