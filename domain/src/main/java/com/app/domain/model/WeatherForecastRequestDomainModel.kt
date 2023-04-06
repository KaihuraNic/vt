package com.app.domain.model

import com.app.core.enums.ApiUnitOfMeasurement

data class WeatherForecastRequestDomainModel(
    var lat: String?,
    var lon: String?,
    var cnt: String?,
    var units: ApiUnitOfMeasurement,
    var appid: String?,
)