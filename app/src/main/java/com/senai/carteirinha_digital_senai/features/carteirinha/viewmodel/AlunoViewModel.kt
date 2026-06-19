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
    obterAlunoUseCase: ObterAlunoUseCase,
    private val salvarAlunoUseCase: SalvarAlunoUseCase,
    private val deletarAlunoUseCase: DeletarAlunoUseCase
) : ViewModel() {

    val alunoState = obterAlunoUseCase().stateIn(
        scope = viewModelScope,
        started = SharingStarted.WhileSubscribed(5000),
        initialValue = null
    )

    fun salvarAluno(aluno: Aluno) {
        viewModelScope.launch {
            salvarAlunoUseCase(aluno)
        }
    }

    fun deletarAluno() {
        viewModelScope.launch {
            deletarAlunoUseCase()
        }
    }
}