package com.senai.carteirinha_digital_senai.data.local

import android.content.Context
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.launch

private val Context.dataStore by preferencesDataStore(name = "auth_prefs")

class DataStoreManager(private val context: Context) {
    private val TOKEN_KEY = stringPreferencesKey("jwt_token")

    // Mantém o token em memória usando StateFlow
    private val _tokenCache = MutableStateFlow<String?>(null)
    val authToken: Flow<String?> = _tokenCache.asStateFlow()

    init {
        // Inicia a recolha do DataStore para atualizar a cache em memória de forma reativa
        CoroutineScope(Dispatchers.IO).launch {
            context.dataStore.data.map { it[TOKEN_KEY] }.collect { token ->
                _tokenCache.value = token
            }
        }
    }

    // Função síncrona para ser usada pelo Interceptor (evita runBlocking)
    fun getTokenSync(): String? = _tokenCache.value

    suspend fun salvarToken(token: String) {
        context.dataStore.edit { preferences ->
            preferences[TOKEN_KEY] = token
        }
    }

    suspend fun limparToken() {
        context.dataStore.edit { preferences ->
            preferences.remove(TOKEN_KEY)
        }
    }
}