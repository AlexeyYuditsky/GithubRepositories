package com.alexeyyuditsky.githubrepositories.presentation

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import com.alexeyyuditsky.githubrepositories.Dependencies
import com.alexeyyuditsky.githubrepositories.R
import com.alexeyyuditsky.githubrepositories.core.log
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class RepositoriesFragment : Fragment(R.layout.fragment_repositories) {

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        lifecycleScope.launch(Dispatchers.IO) {
            log(Dependencies.reposInteractor.fetchRepos("Android"))
        }
    }

}