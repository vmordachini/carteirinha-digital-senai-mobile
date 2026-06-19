package com.senai.carteirinha_digital_senai.features.auth.presentation.screen

import androidx.compose.runtime.*
import com.senai.carteirinha_digital_senai.features.auth.viewmodel.AuthViewModel

@Composable
fun LoginScreen(
    viewModel: AuthViewModel,
    onAuthSuccess: () -> Unit
) {
    var loginInput by remember { mutableStateOf("") }
    var senhaInput by remember { mutableStateOf("") }

    val errorMessage by viewModel.errorMessage.collectAsState()

    LoginContent(
        loginInput = loginInput,
        senhaInput = senhaInput,
        erro = errorMessage != null,
        onLoginChange = { novoLogin ->
            loginInput = novoLogin
            viewModel.limparErro()
        },
        onSenhaChange = { novaSenha ->
            senhaInput = novaSenha
            viewModel.limparErro()
        },
        onActionClick = {
            // Login SIMULADO: entra direto, sem validar credenciais nem exigir API.
            viewModel.fazerLogin(loginInput, senhaInput) {
                onAuthSuccess()
            }
        }
    )
}