package com.app.data.util

import com.app.core.emptyIfNull
import  com.app.core.Result

suspend fun <T : Any> safeApiCall(
    apiCall: suspend () -> T
): Result<T> = try {
    Result.Success(apiCall.invoke())
} catch (throwable: Throwable) {
    Result.Error(throwable.message.emptyIfNull())
}
