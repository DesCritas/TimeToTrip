package com.descritas.timetotrip.error

sealed class AppError(var code: String): RuntimeException()
object NetworkError : AppError("error_network")
object UnknownError: AppError("error_unknown")
