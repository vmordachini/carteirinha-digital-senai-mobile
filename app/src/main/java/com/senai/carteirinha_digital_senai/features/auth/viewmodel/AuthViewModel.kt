package com.senai.carteirinha_digital_senai.features.auth.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.senai.carteirinha_digital_senai.domain.repository.AuthRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AuthViewModel @Inject constructor(
    private val authRepository: AuthRepository
) : ViewModel() {

    val authToken = authRepository.authToken.stateIn(
        scope = viewModelScope,
        started = SharingStarted.WhileSubscribed(5000),
        initialValue = null
    )

    fun login(matricula: String, senha: String, onResult: (Boolean) -> Unit) {
        viewModelScope.launch {
            val result = authRepository.fazerLogin(matricula, senha)
            onResult(result.isSuccess)
        }
    }

    fun fazerLogout() {
        viewModelScope.launch {
            authRepository.fazerLogout()
        }
    }
}