package com.senai.carteirinha_digital_senai.domain.usecase.auth

import com.senai.carteirinha_digital_senai.data.remote.model.LoginResponse
import com.senai.carteirinha_digital_senai.domain.repository.AuthRepository
import javax.inject.Inject

class FazerLoginUseCase @Inject constructor(
    private val authRepository: AuthRepository
) {
    suspend operator fun invoke(matricula: String, senha: String): Result<LoginResponse> {
        return authRepository.fazerLogin(matricula, senha)
    }

    /** Login simulado offline (sem API). */
    suspend fun simularLogin() {
        authRepository.simularLogin()
    }
}