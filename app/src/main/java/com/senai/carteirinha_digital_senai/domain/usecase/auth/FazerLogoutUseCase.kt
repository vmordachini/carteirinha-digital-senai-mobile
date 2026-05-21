package com.senai.carteirinha_digital_senai.domain.usecase.auth

import com.senai.carteirinha_digital_senai.domain.repository.AuthRepository
import javax.inject.Inject

class FazerLogoutUseCase @Inject constructor(
    private val authRepository: AuthRepository
) {
    suspend operator fun invoke() {
        authRepository.fazerLogout()
    }
}