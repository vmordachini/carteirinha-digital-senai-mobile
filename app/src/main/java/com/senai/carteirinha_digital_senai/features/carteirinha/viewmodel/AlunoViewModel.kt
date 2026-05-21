package com.senai.carteirinha_digital_senai.features.carteirinha.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.senai.carteirinha_digital_senai.domain.model.Aluno
import com.senai.carteirinha_digital_senai.domain.usecase.aluno.DeletarAlunoUseCase
import com.senai.carteirinha_digital_senai.domain.usecase.aluno.ObterAlunoUseCase
import com.senai.carteirinha_digital_senai.domain.usecase.aluno.SalvarAlunoUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AlunoViewModel @Inject constructor(
    // Injeção exclusiva dos UseCases
    obterAlunoUseCase: ObterAlunoUseCase,
    private val salvarAlunoUseCase: SalvarAlunoUseCase,
    private val deletarAlunoUseCase: DeletarAlunoUseCase
) : ViewModel() {

    // Observa o Flow retornado pelo UseCase
    val alunoState = obterAlunoUseCase().stateIn(
        scope = viewModelScope,
        started = SharingStarted.WhileSubscribed(5000),
        initialValue = null
    )

    fun salvarAluno(nome: String, curso: String, matricula: String, fotoUri: String?, codigoQr: String) {
        viewModelScope.launch {
            val novoAluno = Aluno(
                nome = nome,
                curso = curso,
                matricula = matricula,
                fotoUri = fotoUri,
                codigoQr = codigoQr
            )
            // Chama o UseCase como se fosse uma função
            salvarAlunoUseCase(novoAluno)
        }
    }

    fun deletarAluno() {
        viewModelScope.launch {
            // Chama o UseCase como se fosse uma função
            deletarAlunoUseCase()
        }
    }
}