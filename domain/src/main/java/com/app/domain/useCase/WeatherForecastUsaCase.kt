package com.app.domain.useCase

import android.content.Context
import com.app.domain.repository.WeatherRepository
import com.app.core.Result
import com.app.domain.model.WeatherForecastRequestDomainModel
import com.app.domain.model.WeatherForecastResponseDomainModel


typealias BaseWeatherForeCastUseCase = BaseUseCaseTwoInputOneOutPut<Context, WeatherForecastRequestDomainModel, Result<WeatherForecastResponseDomainModel>>

class WeatherForecastUsaCase(
    private val weatherDomainRepository: WeatherRepository
) : BaseWeatherForeCastUseCase {
    override suspend fun invoke(parOne: Context, parTwo: WeatherForecastRequestDomainModel): Result<WeatherForecastResponseDomainModel> = weatherDomainRepository.getWeatherForecast(parOne, parTwo)
}