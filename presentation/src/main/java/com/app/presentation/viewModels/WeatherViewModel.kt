package com.app.presentation.viewModels

import android.content.Context
import androidx.databinding.ObservableField
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.app.core.Result
import com.app.core.enums.ApiUnitOfMeasurement
import com.app.domain.model.WeatherForecastRequestDomainModel
import com.app.domain.model.WeatherForecastResponseDomainModel
import com.app.domain.model.WeatherUpdateRequestDomainModel
import com.app.domain.model.WeatherUpdateResponseDomainModel
import com.app.domain.useCase.WeatherForecastUsaCase
import com.app.domain.useCase.WeatherUpdateUseCase
import com.app.presentation.R
import com.app.presentation.theme.AppTheme
import com.app.presentation.util.DataHolder
import com.app.presentation.util.DaysUtil
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class WeatherViewModel(
    private val weatherUpdateUseCase: WeatherUpdateUseCase,
    private val weatherForecastUsaCase: WeatherForecastUsaCase,
    private val daysUtil: DaysUtil
) : ViewModel() {
    //constants
    var temperatureSymbol = "\u00B0"
    val minTemp = "\nmin"
    val currentTemp = "\ncurrent"
    val maxTemp = "\nmax"

    private lateinit var context: Context

    val newTheme = MutableLiveData<Int>()
    val newColor = MutableLiveData<Int>(R.color.rainy)
    val weatherUpdateLiveData = MutableLiveData<WeatherUpdateResponseDomainModel>()
    var observableWeatherUpdate = ObservableField<WeatherUpdateResponseDomainModel>()
    var observableWeatherForecast = ObservableField<WeatherForecastResponseDomainModel>()

    var observableErrorMessage = ObservableField<String>()
    var observableLoadingState = ObservableField<Boolean>()
    var observableNextFiveDays = ObservableField<List<String>>()
    var observableWeatherIcons = ObservableField<List<Int>>()

    var themeStateLiveData = MutableLiveData<AppTheme>(AppTheme.FOREST)
    var weatherUpdateTypeLiveData = MutableLiveData<String>()


    fun getWeatherUpdate(context: Context) {
        observableLoadingState.set(true)
        viewModelScope.launch(Dispatchers.IO) {
            when (val result = context?.let { weatherUpdateUseCase.invoke(it, WeatherUpdateRequestDomainModel("0.0515", "37.6456", ApiUnitOfMeasurement.METRIC, "90ec66d1d67422ec95a07cf778391432")) }) {
                is Result.Success -> {
                    updateDataCaches(result)
                }
                is Result.Error -> {
                    observableLoadingState.set(false)
                    observableErrorMessage.set(result.throwableMessage)
                }
                else -> {
                    observableLoadingState.set(false)
                    observableErrorMessage.set("Unknown Error! Please try again later")
                }
            }
        }
    }

    fun getWeatherForeCast(context: Context?) {
        viewModelScope.launch(Dispatchers.IO) {
            when (val result = context?.let { weatherForecastUsaCase.invoke(it, WeatherForecastRequestDomainModel("0.0515", "37.6456", "5", ApiUnitOfMeasurement.METRIC, "90ec66d1d67422ec95a07cf778391432")) }) {
                is Result.Success -> {
                    updateDaysAndIcons(result)
                    observableWeatherForecast.set(result.data)
                    println("===========Forecast===============")
                    println(result.data.list.size)
                    println("==========================")
                }
                is Result.Error -> {
                    observableErrorMessage.set(result.throwableMessage)
                }
                else -> {}
            }
        }
    }

    private fun updateDataCaches(result: Result.Success<WeatherUpdateResponseDomainModel>) {
        observableLoadingState.set(false)
        DataHolder.saveUpdate(result.data)
        weatherUpdateLiveData.postValue(result.data)
        observableWeatherUpdate.set(result.data)
        weatherUpdateTypeLiveData.postValue(result.data.weather[0].main)
    }

    private fun updateDaysAndIcons(result: Result.Success<WeatherForecastResponseDomainModel>) {
        observableNextFiveDays.set(daysUtil.getNextFiveDays())
        val listOfIcons = mutableListOf<Int>()
        for (i in 0..4) {
            if (result.data.list[i].weather[0].main.equals("Clouds")) {
                listOfIcons.add(R.drawable.cloudy_main)
            } else if (result.data.list[i].weather[0].main.equals("Rain")) {
                listOfIcons.add(R.drawable.rain_main)
            } else if (result.data.list[i].weather[0].main.equals("Clear")) {
                listOfIcons.add(R.drawable.clear_main)
            }
        }
        observableWeatherIcons.set(listOfIcons)
    }

    fun updateTheme() {
        if (observableWeatherUpdate.get() == null) {
            observableWeatherUpdate.set(DataHolder.getUpdate())
        }
        if (observableWeatherUpdate.get()?.weather?.get(0)?.main.equals("Clear") && themeStateLiveData.value!! == AppTheme.FOREST) {
            newTheme.postValue(R.drawable.forest_sunny)
            newColor.postValue(R.color.sunny)
        } else if (observableWeatherUpdate.get()?.weather?.get(0)?.main.equals("Clouds") && themeStateLiveData.value!! == AppTheme.FOREST) {
            newTheme.postValue(R.drawable.forest_cloudy)
            newColor.postValue(R.color.cloudy)
        } else if (observableWeatherUpdate.get()?.weather?.get(0)?.main.equals("Rain") && themeStateLiveData.value!! == AppTheme.FOREST) {
            newTheme.postValue(R.drawable.forest_rainy)
            newColor.postValue(R.color.rainy)
        } else if (observableWeatherUpdate.get()?.weather?.get(0)?.main.equals("Clear") && themeStateLiveData.value!! == AppTheme.SEA) {
            newTheme.postValue(R.drawable.sea_sunnypng)
            newColor.postValue(R.color.sunny)
        } else if (observableWeatherUpdate.get()?.weather?.get(0)?.main.equals("Clouds") && themeStateLiveData.value!! == AppTheme.SEA) {
            newTheme.postValue(R.drawable.sea_cloudy)
            newColor.postValue(R.color.cloudy)
        } else if (observableWeatherUpdate.get()?.weather?.get(0)?.main.equals("Rain") && themeStateLiveData.value!! == AppTheme.SEA) {
            newTheme.postValue(R.drawable.sea_rainy)
            newColor.postValue(R.color.rainy)
        }
    }

}