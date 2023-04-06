package com.app.data.dataMoldels

import com.app.data.dto.CityContainer
import com.google.gson.annotations.SerializedName

data class WeatherForecastResponseDataModel(
    @field:SerializedName("cod") var cod: String? = null,
    @field:SerializedName("message") var message: Int? = null,
    @field:SerializedName("cnt") var noOfTimestamps: Int? = null,
    @field:SerializedName("list") var list: ArrayList<WeatherUpdateResponseDataModel> = arrayListOf(),
    @field:SerializedName("city") var cityContainer: CityContainer? = null
)