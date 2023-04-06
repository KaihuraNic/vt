package com.app.domain.dto

import com.app.core.enums.PartOfDay

data class DomainSys(
    var type: Int? = null,
    var id: Int? = null,
    var country: String? = null,
    var sunrise: Int? = null,
    var sunset: Int? = null,
    var pod: PartOfDay? = null
)