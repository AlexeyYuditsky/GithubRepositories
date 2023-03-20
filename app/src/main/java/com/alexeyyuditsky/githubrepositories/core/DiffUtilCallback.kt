package com.alexeyyuditsky.githubrepositories.core

import androidx.recyclerview.widget.DiffUtil
import com.alexeyyuditsky.githubrepositories.presentation.issues.IssueUi

class DiffUtilCallback(
    private val oldList: List<IssueUi>,
    private val newList: List<IssueUi>,
) : DiffUtil.Callback() {

    override fun getOldListSize(): Int = oldList.size
    override fun getNewListSize(): Int = newList.size

    override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        return oldList[oldItemPosition].same(newList[newItemPosition])
    }

    override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        return oldList[oldItemPosition].sameContent(newList[newItemPosition])
    }

}