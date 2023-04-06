package com.app.domain.model

import com.app.domain.dto.DomainCityContainer


data class WeatherForecastResponseDomainModel(
    var cod: String? = null,
    var message: Int? = null,
    var noOfTimestamps: Int? = null,
    var list: ArrayList<WeatherUpdateResponseDomainModel> = arrayListOf(),
    var cityContainer: DomainCityContainer? = null
)