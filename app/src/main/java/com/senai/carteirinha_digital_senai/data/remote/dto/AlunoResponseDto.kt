package com.senai.carteirinha_digital_senai.data.remote.dto

data class AlunoResponseDto(
    val id: Int,
    val nome: String,
    val curso: String,
    val matricula: String,
    val fotoUri: String?,
    val codigoQr: String

)
