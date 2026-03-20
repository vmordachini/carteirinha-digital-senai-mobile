package com.senai.carteirinha_digital_senai.data.remote

import com.senai.carteirinha_digital_senai.data.local.DataStoreManager
import com.senai.carteirinha_digital_senai.data.remote.api.AuthApi
import com.senai.carteirinha_digital_senai.data.remote.api.CarteirinhaApi
import com.senai.carteirinha_digital_senai.data.remote.interceptor.AuthInterceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitClient {
    private const val BASE_URL = "https://api.exemplo-senai.com.br/v1/"
    private var retrofit: Retrofit? = null

    // Agora passamos o Context para poder inicializar o Interceptor
    fun getApi(dataStoreManager: DataStoreManager): AuthApi {
        if (retrofit == null) {
            val loggingInterceptor = HttpLoggingInterceptor().apply {
                level = HttpLoggingInterceptor.Level.BODY
            }

            val authInterceptor = AuthInterceptor(dataStoreManager)

            val okHttpClient = OkHttpClient.Builder()
                .addInterceptor(loggingInterceptor)
                .addInterceptor(authInterceptor) // Aqui acoplamos o nosso injetor de Token!
                .build()

            retrofit = Retrofit.Builder()
                .baseUrl(BASE_URL)
                .client(okHttpClient)
                .addConverterFactory(GsonConverterFactory.create())
                .build()
        }
        return retrofit!!.create(AuthApi::class.java)
    }

    fun getCarteirinhaApi(dataStoreManager: DataStoreManager): CarteirinhaApi {
        // Garantimos que o Retrofit foi inicializado chamando o getApi primeiro (ou isolando a lógica de build)
        if (retrofit == null) {
            getApi(dataStoreManager)
        }
        return retrofit!!.create(CarteirinhaApi::class.java)
    }
}
