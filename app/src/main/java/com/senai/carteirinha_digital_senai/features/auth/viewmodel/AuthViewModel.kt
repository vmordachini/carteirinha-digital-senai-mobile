package com.senai.carteirinha_digital_senai.features.auth.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.senai.carteirinha_digital_senai.data.repository.AuthRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch

class AuthViewModel(private val repository: AuthRepository) : ViewModel() {

    // 1. Observa o Token
    val authToken = repository.authToken.stateIn(
        scope = viewModelScope,
        started = SharingStarted.WhileSubscribed(5000),
        initialValue = null
    )

    // 2. Estado de Carregamento (para desativar o botão enquanto aguarda a API)
    private val _isLoading = MutableStateFlow(false)
    val isLoading: StateFlow<Boolean> = _isLoading

    // 3. Estado de Erro (para mostrar na UI caso a API rejeite o login)
    private val _errorMessage = MutableStateFlow<String?>(null)
    val errorMessage: StateFlow<String?> = _errorMessage

    // Função que a Interface (LoginScreen) vai chamar
    fun fazerLogin(matricula: String, senha: String, onSuccess: () -> Unit) {
        viewModelScope.launch {
            _isLoading.value = true
            _errorMessage.value = null // Limpa erros anteriores

            val result = repository.fazerLoginNaApi(matricula, senha)

            if (result.isSuccess) {
                onSuccess() // Navega para a Carteirinha
            } else {
                // Pega a mensagem de erro que veio do Repository
                _errorMessage.value = result.exceptionOrNull()?.message ?: "Erro ao fazer login"
            }

            _isLoading.value = false
        }
    }

    fun fazerLogout() {
        viewModelScope.launch {
            repository.fazerLogout()
        }
    }

    // Ajuda a limpar o erro quando o utilizador volta a digitar
    fun limparErro() {
        _errorMessage.value = null
    }
}