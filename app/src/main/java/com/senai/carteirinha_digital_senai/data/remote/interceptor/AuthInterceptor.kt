package com.senai.carteirinha_digital_senai.data.remote.interceptor

import com.senai.carteirinha_digital_senai.data.local.DataStoreManager
import okhttp3.Interceptor
import okhttp3.Response

class AuthInterceptor(private val dataStoreManager: DataStoreManager) : Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        val requestBuilder = chain.request().newBuilder()

        // Leitura síncrona diretamente da memória (evita deadlock)
        val token = dataStoreManager.getTokenSync()

        if (!token.isNullOrEmpty()) {
            requestBuilder.addHeader("Authorization", "Bearer $token")
        }

        return chain.proceed(requestBuilder.build())
    }
}