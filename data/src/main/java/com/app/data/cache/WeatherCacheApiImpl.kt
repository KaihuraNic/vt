package com.app.data.cache

import com.app.core.dateFormatPattern
import com.app.data.cache.sharedPrefs.SharedPrefRepositoryImpl
import com.app.data.cache.sharedPrefs.lastUpdate
import com.app.data.cache.sharedPrefs.weatherForecast
import com.app.data.cache.sharedPrefs.weatherUpdate
import com.app.domain.model.WeatherForecastResponseDomainModel
import com.app.domain.model.WeatherUpdateResponseDomainModel
import com.google.gson.Gson
import java.text.SimpleDateFormat
import java.util.*

class WeatherCacheApiImpl(private val sharedPrefRepository: SharedPrefRepositoryImpl) : WeatherCacheApi {

    override fun saveWeatherUpdate(weatherUpdateResponseDataModel: WeatherUpdateResponseDomainModel) {
        sharedPrefRepository.deleteString(lastUpdate)
        sharedPrefRepository.deleteString(weatherUpdate)
        val saved  = sharedPrefRepository.saveString(SimpleDateFormat(dateFormatPattern).format(Date()), lastUpdate)
        if (!saved){
            sharedPrefRepository.saveString(SimpleDateFormat(dateFormatPattern).format(Date()), lastUpdate)
        }

        println("------------------saving weateher update to cache--------------------------")
        println(Gson().toJson(weatherUpdateResponseDataModel))
        println("----------------------0000000000000000----------------------")
        sharedPrefRepository.saveString(Gson().toJson(weatherUpdateResponseDataModel), weatherUpdate)
    }

    override fun saveWeatherForecast(weatherForecastResponseDataModel: WeatherForecastResponseDomainModel) {
        sharedPrefRepository.deleteString(weatherForecast)
        println("------------------saving weather forecast to cache--------------------------")
        println(Gson().toJson(Gson().toJson(weatherForecastResponseDataModel)))
        println("----------------------=======================----------------------")
        val saved = sharedPrefRepository.saveString(Gson().toJson(weatherForecastResponseDataModel), weatherForecast)
        if (!saved) {
            sharedPrefRepository.saveString(Gson().toJson(weatherForecastResponseDataModel), weatherForecast)
        }
    }

    override fun getLastUpdate(): String {
        return sharedPrefRepository.getString(lastUpdate)
    }

    override fun saveCities() {
        TODO("Not yet implemented")
    }

    override fun getWeatherUpdate(): String {
        println("------------------update from cache--------------------------")
        println(sharedPrefRepository.getString(weatherUpdate))
        println("--------------------------------------------")
        return sharedPrefRepository.getString(weatherUpdate)
    }

    override fun getWeatherForecast(): String {

        println("-------------------forecast from cache-------------------------")
        println(sharedPrefRepository.getString(weatherForecast))
        println("--------------------------------------------")
        return sharedPrefRepository.getString(weatherForecast)
    }

}