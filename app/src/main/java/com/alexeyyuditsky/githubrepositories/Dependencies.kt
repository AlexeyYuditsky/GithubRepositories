package com.alexeyyuditsky.githubrepositories

import com.alexeyyuditsky.githubrepositories.data.repos.*
import com.alexeyyuditsky.githubrepositories.data.repos.cloud.ReposCloudDataSource
import com.alexeyyuditsky.githubrepositories.data.repos.cloud.ReposService
import com.alexeyyuditsky.githubrepositories.domain.repos.ReposInteractor
import com.squareup.moshi.Moshi
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory


object Dependencies {

    private const val BASE_URL = "https://api.github.com/search/"

    private val interceptor = HttpLoggingInterceptor().apply { level = HttpLoggingInterceptor.Level.BODY }

    private val client = OkHttpClient.Builder()
        .addInterceptor(interceptor)
        .build()

    private val retrofit = Retrofit.Builder()
        .baseUrl(BASE_URL)
        .client(client)
        .addConverterFactory(MoshiConverterFactory.create(Moshi.Builder().build()))
        .build()

    private val reposService = retrofit.create(ReposService::class.java)

    private val cloudDataSource = ReposCloudDataSource.Base(reposService)

    private val toReposDataMapper = ToReposDataMapper.Base(
        ToRepoCloudMapper.Base(),
        ToStringMapper.Base(),
        ToRepoDataMapper.Base()
    )

    private val reposRepository = ReposRepository.Base(cloudDataSource, toReposDataMapper)

    val reposInteractor = ReposInteractor.Base(reposRepository)

}

