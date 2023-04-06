package com.app.data.api

import com.app.core.enums.ApiUnitOfMeasurement
import com.app.data.dataMoldels.WeatherForecastResponseDataModel
import com.app.data.dataMoldels.WeatherUpdateResponseDataModel
import retrofit2.http.GET
import retrofit2.http.Query

interface WeatherApi {

    @GET("weather")
    suspend fun getWeatherUpdate(
        @Query("lat") lat: String?,
        @Query("lon") lon: String?,
        @Query("units") units: ApiUnitOfMeasurement = ApiUnitOfMeasurement.METRIC,
        @Query("appid") clientId: String?
    ): WeatherUpdateResponseDataModel

    @GET("forecast")
    suspend fun getWeatherForecast(
        @Query("lat") lat: String?,
        @Query("lon") lon: String?,
        @Query("cnt") cnt: String?,
        @Query("units") units: ApiUnitOfMeasurement = ApiUnitOfMeasurement.METRIC,
        @Query("appid") clientId: String?
    ): WeatherForecastResponseDataModel

}