package com.app.data.repository

import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import android.os.Build
import com.app.data.api.WeatherApi
import com.app.data.cache.WeatherCacheApiImpl
import com.app.data.dataMoldels.WeatherForecastResponseDataModel
import com.app.data.dataMoldels.WeatherUpdateResponseDataModel
import com.app.data.mapper.toDomain
import com.app.data.util.safeApiCall
import com.app.domain.model.WeatherForecastRequestDomainModel
import com.app.domain.model.WeatherForecastResponseDomainModel
import com.app.domain.model.WeatherUpdateRequestDomainModel
import com.app.domain.model.WeatherUpdateResponseDomainModel
import com.app.domain.repository.WeatherRepository
import com.google.gson.Gson

class WeatherRepositoryImpl(private val weatherApi: WeatherApi, private val weatherCacheApi: WeatherCacheApiImpl) : WeatherRepository {
    override suspend fun getWeatherUpdate(context: Context, param: WeatherUpdateRequestDomainModel) = safeApiCall(
        apiCall = {
            if (hasInternet(context)) {
                saveWeatherUpdate(
                    weatherApi.getWeatherUpdate(
                        param.lat,
                        param.lon,
                        param.units,
                        param.appid
                    ).toDomain()
                )
            } else {
                Gson().fromJson(weatherCacheApi.getWeatherUpdate(), WeatherUpdateResponseDataModel::class.java).toDomain()
            }
        }

    )

    override suspend fun getWeatherForecast(context: Context, param: WeatherForecastRequestDomainModel) = safeApiCall(
        apiCall = {
            if (hasInternet(context)) {
                saveWeatherForecast(
                    weatherApi.getWeatherForecast(
                        param.lat,
                        param.lon,
                        param.cnt,
                        param.units,
                        param.appid
                    ).toDomain()
                )
            } else
                Gson().fromJson(weatherCacheApi.getWeatherForecast(), WeatherForecastResponseDataModel::class.java).toDomain()
        }
    )

    private fun saveWeatherForecast(result: WeatherForecastResponseDomainModel): WeatherForecastResponseDomainModel {
        weatherCacheApi.saveWeatherForecast(result)
        return result
    }

    private fun saveWeatherUpdate(result: WeatherUpdateResponseDomainModel): WeatherUpdateResponseDomainModel {
        weatherCacheApi.saveWeatherUpdate(result)
        return result
    }

//    private fun cacheIsStale(): Boolean {
//        return if (weatherCacheApi.getLastUpdate().isEmpty()){
//            true
//        }else {
//            val dateFormat = SimpleDateFormat(dateFormatPattern)
//            val lastUpdate: Date = dateFormat.parse(weatherCacheApi.getLastUpdate()) as Date
//            val currentDate: Date = dateFormat.parse(dateFormat.format(Date())) as Date
//            val secs: Long = (currentDate.time - lastUpdate.time) / 1000
//            val hours = (secs / 3600).toInt()
//            hours > 1
//        }
//    }

    private fun hasInternet(context: Context): Boolean {
        val connectivityManager = context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            val network = connectivityManager.activeNetwork ?: return false
            val activeNetwork = connectivityManager.getNetworkCapabilities(network) ?: return false
            return when {
                activeNetwork.hasTransport(NetworkCapabilities.TRANSPORT_WIFI) -> true
                activeNetwork.hasTransport(NetworkCapabilities.TRANSPORT_CELLULAR) -> true
                else -> false
            }
        } else {
            @Suppress("DEPRECATION") val networkInfo =
                connectivityManager.activeNetworkInfo ?: return false
            @Suppress("DEPRECATION")
            return networkInfo.isConnected
        }
    }

}
