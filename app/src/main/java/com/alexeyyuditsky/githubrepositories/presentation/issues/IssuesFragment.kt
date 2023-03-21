package com.alexeyyuditsky.githubrepositories.presentation.issues

import android.os.Bundle
import android.view.View
import android.widget.LinearLayout
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.SimpleItemAnimator
import com.alexeyyuditsky.githubrepositories.R
import com.alexeyyuditsky.githubrepositories.core.App
import com.alexeyyuditsky.githubrepositories.databinding.FragmentIssuesBinding
import com.alexeyyuditsky.githubrepositories.presentation.ViewModelFactoryIssues
import com.alexeyyuditsky.githubrepositories.presentation.main.MainActivity
import com.bumptech.glide.Glide

class IssuesFragment : Fragment(R.layout.fragment_issues) {

    private val app by lazy { (requireActivity().application as App) }
    private val viewModel by viewModels<IssuesViewModel> {
        ViewModelFactoryIssues(
            app.issuesInteractor,
            app.resourceProvider
        )
    }
    private lateinit var binding: FragmentIssuesBinding

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        binding = FragmentIssuesBinding.bind(view)
        val dataForQuery = fetchFragmentArguments()
        val issuesAdapter = initViews(dataForQuery.second)
        fetchIssues(savedInstanceState, dataForQuery)
        initObservers(issuesAdapter)
    }

    private fun initViews(repo: String): IssuesAdapter {
        initToolbar(repo)
        return initRecyclerView()
    }

    private fun initToolbar(repo: String) {
        val actionBar = (requireActivity() as MainActivity).supportActionBar
        actionBar?.title = getString(R.string.issues, repo)
        actionBar?.show()
    }

    private fun initRecyclerView(): IssuesAdapter {
        val issuesAdapter = IssuesAdapter { user: String, repo: String -> viewModel.fetchIssues(user, repo) }
        binding.recyclerView.adapter = issuesAdapter
        binding.recyclerView.addItemDecoration(DividerItemDecoration(requireContext(), LinearLayout.VERTICAL))
        (binding.recyclerView.itemAnimator as SimpleItemAnimator).supportsChangeAnimations = false
        return issuesAdapter
    }

    private fun initObservers(issuesAdapter: IssuesAdapter) {
        viewModel.issuesLiveData.observe(viewLifecycleOwner) {
            issuesAdapter.update(it)
        }
    }

    private fun fetchIssues(savedInstanceState: Bundle?, dataForQuery: Pair<String, String>) {
        if (savedInstanceState == null) {
            viewModel.fetchIssues(dataForQuery.first, dataForQuery.second)
        }
    }

    private fun fetchFragmentArguments(): Pair<String, String> {
        val login = arguments?.getString(KEY_LOGIN).toString()
        val repo = arguments?.getString(KEY_REPO).toString()
        val avatar = arguments?.getString(KEY_AVATAR).toString()
        with(binding) {
            Glide.with(this@IssuesFragment).load(avatar).into(itemRepos.avatar)
            itemRepos.login.text = login
            itemRepos.repository.text = repo
            itemRepos.description.text = arguments?.getString(KEY_DESC).toString()
        }

        return Pair(login, repo)
    }

    companion object {
        const val KEY_LOGIN = "login"
        const val KEY_REPO = "repo"
        const val KEY_AVATAR = "avatar"
        const val KEY_DESC = "desc"
    }

}