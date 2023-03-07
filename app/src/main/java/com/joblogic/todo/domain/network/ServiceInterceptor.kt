package com.joblogic.todo.domain.network

import okhttp3.Interceptor
import okhttp3.Response
import javax.inject.Inject


class ServiceInterceptor @Inject constructor(
) : Interceptor {

    override fun intercept(chain: Interceptor.Chain): Response {
        var request = chain.request()

        return try {
            request = request.newBuilder()
                .addHeader("osType", "android")
                .addHeader(
                    "appVersion",
                    "BuildConfig.VERSION_NAME"
                )
                .build()
            chain.proceed(request)
        } catch (e: Exception) {
            chain.proceed(request)
        }
    }

}