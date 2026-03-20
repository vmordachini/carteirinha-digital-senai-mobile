package com.senai.carteirinha_digital_senai.data.remote.api

import com.senai.carteirinha_digital_senai.data.remote.model.LoginRequest
import com.senai.carteirinha_digital_senai.data.remote.model.LoginResponse
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.POST

interface AuthApi {
    // Define a rota de login na API (ex: https://api.senai.br/v1/auth/login)
    @POST("auth/login")
    suspend fun fazerLogin(@Body request: LoginRequest): Response<LoginResponse>
}