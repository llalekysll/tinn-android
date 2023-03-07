package com.example.tinn.data.networkService

import okhttp3.Interceptor
import okhttp3.Response

object ServiceInterceptor : Interceptor {
    var token: String = ""

    override fun intercept(chain: Interceptor.Chain): Response {
        var request = chain.request()

        if (request.header("No-Authentication") == null) {
            if (!token.isNullOrEmpty()) {
                val finalToken = "Bearer $token"
                request = request.newBuilder()
                    .addHeader("Authorization", finalToken).build()
            }
        }
        return chain.proceed(request)
    }
}