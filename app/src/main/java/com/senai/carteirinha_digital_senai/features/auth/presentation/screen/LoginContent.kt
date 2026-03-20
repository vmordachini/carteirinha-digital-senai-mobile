package com.senai.carteirinha_digital_senai.features.auth.presentation.screen

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.unit.dp

@Composable
fun LoginContent(
    loginInput: String,
    senhaInput: String,
    erro: Boolean,
    onLoginChange: (String) -> Unit,
    onSenhaChange: (String) -> Unit,
    onActionClick: () -> Unit
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Text(
            text = "Acesso do Aluno",
            style = MaterialTheme.typography.headlineMedium
        )

        Spacer(modifier = Modifier.height(24.dp))

        // Campo de Login (Matrícula ou Email)
        OutlinedTextField(
            value = loginInput,
            onValueChange = onLoginChange,
            label = { Text("Matrícula ou Email") },
            isError = erro,
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(8.dp))

        // Campo de Senha
        OutlinedTextField(
            value = senhaInput,
            onValueChange = onSenhaChange,
            label = { Text("Senha") },
            visualTransformation = PasswordVisualTransformation(),
            isError = erro,
            modifier = Modifier.fillMaxWidth()
        )

        if (erro) {
            Text(
                text = "Credenciais incorretas!",
                color = MaterialTheme.colorScheme.error,
                modifier = Modifier.padding(top = 8.dp)
            )
        }

        Spacer(modifier = Modifier.height(24.dp))

        Button(
            onClick = onActionClick,
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("Entrar")
        }
    }
}