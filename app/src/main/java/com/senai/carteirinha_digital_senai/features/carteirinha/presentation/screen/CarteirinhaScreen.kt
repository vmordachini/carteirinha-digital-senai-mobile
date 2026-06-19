package com.senai.carteirinha_digital_senai.features.carteirinha.presentation.screen

import androidx.compose.runtime.Composable
import com.senai.carteirinha_digital_senai.domain.model.Aluno

@Composable
fun CarteirinhaScreen(
    aluno: Aluno,
    onEditar: () -> Unit,
    onDeletar: () -> Unit
) {
    CarteirinhaContent(
        aluno = aluno,
        onEditarClick = onEditar,
        onDeletarClick = onDeletar
    )
}