package com.senai.carteirinha_digital_senai.data.remote.model

import com.google.gson.annotations.SerializedName

// Representa a resposta do servidor em caso de sucesso
data class LoginResponse(
    val token: String, // O Token JWT que usaremos para as próximas requisições
    @SerializedName("aluno_nome") val alunoNome: String,
    @SerializedName("aluno_id") val alunoId: Int
)
