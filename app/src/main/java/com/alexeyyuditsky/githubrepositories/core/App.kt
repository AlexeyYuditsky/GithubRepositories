package com.alexeyyuditsky.githubrepositories.core

import android.app.Application
import com.alexeyyuditsky.githubrepositories.data.issues.IssuesRepository
import com.alexeyyuditsky.githubrepositories.data.issues.cloud.IssuesCloudDataSource
import com.alexeyyuditsky.githubrepositories.data.issues.cloud.IssuesService
import com.alexeyyuditsky.githubrepositories.data.repos.*
import com.alexeyyuditsky.githubrepositories.data.repos.cloud.ReposCloudDataSource
import com.alexeyyuditsky.githubrepositories.data.repos.cloud.ReposService
import com.alexeyyuditsky.githubrepositories.domain.issues.IssuesInteractor
import com.alexeyyuditsky.githubrepositories.domain.repos.ReposInteractor
import com.squareup.moshi.Moshi
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory

class App : Application() {

    lateinit var reposInteractor: ReposInteractor

    lateinit var issuesInteractor: IssuesInteractor
    lateinit var resourceProvider: ResourceProvider

    override fun onCreate() {
        super.onCreate()

        val interceptor = HttpLoggingInterceptor().apply { level = HttpLoggingInterceptor.Level.BODY }

        val client = OkHttpClient.Builder()
            .addInterceptor(interceptor)
            .build()

        val retrofit = Retrofit.Builder()
            .baseUrl(BASE_URL)
            .client(client)
            .addConverterFactory(MoshiConverterFactory.create(Moshi.Builder().build()))
            .build()

        val reposService = retrofit.create(ReposService::class.java)
        val issuesService = retrofit.create(IssuesService::class.java)

        val reposCloudDataSource = ReposCloudDataSource.Base(reposService)
        val issuesCloudDataSource = IssuesCloudDataSource.Base(issuesService)

        val reposRepository = ReposRepository.Base(reposCloudDataSource /*toReposDataMapper*/)
        val issuesRepository = IssuesRepository.Base(issuesCloudDataSource /*toReposDataMapper*/)

        reposInteractor = ReposInteractor.Base(reposRepository)
        issuesInteractor = IssuesInteractor.Base(issuesRepository)

        resourceProvider = ResourceProvider.Base(this)
    }


    companion object {
        private const val BASE_URL = "https://api.github.com/"
    }

}