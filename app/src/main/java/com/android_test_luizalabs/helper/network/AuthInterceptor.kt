package com.android_test_luizalabs.helper.network

import okhttp3.Interceptor
import okhttp3.Response

class AuthInterceptor : Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        val originalRequest = chain.request()
        val requestBuilder = originalRequest.newBuilder()
            .addHeader("Accept", "application/vnd.github+json")
            .addHeader("X-GitHub-Api-Version", "2022-11-28")
            .method(originalRequest.method, originalRequest.body)

        val request = requestBuilder.build()
        return chain.proceed(request)
    }
}