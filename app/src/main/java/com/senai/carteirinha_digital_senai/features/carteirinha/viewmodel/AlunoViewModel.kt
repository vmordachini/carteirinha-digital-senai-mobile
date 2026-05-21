package com.senai.carteirinha_digital_senai.features.carteirinha.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.senai.carteirinha_digital_senai.domain.model.Aluno
import com.senai.carteirinha_digital_senai.domain.repository.AlunoRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AlunoViewModel @Inject constructor(
    private val alunoRepository: AlunoRepository
) : ViewModel() {

    val alunoState = alunoRepository.alunoState.stateIn(
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
            alunoRepository.salvarAluno(novoAluno)
        }
    }

    fun deletarAluno() {
        viewModelScope.launch {
            alunoRepository.deletarAluno()
        }
    }
}