package com.senai.carteirinha_digital_senai.data.repository

import com.senai.carteirinha_digital_senai.data.local.DataStoreManager
import com.senai.carteirinha_digital_senai.data.remote.api.AuthApi
import com.senai.carteirinha_digital_senai.data.remote.model.LoginRequest
import kotlinx.coroutines.flow.Flow

// recebe a API e o DataStoreManager
class AuthRepository(
    private val api: AuthApi,
    private val dataStoreManager: DataStoreManager
) {
    // Repassa o fluxo do DataStoreManager para a ViewModel
    val authToken: Flow<String?> = dataStoreManager.authToken

    suspend fun fazerLoginNaApi(matricula: String, senha: String): Result<Boolean> {
        return try {
            val response = api.fazerLogin(LoginRequest(matricula, senha))
            if (response.isSuccessful && response.body() != null) {
                // Pede para o DataStoreManager salvar!
                dataStoreManager.salvarToken(response.body()!!.token)
                Result.success(true)
            } else {
                Result.failure(Exception("Credenciais inválidas"))
            }
        } catch (e: Exception) {
            Result.failure(Exception("Erro de conexão"))
        }
    }

    suspend fun fazerLogout() {
        dataStoreManager.limparToken()
    }
}