package com.app.data.providers

import com.localebro.okhttpprofiler.OkHttpProfilerInterceptor
import okhttp3.Dispatcher
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.android.BuildConfig
import java.util.concurrent.TimeUnit

fun provideNetworkClient(): OkHttpClient {

    val dispatcher = Dispatcher()
    dispatcher.maxRequests = 2

    val loggingInterceptor = HttpLoggingInterceptor().apply {
        level = HttpLoggingInterceptor.Level.BODY
    }

    val builder = OkHttpClient.Builder()
        .connectTimeout(1, TimeUnit.MINUTES)
        .readTimeout(1, TimeUnit.MINUTES)
        .writeTimeout(1, TimeUnit.MINUTES)
        .addInterceptor { chain ->
            val original = chain.request()
            val request = original.newBuilder().apply {
                addHeader("Content-Type", "application/json; version=1")
            }.build()
            chain.proceed(request)
        }
        .dispatcher(dispatcher)
        .cache(null)

    if (BuildConfig.DEBUG) {
        builder
            .addInterceptor(loggingInterceptor)
            .addInterceptor(OkHttpProfilerInterceptor())
    }

    return builder.build()
}