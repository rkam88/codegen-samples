package dev.roman.kamyshnikov.codegen.samples.core.network.util

import arrow.core.Either
import dev.roman.kamyshnikov.codegen.samples.core.network.util.domain.AppException
import retrofit2.Response
import javax.inject.Inject

class ErrorMapper @Inject constructor() {
    fun <T> mapResponse(response: Response<T>): Either.Left<AppException> {
        return Either.Left(AppException.Generic)
    }

    fun map(exception: Exception): Either.Left<AppException> {
        return Either.Left(AppException.Generic)
    }
}
