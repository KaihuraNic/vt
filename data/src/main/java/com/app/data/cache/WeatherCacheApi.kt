package com.app.data.cache

import com.app.domain.model.WeatherForecastResponseDomainModel
import com.app.domain.model.WeatherUpdateResponseDomainModel

interface WeatherCacheApi {
    fun saveCities()
    fun getWeatherUpdate(): String
    fun getWeatherForecast():String
    fun saveWeatherUpdate(weatherUpdateResponseDataModel: WeatherUpdateResponseDomainModel)
    fun saveWeatherForecast(weatherForecastResponseDataModel: WeatherForecastResponseDomainModel)
    fun getLastUpdate(): String
}