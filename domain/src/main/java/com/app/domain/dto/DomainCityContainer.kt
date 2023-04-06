package com.app.domain.dto

data class DomainCityContainer(
    var id: Int? = null,
    var name: String? = null,
    var coordinates: DomainCoordinates? = null,
    var country: String? = null,
    var population: Int? = null,
    var timezone: Int? = null,
    var sunrise: Int? = null,
    var sunset: Int? = null
)