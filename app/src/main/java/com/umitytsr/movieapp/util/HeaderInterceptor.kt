package com.umitytsr.movieapp.util

import okhttp3.Interceptor
import okhttp3.Response

class HeaderInterceptor : Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        val request = chain.request().newBuilder()
            .addHeader(Constants.AUTHORIZATION, Constants.AUTHORIZATION_KEY)
            .build()
        return chain.proceed(request)
    }
}