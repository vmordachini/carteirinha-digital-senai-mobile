package com.senai.carteirinha_digital_senai.data.repository

import com.senai.carteirinha_digital_senai.data.local.DataStoreManager
import com.senai.carteirinha_digital_senai.data.remote.RetrofitClient
import com.senai.carteirinha_digital_senai.data.remote.model.Aluno

class AlunoRepository(private val dataStoreManager: DataStoreManager) {

    // Busca o aluno diretamente do servidor
    suspend fun obterDadosDoAluno(): Result<Aluno> {
        return try {
            // Retrofit + OkHttp (com Token injetado)
            val response = RetrofitClient.getCarteirinhaApi(dataStoreManager).getDadosAluno()

            if (response.isSuccessful && response.body() != null) {
                Result.success(response.body()!!)
            } else {
                Result.failure(Exception("Erro ao buscar dados do servidor. Código: ${response.code()}"))
            }
        } catch (e: Exception) {
            Result.failure(Exception("Erro de conexão: Verifique sua internet."))
        }
    }
}