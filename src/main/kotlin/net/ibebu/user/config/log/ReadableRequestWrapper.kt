package net.ibebu.user.config.log

import jakarta.servlet.ReadListener
import jakarta.servlet.ServletInputStream
import jakarta.servlet.http.HttpServletRequest
import jakarta.servlet.http.HttpServletRequestWrapper
import java.io.*
import java.nio.charset.Charset
import java.nio.charset.StandardCharsets
import java.util.*


class ReadableRequestWrapper(request: HttpServletRequest) : HttpServletRequestWrapper(request) {

    private val encoding: Charset
    private val parameterMap: Map<String, Array<String>>
    private val rawData: ByteArray

    init {
        val encoding = request.characterEncoding
        this.encoding = if (encoding.isNullOrEmpty()) StandardCharsets.UTF_8 else Charset.forName(encoding)
        this.parameterMap = HashMap(request.parameterMap)
        this.rawData = request.inputStream.readAllBytes()
    }

    fun getContentAsByteArray(): ByteArray {
        return rawData
    }

    @Throws(IOException::class)
    override fun getInputStream(): ServletInputStream {
        return ReadableInputStream(ByteArrayInputStream(rawData))
    }

    @Throws(IOException::class)
    override fun getReader(): BufferedReader {
        return BufferedReader(InputStreamReader(inputStream, encoding))
    }

    override fun getParameter(name: String): String? {
        val values = parameterMap[name]
        return if (values.isNullOrEmpty()) null else values[0]
    }

    override fun getParameterMap(): Map<String, Array<String>> {
        return parameterMap
    }

    override fun getParameterNames(): Enumeration<String> {
        return Collections.enumeration(parameterMap.keys)
    }

    override fun getParameterValues(name: String): Array<String>? {
        val values = parameterMap[name]
        return if (values.isNullOrEmpty()) values else values.copyOf()
    }

    private class ReadableInputStream (private val inputStream: InputStream) : ServletInputStream() {
        override fun isFinished(): Boolean {
            return false
        }

        override fun isReady(): Boolean {
            return true
        }

        override fun setReadListener(readListener: ReadListener) {
            throw UnsupportedOperationException()
        }

        @Throws(IOException::class)
        override fun read(): Int {
            return inputStream.read()
        }
    }
}
