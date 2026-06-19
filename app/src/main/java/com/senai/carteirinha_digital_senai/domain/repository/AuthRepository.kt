package com.senai.carteirinha_digital_senai.domain.repository

import com.senai.carteirinha_digital_senai.data.remote.model.LoginResponse
import kotlinx.coroutines.flow.Flow

interface AuthRepository {
    val authToken: Flow<String?>
    suspend fun fazerLogin(matricula: String, senha: String): Result<LoginResponse>

    /** Login simulado offline: salva um token fictício. Remover quando a API existir. */
    suspend fun simularLogin()

    suspend fun fazerLogout()
}