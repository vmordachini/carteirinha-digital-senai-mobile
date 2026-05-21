package com.senai.carteirinha_digital_senai.domain.usecase.aluno

import com.senai.carteirinha_digital_senai.domain.repository.AlunoRepository
import javax.inject.Inject

class DeletarAlunoUseCase @Inject constructor(
    private val alunoRepository: AlunoRepository
) {
    suspend operator fun invoke() {
        alunoRepository.deletarAluno()
    }
}