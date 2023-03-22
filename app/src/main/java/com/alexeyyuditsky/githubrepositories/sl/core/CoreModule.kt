package com.alexeyyuditsky.githubrepositories.sl.core

import android.content.Context
import com.alexeyyuditsky.githubrepositories.core.ResourceProvider
import com.squareup.moshi.Moshi
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import java.util.concurrent.TimeUnit

object CoreModule {

    private lateinit var retrofit: Retrofit
    lateinit var resourceProvider: ResourceProvider

    fun init(context: Context) {
        val interceptor = HttpLoggingInterceptor().apply { level = HttpLoggingInterceptor.Level.BODY }

        val client = OkHttpClient.Builder()
            .connectTimeout(1, TimeUnit.MINUTES)
            .readTimeout(1, TimeUnit.MINUTES)
            .addInterceptor(interceptor)
            .build()

        val moshi = Moshi.Builder().build()

        retrofit = Retrofit.Builder()
            .baseUrl(BASE_URL)
            .client(client)
            .addConverterFactory(MoshiConverterFactory.create(moshi))
            .build()

        resourceProvider = ResourceProvider.Base(context)
    }

    fun <T> makeService(clazz: Class<T>): T {
        return retrofit.create(clazz)
    }

    private const val BASE_URL = "https://api.github.com/"

}