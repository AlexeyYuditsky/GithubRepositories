package com.alexeyyuditsky.githubrepositories.presentation.issues

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.alexeyyuditsky.githubrepositories.R
import com.alexeyyuditsky.githubrepositories.databinding.ItemFailBinding
import com.alexeyyuditsky.githubrepositories.databinding.ItemIssueBinding

typealias RetryClickListenerWithArg = (user:String, repo:String) -> Unit

class IssuesAdapter(
    private val retry: RetryClickListenerWithArg,
) : RecyclerView.Adapter<IssueViewHolder>() {

    private val issues = mutableListOf<IssueUi>()

    fun update(newIssues: List<IssueUi>) {
        val diffCallback = DiffUtilCallback(issues, newIssues)
        val result = DiffUtil.calculateDiff(diffCallback, false)
        issues.clear()
        issues.addAll(newIssues)
        result.dispatchUpdatesTo(this)
    }

    override fun getItemViewType(position: Int): Int {
        return when (issues[position]) {
            is IssueUi.Base -> R.layout.item_issue
            is IssueUi.Fail -> R.layout.item_fail
            is IssueUi.Empty -> R.layout.item_empty
            else -> R.layout.item_progress
        }
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): IssueViewHolder {
        return when (viewType) {
            R.layout.item_issue -> {
                val binding = ItemIssueBinding.inflate(LayoutInflater.from(parent.context), parent, false)
                IssueViewHolder.Base(binding)
            }
            R.layout.item_fail -> {
                val binding = ItemFailBinding.inflate(LayoutInflater.from(parent.context), parent, false)
                IssueViewHolder.Fail(binding, retry)
            }
            R.layout.item_empty -> {
                val view = LayoutInflater.from(parent.context).inflate(R.layout.item_empty, parent, false)
                IssueViewHolder.Empty(view)
            }
            else -> {
                val view = LayoutInflater.from(parent.context).inflate(R.layout.item_progress, parent, false)
                IssueViewHolder.Progress(view)
            }
        }
    }

    override fun onBindViewHolder(holder: IssueViewHolder, position: Int) {
        holder.bind(issues[position])
    }

    override fun getItemCount(): Int = issues.size

}

