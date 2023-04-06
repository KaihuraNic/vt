package com.app.data.dataMoldels

import com.app.data.dto.*
import com.google.gson.annotations.SerializedName

data class  WeatherUpdateResponseDataModel(
    @field:SerializedName("coord") var coordinates: Coordinates? = null,
    @field:SerializedName("weather") var weather: ArrayList<Weather> = arrayListOf(),
    @field:SerializedName("base") var base: String? = null,
    @field:SerializedName("main") var main: Main? = null,
    @field:SerializedName("visibility") var visibility: Int? = null,
    @field:SerializedName("wind") var wind: Wind? = null,
    @field:SerializedName("rain") var rain: Rain? = null,
    @field:SerializedName("snow") var snow: Snow? = null,
    @field:SerializedName("clouds") var clouds: Clouds? = null,
    @field:SerializedName("dt") var dataCalculationTime: Int? = null,
    @field:SerializedName("sys") var sys: Sys? = null,
    @field:SerializedName("timezone") var timezone: Int? = null,
    @field:SerializedName("id") var id: Int? = null,
    @field:SerializedName("name") var name: String? = null,
    @field:SerializedName("cod") var cod: Int? = null
)