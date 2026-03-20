package com.senai.carteirinha_digital_senai.data.remote.model

// Representa o corpo do JSON enviado no POST de login
data class LoginRequest(
    val matricula: String,
    val senha: String
)
