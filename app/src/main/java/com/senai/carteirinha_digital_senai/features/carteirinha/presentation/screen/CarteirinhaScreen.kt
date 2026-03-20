package com.senai.carteirinha_digital_senai.features.carteirinha.presentation.screen

import androidx.compose.runtime.Composable
import com.senai.carteirinha_digital_senai.data.remote.model.Aluno

@Composable
fun CarteirinhaScreen(
    aluno: Aluno,
    onEditar: () -> Unit,
    onDeletar: () -> Unit
) {
    // Aqui no futuro poderá observar a ViewModel (ex: mostrar loading, gerir erros de API)
    // Por agora, apenas repassa a informação para a interface Stateless.

    CarteirinhaContent(
        aluno = aluno,
        onEditarClick = onEditar,
        onDeletarClick = onDeletar
    )
}