package com.alexeyyuditsky.githubrepositories.presentation.repos

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ProgressBar
import android.widget.TextView
import androidx.core.view.isVisible
import androidx.paging.LoadState
import androidx.paging.LoadStateAdapter
import androidx.recyclerview.widget.RecyclerView
import com.alexeyyuditsky.githubrepositories.R

class LoadStateAdapter(
    private val retry: () -> Unit,
) : LoadStateAdapter<LoadStateViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, loadState: LoadState): LoadStateViewHolder {
        return LoadStateViewHolder(retry, inflateLayout(R.layout.item_load_state, parent))
    }

    override fun onBindViewHolder(holder: LoadStateViewHolder, loadState: LoadState) {
        holder.bind(loadState)
    }

    private fun inflateLayout(id: Int, parent: ViewGroup): View {
        return LayoutInflater.from(parent.context).inflate(id, parent, false)
    }

}

class LoadStateViewHolder(
    retry: () -> Unit,
    view: View,
) : RecyclerView.ViewHolder(view) {

    private val errorTextView = itemView.findViewById<TextView>(R.id.errorTextView)
    private val retryButton = itemView.findViewById<Button>(R.id.retryButton)
    private val progressBar = itemView.findViewById<ProgressBar>(R.id.progressBar)

    init {
        retryButton.setOnClickListener { retry.invoke() }
    }

    fun bind(loadState: LoadState) {
        if (loadState is LoadState.Error) {
            errorTextView.text = loadState.error.localizedMessage
        }
        errorTextView.isVisible = loadState is LoadState.Error
        retryButton.isVisible = loadState is LoadState.Error
        progressBar.isVisible = loadState is LoadState.Loading
    }

}