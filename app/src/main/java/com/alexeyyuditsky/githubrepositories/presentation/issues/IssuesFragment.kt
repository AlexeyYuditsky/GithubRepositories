package com.alexeyyuditsky.githubrepositories.presentation.issues

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.alexeyyuditsky.githubrepositories.App
import com.alexeyyuditsky.githubrepositories.R
import com.alexeyyuditsky.githubrepositories.databinding.FragmentIssuesBinding
import com.alexeyyuditsky.githubrepositories.presentation.ViewModelFactory
import com.bumptech.glide.Glide

class IssuesFragment : Fragment(R.layout.fragment_issues) {

    private val app by lazy { (requireActivity().application as App) }
    private val viewModel by viewModels<IssuesViewModel> { ViewModelFactory(app.reposInteractor, app.mapper) }
    private lateinit var binding: FragmentIssuesBinding

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        binding = FragmentIssuesBinding.bind(view)

        if (savedInstanceState == null) {
            val avatar = arguments?.getString("avatar").toString()
            Glide.with(this).load(avatar).into(binding.itemRepos.avatar)
            binding.itemRepos.login.text = arguments?.getString("login").toString()
            binding.itemRepos.repository.text = arguments?.getString("repository").toString()
            binding.itemRepos.description.text = arguments?.getString("description").toString()
        }
    }

}