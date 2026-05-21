package com.senai.carteirinha_digital_senai.features.auth.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.senai.carteirinha_digital_senai.domain.usecase.auth.FazerLoginUseCase
import com.senai.carteirinha_digital_senai.domain.usecase.auth.FazerLogoutUseCase
import com.senai.carteirinha_digital_senai.domain.usecase.auth.ObterTokenUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AuthViewModel @Inject constructor(
    private val fazerLoginUseCase: FazerLoginUseCase,
    private val fazerLogoutUseCase: FazerLogoutUseCase,
    obterTokenUseCase: ObterTokenUseCase
) : ViewModel() {

    // Note como a chamada agora é feita ao UseCase
    val authToken = obterTokenUseCase().stateIn(
        scope = viewModelScope,
        started = SharingStarted.WhileSubscribed(5000),
        initialValue = null
    )

    fun login(matricula: String, senha: String, onResult: (Boolean) -> Unit) {
        viewModelScope.launch {
            // Chamando o UseCase como se fosse uma função graças ao `operator fun invoke`
            val result = fazerLoginUseCase(matricula, senha)
            onResult(result.isSuccess)
        }
    }

    fun fazerLogout() {
        viewModelScope.launch {
            fazerLogoutUseCase()
        }
    }
}