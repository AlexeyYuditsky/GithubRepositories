package com.alexeyyuditsky.githubrepositories.presentation.main

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.alexeyyuditsky.githubrepositories.R
import com.alexeyyuditsky.githubrepositories.presentation.repos.RepositoriesFragment

class MainActivity : AppCompatActivity(R.layout.activity_main) {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        if (savedInstanceState == null) {
            supportFragmentManager.beginTransaction()
                .replace(R.id.fragmentContainer, RepositoriesFragment())
                .commit()
        }
    }

}