package com.app.domain.repository

import android.content.Context
import com.app.core.Result
import com.app.domain.model.*

interface WeatherRepository {
    suspend fun getWeatherUpdate(context: Context,param: WeatherUpdateRequestDomainModel): Result<WeatherUpdateResponseDomainModel>
    suspend fun getWeatherForecast(context: Context,param: WeatherForecastRequestDomainModel): Result<WeatherForecastResponseDomainModel>
}