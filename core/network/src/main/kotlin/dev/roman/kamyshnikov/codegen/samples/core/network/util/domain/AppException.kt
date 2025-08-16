package dev.roman.kamyshnikov.codegen.samples.core.network.util.domain

sealed class AppException {
    object Generic: AppException()
    object Network: AppException()
}
