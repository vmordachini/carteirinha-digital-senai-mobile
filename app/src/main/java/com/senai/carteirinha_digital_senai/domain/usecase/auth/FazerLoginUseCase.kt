package com.senai.carteirinha_digital_senai.domain.usecase.auth

import com.senai.carteirinha_digital_senai.data.remote.model.LoginResponse
import com.senai.carteirinha_digital_senai.domain.repository.AuthRepository
import javax.inject.Inject

class FazerLoginUseCase @Inject constructor(
    private val authRepository: AuthRepository
) {
    suspend operator fun invoke(matricula: String, senha: String): Result<LoginResponse> {
        // futuramente você pode adicionar validações, ex: se a senha tem 6 caracteres
        return authRepository.fazerLogin(matricula, senha)
    }
}