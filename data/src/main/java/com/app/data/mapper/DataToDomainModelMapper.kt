package com.app.data.mapper

import com.app.data.dataMoldels.WeatherForecastResponseDataModel
import com.app.data.dataMoldels.WeatherUpdateResponseDataModel
import com.app.data.dto.*
import com.app.domain.dto.*
import com.app.domain.model.WeatherForecastResponseDomainModel
import com.app.domain.model.WeatherUpdateResponseDomainModel


fun WeatherUpdateResponseDataModel.toDomain() = WeatherUpdateResponseDomainModel(
    coordinates = coordinates?.toDomain(),
    weather = convertList(weather),
    base = base,
    main = main?.toDomain(),
    visibility = visibility,
    wind = wind?.toDomain(),
    rain = rain?.toDomain(),
    snow = snow?.toDomain(),
    clouds = clouds?.toDomain(),
    dataCalculationTime = dataCalculationTime,
    sys = sys?.toDomain(),
    timezone = timezone,
    id = id,
    name = name,
    cod = cod
)

fun WeatherForecastResponseDataModel.toDomain() = WeatherForecastResponseDomainModel(
    cod = cod,
    message = message,
    noOfTimestamps = noOfTimestamps,
    list = convertWeatherUpdateList(list),
    cityContainer = cityContainer?.toDomain()
)

fun convertWeatherUpdateList(list: ArrayList<WeatherUpdateResponseDataModel>): ArrayList<WeatherUpdateResponseDomainModel> {
    val arrayList = ArrayList<WeatherUpdateResponseDomainModel>()
    for (weatherObj in list) {
        arrayList.add(weatherObj.toDomain())
    }
    return arrayList
}

fun convertList(weatherList: ArrayList<Weather>): ArrayList<DomainWeather> {
    val result = ArrayList<DomainWeather>()
    for (weatherObj in weatherList) {
        result.add(weatherObj.toDomain())
    }
    return result
}

fun Main.toDomain() = DomainMain(
    temperature = temperature,
    feelsLike = feelsLike,
    minimumTemperature = minimumTemperature,
    maximumTemperature = maximumTemperature,
    pressure = pressure,
    humidity = humidity,
    seaLevel = seaLevel,
    groundLevel = groundLevel,
    tempKf = tempKf
)

fun Coordinates.toDomain() = DomainCoordinates(
    longitude = longitude,
    latitude = latitude
)

fun Wind.toDomain() = DonainWind(
    speed = speed,
    degrees = degrees,
    gust = gust
)

fun Rain.toDomain() = DomainRain(
    lastOneHour = lastOneHour,
    lastThreeHours = lastThreeHours
)

fun Snow.toDomain() = DomainSnow(
    lastOneHour = lastOneHour,
    lastThreeHours = lastThreeHours
)

fun Sys.toDomain() = DomainSys(
    type = type,
    id = id,
    country = country,
    sunrise = sunrise,
    pod = pod
)

fun Clouds.toDomain() = DomainClouds(
    all = all
)

fun CityContainer.toDomain() = DomainCityContainer(
    id = id,
    name = name,
    coordinates = coordinates?.toDomain(),
    country = country,
    population = population,
    timezone = timezone,
    sunrise = sunrise,
    sunset = sunset
)

fun Weather.toDomain() = DomainWeather(
    id = id,
    main = main,
    description = description,
    icon = icon
)
