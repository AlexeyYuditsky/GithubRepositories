package com.alexeyyuditsky.githubrepositories.presentation.issues

import android.view.View
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.alexeyyuditsky.githubrepositories.R

abstract class IssueViewHolder(view: View) : RecyclerView.ViewHolder(view) {

    open fun bind(item: IssueUi) = Unit

    class Base(
        view: View,
    ) : IssueViewHolder(view) {

        private val textView1: TextView = itemView.findViewById(R.id.title)
        private val textView2: TextView = itemView.findViewById(R.id.user)
        private val textView3: TextView = itemView.findViewById(R.id.created)
        private val textView4: TextView = itemView.findViewById(R.id.updated)

        override fun bind(item: IssueUi) {
            item.map(object : ToTextMapper {
                override fun map(title: String, user: String, created: String, updated: String) {
                    textView1.text = title
                    textView2.text = user
                    textView3.text = created
                    textView4.text = updated
                }
            })
        }
    }

    class Fail(
        view: View,
    ) : IssueViewHolder(view)

    class Progress(
        view: View,
    ) : IssueViewHolder(view)

    class Empty(
        view: View,
    ) : IssueViewHolder(view)

}