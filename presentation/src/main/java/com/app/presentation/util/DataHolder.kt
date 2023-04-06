package com.app.presentation.util

import com.app.domain.model.WeatherUpdateResponseDomainModel

object DataHolder {
    init {

    }

    private var weatherUpdate: WeatherUpdateResponseDomainModel? = null
    const val apiKey = "AIzaSyDn4EWWHzqFgE2As3c_yVeFNYogjm7kymM"

    fun getUpdate(): WeatherUpdateResponseDomainModel? {
        return weatherUpdate
    }

    fun saveUpdate(weatherUpdateResponseDomainModel: WeatherUpdateResponseDomainModel) {
        this.weatherUpdate = weatherUpdateResponseDomainModel
    }
}