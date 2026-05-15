package com.senai.carteirinha_digital_senai.features.home.presentation

sealed class HomeEvent {
    object CarregarDados : HomeEvent()
    object AtualizarTela : HomeEvent()
}