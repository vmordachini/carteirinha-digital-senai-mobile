package com.senai.carteirinha_digital_senai.domain.usecase.aluno

import com.senai.carteirinha_digital_senai.domain.model.Aluno
import com.senai.carteirinha_digital_senai.domain.repository.AlunoRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class ObterAlunoUseCase @Inject constructor(
    private val alunoRepository: AlunoRepository
) {
    // Retorna o Flow do alunoState para ser observado pela ViewModel
    operator fun invoke(): Flow<Aluno?> {
        return alunoRepository.alunoState
    }
}