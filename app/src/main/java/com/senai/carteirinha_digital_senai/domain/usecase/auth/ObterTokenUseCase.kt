package com.senai.carteirinha_digital_senai.domain.usecase.auth

import com.senai.carteirinha_digital_senai.domain.repository.AuthRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class ObterTokenUseCase @Inject constructor(
    private val authRepository: AuthRepository
) {
    operator fun invoke(): Flow<String?> {
        return authRepository.authToken
    }
}