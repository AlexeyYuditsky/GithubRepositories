package com.alexeyyuditsky.githubrepositories.presentation.repos

import android.os.Bundle
import android.view.View
import android.widget.LinearLayout
import android.widget.Toast
import androidx.core.os.bundleOf
import androidx.core.view.isVisible
import androidx.core.widget.addTextChangedListener
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.paging.LoadState
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.SimpleItemAnimator
import com.alexeyyuditsky.githubrepositories.core.App
import com.alexeyyuditsky.githubrepositories.R
import com.alexeyyuditsky.githubrepositories.databinding.FragmentReposBinding
import com.alexeyyuditsky.githubrepositories.presentation.ViewModelFactoryRepos
import com.alexeyyuditsky.githubrepositories.presentation.issues.IssuesFragment
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

class ReposFragment : Fragment(R.layout.fragment_repos) {

    private val app by lazy { (requireActivity().application as App) }
    private val viewModel by viewModels<ReposViewModel> { ViewModelFactoryRepos(app.reposInteractor, app.mapper) }
    private lateinit var binding: FragmentReposBinding
    private val reposAdapter = ReposAdapterPaging(createItemClickListener())

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentReposBinding.bind(view)

        initViews()
        initObservers()

        requireActivity().title = getString(R.string.app_name)
    }

    private fun initViews() {
        initSearchEditText()
        initRecyclerView()
    }

    private fun initSearchEditText() {
        binding.searchEditText.addTextChangedListener {
            viewModel.queryToRepos(binding.searchEditText.text!!.trim().toString())
        }
    }

    private fun initRecyclerView() {
        binding.recyclerView.adapter = reposAdapter.withLoadStateHeaderAndFooter(
            header = LoadStateAdapter { reposAdapter.retry() },
            footer = LoadStateAdapter { reposAdapter.retry() },
        )
        binding.recyclerView.addItemDecoration(DividerItemDecoration(requireContext(), LinearLayout.VERTICAL))
        (binding.recyclerView.itemAnimator as SimpleItemAnimator).supportsChangeAnimations = false
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
        binding.retryButton.setOnClickListener { reposAdapter.retry() }
    }

    private fun createItemClickListener(): ItemClickListener {
        return { avatar: String, login: String, repository: String, description: String ->
            val fragment = IssuesFragment()
            fragment.arguments = bundleOf(
                IssuesFragment.KEY_AVATAR to avatar,
                IssuesFragment.KEY_LOGIN to login,
                IssuesFragment.KEY_REPO to repository,
                IssuesFragment.KEY_DESC to description
            )

            requireActivity()
                .supportFragmentManager
                .beginTransaction()
                .replace(R.id.fragmentContainer, fragment)
                .addToBackStack(null)
                .commit()
        }
    }

}