package com.senai.carteirinha_digital_senai.data.remote.interceptor

import androidx.datastore.preferences.core.stringPreferencesKey
import com.senai.carteirinha_digital_senai.data.local.DataStoreManager
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.runBlocking
import okhttp3.Interceptor
import okhttp3.Response


class AuthInterceptor(private val dataStoreManager: DataStoreManager) : Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        val requestBuilder = chain.request().newBuilder()

        // Lemos o token do DataStore de forma síncrona (bloqueando a thread da rede)
        val tokenKey = stringPreferencesKey("jwt_token")
        val token = runBlocking {
            dataStoreManager.authToken.first()
        }

        // Se existir um token salvo, adicionamos ao cabeçalho (Header) da requisição
        if (!token.isNullOrEmpty()) {
            requestBuilder.addHeader("Authorization", "Bearer $token")
        }

        return chain.proceed(requestBuilder.build())
    }
}