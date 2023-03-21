package com.alexeyyuditsky.githubrepositories.presentation.issues

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.alexeyyuditsky.githubrepositories.databinding.ItemFailBinding
import com.alexeyyuditsky.githubrepositories.databinding.ItemIssueBinding

abstract class IssueViewHolder(view: View) : RecyclerView.ViewHolder(view) {

    open fun bind(item: IssueUi) = Unit

    class Base(
        private val binding: ItemIssueBinding,
    ) : IssueViewHolder(binding.root) {

        override fun bind(item: IssueUi) {
            item.map(object : ToTextMapper {
                override fun map(title: String, user: String, created: String, updated: String) {
                    binding.title.text = title
                    binding.user.text = user
                    binding.created.text = created
                    binding.updated.text = updated
                }
            })
        }
    }

    class Fail(
        private val binding: ItemFailBinding,
        private val retry: RetryClickListenerWithArg,
    ) : IssueViewHolder(binding.root) {

        override fun bind(item: IssueUi) {
            item.map(object : ToTextMapper {
                override fun map(title: String, user: String, created: String, updated: String) {
                    binding.message.text = title
                    binding.tryAgain.setOnClickListener { retry.invoke(user, title) }
                }
            })
        }

    }

    class Progress(
        view: View,
    ) : IssueViewHolder(view)

    class Empty(
        view: View,
    ) : IssueViewHolder(view)

}