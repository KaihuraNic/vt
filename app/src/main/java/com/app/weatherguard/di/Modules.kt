package com.app.weatherguard.di

import android.content.Context.MODE_PRIVATE
import android.content.SharedPreferences
import com.app.data.cache.WeatherCacheApiImpl
import com.app.data.cache.sharedPrefs.SharedPrefRepositoryImpl
import com.app.data.cache.sharedPrefs.sharedPreferencesTitle
import com.app.data.providers.provideNetworkClient
import com.app.data.providers.provideRetrofit
import com.app.data.providers.provideWeatherApi
import com.app.data.repository.WeatherRepositoryImpl
import com.app.domain.repository.WeatherRepository
import com.app.domain.useCase.WeatherForecastUsaCase
import com.app.domain.useCase.WeatherUpdateUseCase
import com.app.presentation.util.DaysUtil
import com.app.presentation.viewModels.WeatherViewModel
import org.koin.android.ext.koin.androidApplication
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.core.qualifier.named
import org.koin.dsl.module


private val dataModule = module {
    single<WeatherRepository> { WeatherRepositoryImpl(get(), get()) }
    factory { DaysUtil() }
}

private val domainModule = module {
    factory { WeatherUpdateUseCase(get()) }
    factory { WeatherForecastUsaCase(get()) }
}

private val networkModule = module {
    single(named("retrofit")) { provideRetrofit(get(named("client"))) }
    factory { provideWeatherApi(get(named("retrofit"))) }
    factory(named("client")) { provideNetworkClient() }
}

private val presentationModule = module {
    viewModel { WeatherViewModel(get(), get(), get()) }
}

private val sharedPrefModule = module {
    factory {
        androidApplication().getSharedPreferences(sharedPreferencesTitle, MODE_PRIVATE)
    }

    single { SharedPrefRepositoryImpl(get()) }
}

private val cacheModule = module {
    factory { WeatherCacheApiImpl(get()) }
}
val myModules = listOf(sharedPrefModule, cacheModule, dataModule, domainModule, networkModule, presentationModule)
