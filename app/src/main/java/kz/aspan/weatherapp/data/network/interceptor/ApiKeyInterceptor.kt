package kz.aspan.weatherapp.data.network.interceptor

import kz.aspan.weatherapp.di.ApplicationScope
import okhttp3.Interceptor
import okhttp3.Response
import javax.inject.Inject
import javax.inject.Singleton

@ApplicationScope
class ApiKeyInterceptor @Inject constructor() : Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        val originalRequest = chain.request()
        val requestBuilder = originalRequest.newBuilder()
        requestBuilder.addHeader(KEY_HEADER, API_KEI)
        val request = requestBuilder.build()
        return chain.proceed(request)
    }

    private companion object {
        const val API_KEI = "4cd80af0c77740d3bfe54507231708"
        const val KEY_HEADER = "key"
    }
}