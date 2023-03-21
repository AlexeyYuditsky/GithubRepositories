package com.alexeyyuditsky.githubrepositories.presentation.issues

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.alexeyyuditsky.githubrepositories.R

class IssuesAdapter : RecyclerView.Adapter<IssueViewHolder>() {

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
            R.layout.item_issue -> IssueViewHolder.Base(inflateLayout(viewType, parent))
            R.layout.item_fail -> IssueViewHolder.Fail(inflateLayout(viewType, parent))
            R.layout.item_empty -> IssueViewHolder.Empty(inflateLayout(viewType, parent))
            else -> IssueViewHolder.Progress(inflateLayout(viewType, parent))
        }
    }

    override fun onBindViewHolder(holder: IssueViewHolder, position: Int) {
        holder.bind(issues[position])
    }

    override fun getItemCount(): Int = issues.size

    private fun inflateLayout(viewType: Int, parent: ViewGroup): View {
        return LayoutInflater.from(parent.context).inflate(viewType, parent, false)
    }

}

