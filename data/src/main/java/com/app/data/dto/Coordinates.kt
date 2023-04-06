package com.app.data.dto

import com.google.gson.annotations.SerializedName

data class Coordinates(
    @field:SerializedName("lon") var longitude: String? = null,
    @field:SerializedName("lat") var latitude: String? = null
)