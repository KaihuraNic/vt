package com.app.domain.useCase


interface BaseUseCaseOneInputOneOutput<in Parameter, out Result> {
    suspend operator fun invoke(params: Parameter): Result
}

interface BaseUseCaseTwoInputOneOutPut<in ParameterOne, in ParameterTwo, out Result> {
    suspend operator fun invoke(parOne: ParameterOne, parTwo: ParameterTwo): Result
}