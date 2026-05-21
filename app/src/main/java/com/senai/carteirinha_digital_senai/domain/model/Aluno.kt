package com.senai.carteirinha_digital_senai.domain.model

data class Aluno(
    val id: Int = 0,
    val nome: String,
    val curso: String,
    val matricula: String,
    val fotoUri: String?,
    val codigoQr: String
)