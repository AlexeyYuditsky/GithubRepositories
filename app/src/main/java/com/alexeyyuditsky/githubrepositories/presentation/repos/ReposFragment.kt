package com.alexeyyuditsky.githubrepositories.presentation.repos

import android.os.Bundle
import android.view.View
import android.widget.LinearLayout
import android.widget.Toast
import androidx.core.view.isVisible
import androidx.core.widget.addTextChangedListener
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.paging.LoadState
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.SimpleItemAnimator
import com.alexeyyuditsky.githubrepositories.App
import com.alexeyyuditsky.githubrepositories.R
import com.alexeyyuditsky.githubrepositories.databinding.FragmentRepositoriesBinding
import com.alexeyyuditsky.githubrepositories.presentation.ViewModelFactory
import com.google.android.material.textfield.TextInputEditText
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

class ReposFragment : Fragment(R.layout.fragment_repositories) {

    private val app by lazy { (requireActivity().application as App) }
    private val viewModel by viewModels<ReposViewModel> { ViewModelFactory(app.reposInteractor, app.mapper) }
    private lateinit var binding: FragmentRepositoriesBinding

    private val reposAdapter = ReposAdapterPaging()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentRepositoriesBinding.bind(view)

        initViews()
        initObservers()
    }

    private fun initViews() {
        initSearchEditText()
        initRecyclerView()
    }

    private fun initSearchEditText() {
        val searchEditText = requireActivity().findViewById<TextInputEditText>(R.id.searchEditText)
        searchEditText.addTextChangedListener {
            viewModel.queryToRepos(searchEditText.text!!.trim().toString())
        }
    }

    private fun initRecyclerView() {
        val recyclerView = requireActivity().findViewById<RecyclerView>(R.id.recyclerView)
        recyclerView.adapter = reposAdapter.withLoadStateHeaderAndFooter(
            header = LoadStateAdapter { reposAdapter.retry() },
            footer = LoadStateAdapter { reposAdapter.retry() },
        )
        recyclerView.addItemDecoration(DividerItemDecoration(requireContext(), LinearLayout.VERTICAL))
        (recyclerView.itemAnimator as SimpleItemAnimator).supportsChangeAnimations = false
    }

    private fun initObservers() {
        initFetchData()
        initState()
    }

    private fun initFetchData() {
        lifecycleScope.launch {
            viewModel.reposFlow.collectLatest {
                reposAdapter.submitData(it)
            }
        }
    }

    private fun initState() {
        lifecycleScope.launch {
            reposAdapter.loadStateFlow.collect { loadState ->
                val isListEmpty = loadState.refresh is LoadState.NotLoading && reposAdapter.itemCount == 0
                binding.noResultTextView.isVisible = isListEmpty
                binding.recyclerView.isVisible = !isListEmpty
                binding.progressBar.isVisible = loadState.source.refresh is LoadState.Loading
                binding.retryButton.isVisible = loadState.source.refresh is LoadState.Error

                val errorState = loadState.source.append as? LoadState.Error
                    ?: loadState.source.prepend as? LoadState.Error
                    ?: loadState.append as? LoadState.Error
                    ?: loadState.prepend as? LoadState.Error
                errorState?.let {
                    Toast.makeText(requireContext(), getString(R.string.whoops, it.error), Toast.LENGTH_LONG).show()
                }
            }
        }
    }

}