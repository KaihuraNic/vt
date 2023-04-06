package com.app.domain.dto

data class DomainMain(
    var temperature: Double? = null,
    var feelsLike: Double? = null,
    var minimumTemperature: Double? = null,
    var maximumTemperature: Double? = null,
    var pressure: Int? = null,
    var humidity: Int? = null,
    var seaLevel: Int? = null,
    var groundLevel: Int? = null,
    var tempKf: Double? = null
)