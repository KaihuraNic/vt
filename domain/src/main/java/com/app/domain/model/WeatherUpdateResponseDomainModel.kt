package com.app.domain.model

import com.app.domain.dto.*


data class WeatherUpdateResponseDomainModel(
    var coordinates: DomainCoordinates? = null,
    var weather: ArrayList<DomainWeather> = arrayListOf(),
    var base: String? = null,
    var main: DomainMain? = null,
    var visibility: Int? = null,
    var wind: DonainWind? = null,
    var rain: DomainRain? = null,
    var snow: DomainSnow? = null,
    var clouds: DomainClouds? = null,
    var dataCalculationTime: Int? = null,
    var sys: DomainSys? = null,
    var timezone: Int? = null,
    var id: Int? = null,
    var name: String? = null,
    var cod: Int? = null
)
