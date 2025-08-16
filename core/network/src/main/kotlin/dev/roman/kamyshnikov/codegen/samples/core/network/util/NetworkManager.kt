package dev.roman.kamyshnikov.codegen.samples.core.network.util

import arrow.core.Either
import dev.roman.kamyshnikov.codegen.samples.core.network.util.domain.AppException
import retrofit2.Response
import javax.inject.Inject
import kotlin.coroutines.cancellation.CancellationException

class NetworkManager @Inject constructor(
    private val errorMapper: ErrorMapper,
    private val logger: Logger
) {
    suspend fun <T> run(logTag: String, call: suspend () -> Response<T>): Either<AppException, T> {
        return try {
            val response = call.invoke()
            if (response.isSuccessful) {
                val body = response.body()
                if (body != null) {
                    return Either.Right(body)
                }
            }
            errorMapper.mapResponse(response)
        } catch (e: CancellationException) {
            logger.log(e)
            throw e
        } catch (e: Exception) {
            logger.log(e)
            errorMapper.map(e)
        }
    }
}
