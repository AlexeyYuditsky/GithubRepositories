package com.alexeyyuditsky.githubrepositories.presentation.repos

import android.os.Bundle
import android.view.View
import android.widget.LinearLayout
import androidx.core.os.bundleOf
import androidx.core.view.isVisible
import androidx.core.widget.addTextChangedListener
import androidx.fragment.app.Fragment
import androidx.fragment.app.commit
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.paging.CombinedLoadStates
import androidx.paging.LoadState
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.SimpleItemAnimator
import com.alexeyyuditsky.githubrepositories.R
import com.alexeyyuditsky.githubrepositories.core.App
import com.alexeyyuditsky.githubrepositories.databinding.FragmentReposBinding
import com.alexeyyuditsky.githubrepositories.presentation.issues.IssuesFragment
import com.alexeyyuditsky.githubrepositories.presentation.main.MainActivity
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

class ReposFragment : Fragment(R.layout.fragment_repos) {

    private val viewModel by viewModels<ReposViewModel>(
        factoryProducer = { (requireActivity().application as App).reposFactory() }
    )
    private var _binding: FragmentReposBinding? = null
    private val binding get() = _binding!!

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        _binding = FragmentReposBinding.bind(view)
        val reposAdapter = initViews()
        initObservers(reposAdapter)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private fun initViews(): ReposAdapterPaging {
        initToolbar()
        initSearchEditText()
        return initRecyclerView()
    }

    private fun initToolbar() {
        (requireActivity() as MainActivity).supportActionBar?.hide()
    }

    private fun initSearchEditText() {
        binding.searchEditText.addTextChangedListener {
            viewModel.queryToRepos(binding.searchEditText.text!!.trim().toString())
        }
    }

    private fun initRecyclerView(): ReposAdapterPaging {
        val reposAdapter = ReposAdapterPaging(createItemClickListener())
        binding.recyclerView.adapter = reposAdapter.withLoadStateHeaderAndFooter(
            header = LoadStateAdapter { reposAdapter.retry() },
            footer = LoadStateAdapter { reposAdapter.retry() },
        )
        binding.recyclerView.addItemDecoration(DividerItemDecoration(requireContext(), LinearLayout.VERTICAL))
        (binding.recyclerView.itemAnimator as SimpleItemAnimator).supportsChangeAnimations = false
        binding.retryButton.setOnClickListener { reposAdapter.retry() }

        return reposAdapter
    }

    private fun initObservers(reposAdapter: ReposAdapterPaging) {
        initFetchRepos(reposAdapter)
        initRecyclerState(reposAdapter)
    }

    private fun initFetchRepos(reposAdapter: ReposAdapterPaging) {
        lifecycleScope.launch {
            viewModel.reposFlow.collectLatest {
                reposAdapter.submitData(it)
            }
        }
    }

    private fun initRecyclerState(reposAdapter: ReposAdapterPaging) {
        lifecycleScope.launch {
            reposAdapter.loadStateFlow.collect { loadState: CombinedLoadStates ->
                val isListEmpty = loadState.refresh is LoadState.NotLoading && reposAdapter.itemCount == 0
                with(binding) {
                    noResultTextView.isVisible = isListEmpty
                    recyclerView.isVisible = !isListEmpty && loadState.source.refresh !is LoadState.Error
                    appendProgress.isVisible = loadState.source.refresh is LoadState.Loading
                    retryButton.isVisible = loadState.source.refresh is LoadState.Error
                    errorMessageTextView.isVisible = loadState.source.refresh is LoadState.Error
                }
            }
        }
    }

    private fun createItemClickListener(): ItemClickListener {
        return { repoUi: RepoUi ->
            val fragment = IssuesFragment()
            fragment.arguments = bundleOf(
                IssuesFragment.KEY_AVATAR to repoUi.avatarUrl,
                IssuesFragment.KEY_LOGIN to repoUi.login,
                IssuesFragment.KEY_REPO to repoUi.repository,
                IssuesFragment.KEY_DESC to repoUi.description
            )

            requireActivity()
                .supportFragmentManager
                .commit {
                    setCustomAnimations(
                        R.anim.slide_in,
                        R.anim.fade_out,
                        R.anim.fade_in,
                        R.anim.slide_out
                    )
                        .replace(R.id.fragmentContainer, fragment)
                        .addToBackStack(null)
                }
        }
    }

}