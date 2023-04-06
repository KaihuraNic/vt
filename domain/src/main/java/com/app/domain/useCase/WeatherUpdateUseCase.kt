package com.app.domain.useCase

import android.content.Context
import com.app.domain.repository.WeatherRepository
import com.app.core.Result
import com.app.domain.model.WeatherUpdateResponseDomainModel
import com.app.domain.model.WeatherUpdateRequestDomainModel

typealias  BaseWeatherUpdateUseCase = BaseUseCaseTwoInputOneOutPut<Context, WeatherUpdateRequestDomainModel, Result<WeatherUpdateResponseDomainModel>>

class WeatherUpdateUseCase(
    private val weatherDomainRepository: WeatherRepository
) : BaseWeatherUpdateUseCase {
    override suspend fun invoke(parOne: Context, parTwo: WeatherUpdateRequestDomainModel) = weatherDomainRepository.getWeatherUpdate(parOne, parTwo)
}
