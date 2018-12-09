package com.mroz.mateusz.mvvm_android_architecture_dagger2.web_api

import retrofit2.Response
import timber.log.Timber
import java.lang.NumberFormatException
import java.util.regex.Pattern


sealed class ApiResponse<T>{
    companion object {
        private const val emptyResponse = 204

        fun <T> create(error: Throwable): ApiErrorResponse<T> {
            return ApiErrorResponse(error.message ?: "unknown error")
        }

        fun <T> create(response : Response<T>): ApiResponse<T> {
            return if(response.isSuccessful) {
                val body = response.body()

                if(body == null || response.code() == emptyResponse) {
                    ApiEmptyResponse()
                } else {
                    ApiSuccessResponse(
                            body = body,
                            linkerHeader = response.headers()?.get("link")
                    )
                }
            } else {
                val msg = response.errorBody()?.string()
                ApiErrorResponse(msg ?: "unknown message")
            }
        }
    }
}

class ApiEmptyResponse<T> : ApiResponse<T>()

class ApiErrorResponse<T>(val errorMessage: String) : ApiResponse<T>()

 class ApiSuccessResponse<T>(
        val body: T,
        val links: Map<String, String>
): ApiResponse<T>() {

    constructor(body: T, linkerHeader: String?): this (
        body = body,
        links = linkerHeader?.extractLinks() ?: emptyMap()
    )

    val nextPage: Int? by lazy(LazyThreadSafetyMode.NONE) {
        links[NEXT_LINK]?.let { next ->
            val matcher = PAGE_PATTERN.matcher(next)
            if(!matcher.find() || matcher.groupCount() != 1) {
                null
            } else {
                try {
                    Integer.parseInt(matcher.group(1))
                } catch (e: NumberFormatException) {
                    Timber.w("cannot parse next page from %s", next)
                    null
                }
            }
        }
    }

    companion object {
        private val LINK_PATTERN = Pattern.compile("<([^>]*)>[\\s]*;[\\s]*rel=\"([a-zA-Z0-9]+)\"")
        private val PAGE_PATTERN = Pattern.compile("\\bpage=(\\d+)")
        private const val NEXT_LINK = "next"

        private fun String.extractLinks():Map<String, String> {
            val links = mutableMapOf<String, String>()
            val matcher = LINK_PATTERN.matcher(this)

            while(matcher.find()) {
                val count = matcher.groupCount()
                if(count == 2) {
                    links[matcher.group(2)] = matcher.group(1)
                }
            }
            return links
        }

    }
}


