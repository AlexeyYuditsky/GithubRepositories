package com.alexeyyuditsky.githubrepositories.presentation.repos

import android.os.Bundle
import android.view.View
import android.widget.LinearLayout
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.SimpleItemAnimator
import com.alexeyyuditsky.githubrepositories.App
import com.alexeyyuditsky.githubrepositories.R
import com.alexeyyuditsky.githubrepositories.presentation.ViewModelFactory
import kotlinx.coroutines.launch

class ReposFragment : Fragment(R.layout.fragment_repositories) {

    private val app by lazy { (requireActivity().application as App) }
    private val viewModel by viewModels<ReposViewModel> { ViewModelFactory(app.reposInteractor, app.mapper) }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val reposAdapter = ReposAdapterPaging()
        val recyclerView = requireActivity().findViewById<RecyclerView>(R.id.reposRecyclerView)
        recyclerView.adapter = reposAdapter.withLoadStateHeaderAndFooter(
            header = LoadStateAdapter { reposAdapter.retry() },
            footer = LoadStateAdapter { reposAdapter.retry() },
        )
        recyclerView.addItemDecoration(DividerItemDecoration(requireContext(), LinearLayout.VERTICAL))
        (recyclerView.itemAnimator as SimpleItemAnimator).supportsChangeAnimations = false

        lifecycleScope.launch {
            viewModel.fetchRepos().observe(viewLifecycleOwner) { pagingData ->
                reposAdapter.submitData(lifecycle, pagingData)
            }
        }
    }

}