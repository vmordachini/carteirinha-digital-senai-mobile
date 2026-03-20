package com.senai.carteirinha_digital_senai.data.local

import android.content.Context
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

private val Context.dataStore by preferencesDataStore(name = "auth_prefs")

class DataStoreManager (private val context: Context) {
    private val TOKEN_KEY = stringPreferencesKey("jwt_token")

    // Lê o token
    val authToken: Flow<String?> = context.dataStore.data.map { it[TOKEN_KEY] }

    // Salva o token
    suspend fun salvarToken(token: String) {
        context.dataStore.edit { preferences ->
            preferences[TOKEN_KEY] = token
        }
    }

    // Apaga o token (Logout)
    suspend fun limparToken() {
        context.dataStore.edit { preferences ->
            preferences.remove(TOKEN_KEY)
        }
    }
}