package com.senai.carteirinha_digital_senai.features.home.presentation

import com.senai.carteirinha_digital_senai.features.home.domain.model.UnidadeCurricular

data class HomeUiState(
    val isLoading: Boolean = false,
    val unidades: List<UnidadeCurricular> = emptyList(),
    val error: String? = null
)
