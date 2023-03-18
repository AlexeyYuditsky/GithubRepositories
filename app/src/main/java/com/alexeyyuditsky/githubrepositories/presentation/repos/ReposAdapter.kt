package com.alexeyyuditsky.githubrepositories.presentation.repos

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.alexeyyuditsky.githubrepositories.R
import com.bumptech.glide.Glide

typealias RetryClickListener = () -> Unit

class ReposAdapter(
    private val retryClickListener: RetryClickListener,
) : RecyclerView.Adapter<ReposViewHolder>() {

    private val list = mutableListOf<RepoUi>()

    @SuppressLint("NotifyDataSetChanged")
    fun update(repos: List<RepoUi>) {
        list.clear()
        list.addAll(repos)
        notifyDataSetChanged()
    }

    override fun getItemViewType(position: Int): Int {
        return when (list[position]) {
            is RepoUi.Base -> R.layout.item_repos
            is RepoUi.Fail -> R.layout.item_fail
            is RepoUi.Progress -> R.layout.item_progress
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ReposViewHolder {
        return when (viewType) {
            R.layout.item_repos -> ReposViewHolder.Base(inflateLayout(viewType, parent))
            R.layout.item_fail -> ReposViewHolder.Fail(retryClickListener, inflateLayout(viewType, parent))
            else -> ReposViewHolder.Progress(inflateLayout(viewType, parent))
        }
    }

    override fun onBindViewHolder(holder: ReposViewHolder, position: Int) {
        val repoUi = list[position]
        holder.bind(repoUi)
    }

    override fun getItemCount(): Int = list.size

    private fun inflateLayout(viewType: Int, parent: ViewGroup): View {
        return LayoutInflater.from(parent.context).inflate(viewType, parent, false)
    }

}

abstract class ReposViewHolder(
    view: View,
) : ViewHolder(view) {

    open fun bind(item: RepoUi) = Unit

    class Base(
        view: View,
    ) : ReposViewHolder(view) {

        private val avatarImageView = itemView.findViewById<ImageView>(R.id.avatar)
       // private val nameTextView = itemView.findViewById<TextView>(R.id.`@+id/login`)
       // private val fullNameTextView = itemView.findViewById<TextView>(R.id.`@+id/repository`)
        private val descriptionTextView = itemView.findViewById<TextView>(R.id.description)

        override fun bind(item: RepoUi) {
            item.map(object : RepoUiToTextMapper {
                override fun map(name: String, fullName: String?, avatarUrl: String?, description: String?) {
                  //  nameTextView.text = name
                  //  fullNameTextView.text = fullName
                    descriptionTextView.text = description
                    Glide.with(itemView.context)
                        .load(avatarUrl)
                        .into(avatarImageView)
                }
            })
        }

    }

    class Fail(
        private val retryClickListener: RetryClickListener,
        view: View,
    ) : ReposViewHolder(view) {

        private val messageTextView = itemView.findViewById<TextView>(R.id.message)
        private val tryAgainButton = itemView.findViewById<Button>(R.id.tryAgain)

        override fun bind(item: RepoUi) {
            item.map(object : RepoUiToTextMapper {
                override fun map(name: String, fullName: String?, avatarUrl: String?, description: String?) {
                    messageTextView.text = name
                    tryAgainButton.setOnClickListener { retryClickListener.invoke() }
                }
            })
        }

    }

    class Progress(view: View) : ReposViewHolder(view)

}

