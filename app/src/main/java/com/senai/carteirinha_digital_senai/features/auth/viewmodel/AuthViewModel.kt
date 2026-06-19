package com.senai.carteirinha_digital_senai.features.auth.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.senai.carteirinha_digital_senai.domain.usecase.auth.FazerLoginUseCase
import com.senai.carteirinha_digital_senai.domain.usecase.auth.FazerLogoutUseCase
import com.senai.carteirinha_digital_senai.domain.usecase.auth.ObterTokenUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AuthViewModel @Inject constructor(
    private val fazerLoginUseCase: FazerLoginUseCase,
    private val fazerLogoutUseCase: FazerLogoutUseCase,
    obterTokenUseCase: ObterTokenUseCase
) : ViewModel() {

    val authToken = obterTokenUseCase().stateIn(
        scope = viewModelScope,
        started = SharingStarted.WhileSubscribed(5000),
        initialValue = null
    )

    private val _errorMessage = MutableStateFlow<String?>(null)
    val errorMessage: StateFlow<String?> = _errorMessage.asStateFlow()

    private val _isLoading = MutableStateFlow(false)
    val isLoading: StateFlow<Boolean> = _isLoading.asStateFlow()

    /**
     * Login SIMULADO (sem API).
     * Como ainda não há backend, o botão "Entrar" sempre autentica com sucesso,
     * salvando um token fictício. Quando a API existir, basta voltar a chamar
     * fazerLoginUseCase(matricula, senha) e remover a simulação.
     */
    fun fazerLogin(matricula: String, senha: String, onSuccess: () -> Unit) {
        viewModelScope.launch {
            _isLoading.value = true
            _errorMessage.value = null

            // Pequeno delay só para dar feedback visual de carregamento.
            delay(400)

            // SIMULAÇÃO: sempre autentica.
            fazerLoginUseCase.simularLogin()

            _isLoading.value = false
            onSuccess()
        }
    }

    fun limparErro() {
        _errorMessage.value = null
    }

    fun fazerLogout() {
        viewModelScope.launch {
            fazerLogoutUseCase()
        }
    }
}