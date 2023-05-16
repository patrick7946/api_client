package net.ibebu.user.config

import net.ibebu.user.common.Logging
import net.ibebu.user.common.data.FailResponse
import org.springframework.http.HttpHeaders
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestControllerAdvice(annotations = [RestController::class])
class RestExceptionHandler {

    companion object : Logging
    @ExceptionHandler(RuntimeException::class)
    protected fun handleRuntimeException(ex: RuntimeException?): ResponseEntity<FailResponse> {
        logger.error("Runtime Exception", ex)
        return handle(HttpStatus.INTERNAL_SERVER_ERROR, FailResponse.of(ex))
    }

    @ExceptionHandler(Exception::class)
    protected fun handleException(ex: Exception?): ResponseEntity<FailResponse> {
        logger.error("Exception", ex)
        return handle(HttpStatus.INTERNAL_SERVER_ERROR, FailResponse.of(ex))
    }

    private fun handle(httpStatus: HttpStatus, failResponse: FailResponse): ResponseEntity<FailResponse> {
        return ResponseEntity
            .status(httpStatus)
            .headers(HttpHeaders())
            .body(failResponse)
    }
}
