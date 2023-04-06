package com.app.data.providers

import com.app.data.api.WeatherApi
import retrofit2.Retrofit

fun provideWeatherApi(retrofit: Retrofit): WeatherApi = retrofit.create(WeatherApi::class.java)