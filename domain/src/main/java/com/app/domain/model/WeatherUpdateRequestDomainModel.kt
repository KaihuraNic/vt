package com.app.domain.model

import com.app.core.enums.ApiUnitOfMeasurement

data class WeatherUpdateRequestDomainModel(
    var lat: String?,
    var lon: String?,
    var units: ApiUnitOfMeasurement,
    var appid: String?,
)