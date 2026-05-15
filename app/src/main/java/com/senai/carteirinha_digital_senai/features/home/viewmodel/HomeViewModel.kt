package com.senai.carteirinha_digital_senai.features.home.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.senai.carteirinha_digital_senai.features.home.data.repository.FakeUnidadeCurricularRepository
import com.senai.carteirinha_digital_senai.features.home.presentation.HomeEvent
import com.senai.carteirinha_digital_senai.features.home.presentation.HomeUiState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class HomeViewModel : ViewModel() {
    private val repository = FakeUnidadeCurricularRepository()

    private val _state = MutableStateFlow(HomeUiState())
    val state: StateFlow<HomeUiState> = _state.asStateFlow()

    init {
        onEvent(HomeEvent.CarregarDados)
    }

    fun onEvent(event: HomeEvent) {
        when (event) {
            is HomeEvent.CarregarDados -> carregarUnidades()
            is HomeEvent.AtualizarTela -> carregarUnidades() // Pode implementar refresh depois
        }
    }

    private fun carregarUnidades() {
        viewModelScope.launch {
            _state.value = _state.value.copy(isLoading = true)
            try {
                val dados = repository.buscarUnidadesCurriculares()
                _state.value = _state.value.copy(isLoading = false, unidades = dados)
            } catch (e: Exception) {
                _state.value = _state.value.copy(isLoading = false, error = "Erro ao carregar")
            }
        }
    }
}