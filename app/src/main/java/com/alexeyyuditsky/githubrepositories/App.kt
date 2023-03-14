package com.alexeyyuditsky.githubrepositories

import android.app.Application
import com.alexeyyuditsky.githubrepositories.core.ResourceProvider
import com.alexeyyuditsky.githubrepositories.data.repos.*
import com.alexeyyuditsky.githubrepositories.data.repos.cloud.ReposCloudDataSource
import com.alexeyyuditsky.githubrepositories.data.repos.cloud.ReposService
import com.alexeyyuditsky.githubrepositories.domain.repos.BaseRepoDataToDomainMapper
import com.alexeyyuditsky.githubrepositories.domain.repos.BaseReposDataToDomainMapper
import com.alexeyyuditsky.githubrepositories.domain.repos.ReposDomainToUiMapper
import com.alexeyyuditsky.githubrepositories.domain.repos.ReposInteractor
import com.alexeyyuditsky.githubrepositories.presentation.repos.BaseRepoDomainToUiMapper
import com.alexeyyuditsky.githubrepositories.presentation.repos.BaseReposDomainToUiMapper
import com.squareup.moshi.Moshi
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory

class App : Application() {

    lateinit var reposInteractor: ReposInteractor
    lateinit var mapper: ReposDomainToUiMapper

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

        val cloudDataSource = ReposCloudDataSource.Base(reposService)

        val toReposDataMapper = ToReposDataMapper.Base(
            ToRepoCloudMapper.Base(),
            ToStringMapper.Base(),
            ToRepoDataMapper.Base()
        )

        val reposRepository = ReposRepository.Base(cloudDataSource, toReposDataMapper)

        reposInteractor = ReposInteractor.Base(
            reposRepository,
            BaseReposDataToDomainMapper(BaseRepoDataToDomainMapper())
        )

        mapper = BaseReposDomainToUiMapper(BaseRepoDomainToUiMapper(), ResourceProvider.Base(this))
    }

    companion object {
        private const val BASE_URL = "https://api.github.com/search/"
    }

}