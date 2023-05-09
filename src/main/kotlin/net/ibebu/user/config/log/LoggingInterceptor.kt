package net.ibebu.user.config.log

import com.fasterxml.jackson.databind.JsonNode
import com.fasterxml.jackson.databind.ObjectMapper
import jakarta.servlet.http.HttpServletRequest
import jakarta.servlet.http.HttpServletResponse
import net.ibebu.user.common.Logging
import org.springframework.stereotype.Component
import org.springframework.web.servlet.AsyncHandlerInterceptor
import org.springframework.web.servlet.ModelAndView
import org.springframework.web.util.ContentCachingResponseWrapper


@Component
class LoggingInterceptor(private val objectMapper: ObjectMapper) : AsyncHandlerInterceptor {

    companion object : Logging

    override fun preHandle(request: HttpServletRequest, response: HttpServletResponse, handler: Any): Boolean {
        logger.info(
            ">>>>> REQUEST {} [{}] / Parameter Map: {} / Request header: {} / Request Body: {}",
            request.method,
            request.requestURI + extractQueryString(request),
            extractRequestParameters(request),
            extractRequestHeader(request),
            extractRequestBody(request)
        )

        return true
    }

    private fun extractQueryString(request: HttpServletRequest): String {
        val queryString = request.queryString

        return if (!queryString.isNullOrEmpty()) "?$queryString" else ""
    }

    private fun extractRequestParameters(request: HttpServletRequest): String {
        val parameterMap = request.parameterMap
        val parameters: JsonNode = objectMapper.valueToTree(parameterMap)

        return parameters.toString()
    }

    private fun extractRequestHeader(request: HttpServletRequest): String {
        if (request !is ReadableRequestWrapper) {
            return ""
        }

        var headerNameString = "";
        var headers = request.headerNames
        while (headers.hasMoreElements()) {
            (headers.nextElement() as String).let {
                headerNameString += " $it = ${request.getHeader(it)}"
            }
        }
        return headerNameString
    }

    private fun extractRequestBody(request: HttpServletRequest): String {
        if (request !is ReadableRequestWrapper) {
            return ""
        }

        return formatBody(request.getContentAsByteArray())
    }

    @Throws(Exception::class)
    override fun postHandle(
        request: HttpServletRequest,
        response: HttpServletResponse,
        handler: Any,
        modelAndView: ModelAndView?,
    ) {
        logger.info(
            "<<<<< RESPONSE {} [{}] ({}) / Response Body: {}",
            request.method,
            request.requestURI + extractQueryString(request),
            response.status,
            extractResponseBody(response)
        )
    }

    private fun extractResponseBody(response: HttpServletResponse): String {
        if (response !is ContentCachingResponseWrapper) {
            return ""
        }

        return formatBody(response.contentAsByteArray)
    }

    private fun formatBody(bodyRawData: ByteArray): String {
        return if (bodyRawData.isNotEmpty()) String(bodyRawData).replace("\n", "") else ""
    }
}
