package com.senai.carteirinha_digital_senai.data.repository

import com.senai.carteirinha_digital_senai.data.local.DataStoreManager
import com.senai.carteirinha_digital_senai.domain.model.Aluno
import com.senai.carteirinha_digital_senai.domain.repository.AlunoRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import javax.inject.Inject

class AlunoRepositoryImpl @Inject constructor(
    private val dataStoreManager: DataStoreManager
) : AlunoRepository {

    // Aluno MOCKADO fixo: como ainda não há API, já iniciamos com dados de demonstração
    // para que a Carteirinha sempre tenha conteúdo para exibir.
    private val _alunoState = MutableStateFlow<Aluno?>(alunoMock())
    override val alunoState: Flow<Aluno?> = _alunoState.asStateFlow()

    override suspend fun salvarAluno(aluno: Aluno) {
        _alunoState.value = aluno
    }

    override suspend fun deletarAluno() {
        _alunoState.value = null
    }

    private fun alunoMock(): Aluno = Aluno(
        id = 1,
        nome = "Victor Themoteo Mordachini",
        curso = "Desenvolvimento de Sistemas",
        matricula = "2024010123",
        fotoUri = null,
        codigoQr = "SENAI-2024010123-VICTOR"
    )
}