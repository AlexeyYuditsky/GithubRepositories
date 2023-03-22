package com.alexeyyuditsky.githubrepositories.core

import android.app.Application
import com.alexeyyuditsky.githubrepositories.sl.core.CoreModule
import com.alexeyyuditsky.githubrepositories.sl.issues.IssuesFactory
import com.alexeyyuditsky.githubrepositories.sl.issues.IssuesModule
import com.alexeyyuditsky.githubrepositories.sl.repos.ReposFactory
import com.alexeyyuditsky.githubrepositories.sl.repos.ReposModule

class App : Application() {

    private val coreModule = CoreModule

    override fun onCreate() {
        super.onCreate()
        coreModule.init(this)
    }

    fun reposFactory(): ReposFactory {
        return ReposFactory(ReposModule(coreModule))
    }

    fun issuesFactory(): IssuesFactory {
        return IssuesFactory(IssuesModule(coreModule))
    }

}