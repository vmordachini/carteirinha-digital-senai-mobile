package com.senai.carteirinha_digital_senai.data.repository

import com.senai.carteirinha_digital_senai.data.local.DataStoreManager
import com.senai.carteirinha_digital_senai.data.remote.api.AuthApi
import com.senai.carteirinha_digital_senai.data.remote.model.LoginRequest
import com.senai.carteirinha_digital_senai.data.remote.model.LoginResponse
import com.senai.carteirinha_digital_senai.domain.repository.AuthRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

// Adicionamos a anotação @Inject constructor para o Hilt saber como instanciar esta classe
class AuthRepositoryImpl @Inject constructor(
    private val authApi: AuthApi,
    private val dataStoreManager: DataStoreManager
) : AuthRepository {

    override val authToken: Flow<String?> = dataStoreManager.authToken

    override suspend fun fazerLogin(matricula: String, senha: String): Result<LoginResponse> {
        return try {
            val response = authApi.login(LoginRequest(matricula, senha))
            if (response.isSuccessful && response.body() != null) {
                val body = response.body()!!
                dataStoreManager.salvarToken(body.token)
                Result.success(body)
            } else {
                Result.failure(Exception("Credenciais inválidas"))
            }
        } catch (e: Exception) {
            Result.failure(e)
        }
    }

    override suspend fun fazerLogout() {
        dataStoreManager.limparToken()
    }
}