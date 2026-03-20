package com.senai.carteirinha_digital_senai.features.auth.presentation.screen

import androidx.compose.runtime.*
import com.senai.carteirinha_digital_senai.features.auth.viewmodel.AuthViewModel

@Composable
fun LoginScreen(
    viewModel: AuthViewModel,
    onAuthSuccess: () -> Unit
) {
    // Estados locais
    var loginInput by remember { mutableStateOf("") }
    var senhaInput by remember { mutableStateOf("") }

    // Estados que vêm da ViewModel (API)
    val errorMessage by viewModel.errorMessage.collectAsState()
    val isLoading by viewModel.isLoading.collectAsState() // Futuramente pode passar para o LoginContent para rodar um Spinner

    LoginContent(
        loginInput = loginInput,
        senhaInput = senhaInput,
        erro = errorMessage != null, // Fica true se a ViewModel tiver alguma mensagem de erro
        onLoginChange = { novoLogin ->
            loginInput = novoLogin
            viewModel.limparErro() // Limpa o erro ao voltar a digitar
        },
        onSenhaChange = { novaSenha ->
            senhaInput = novaSenha
            viewModel.limparErro()
        },
        onActionClick = {
            if (loginInput.isNotBlank() && senhaInput.isNotBlank()) {
                // Chama a API de verdade!
                viewModel.fazerLogin(loginInput, senhaInput) {
                    onAuthSuccess()
                }
            } else {
                // Tratamento local para campos vazios (opcional)
            }
        }
    )
}