package com.senai.carteirinha_digital_senai.domain.repository

import com.senai.carteirinha_digital_senai.domain.model.Aluno
import kotlinx.coroutines.flow.Flow

interface AlunoRepository {
    val alunoState: Flow<Aluno?>
    suspend fun salvarAluno(aluno: Aluno)
    suspend fun deletarAluno()
}