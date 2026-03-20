package com.senai.carteirinha_digital_senai.features.carteirinha.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.senai.carteirinha_digital_senai.data.remote.model.Aluno
import com.senai.carteirinha_digital_senai.data.repository.AlunoRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class AlunoViewModel(private val repository: AlunoRepository) : ViewModel() {

    private val _alunoState = MutableStateFlow<Aluno?>(null)
    val alunoState: StateFlow<Aluno?> = _alunoState

    // Novos estados para gerir a chamada à internet
    private val _isLoading = MutableStateFlow(false)
    val isLoading: StateFlow<Boolean> = _isLoading

    private val _errorMessage = MutableStateFlow<String?>(null)
    val errorMessage: StateFlow<String?> = _errorMessage

    // Chama a API de verdade!
    fun carregarDadosAluno() {
        viewModelScope.launch {
            _isLoading.value = true
            _errorMessage.value = null

            val result = repository.obterDadosDoAluno()

            if (result.isSuccess) {
                // Se a API devolver os dados do aluno com sucesso, atualiza o ecrã
                _alunoState.value = result.getOrNull()
            } else {
                _errorMessage.value = result.exceptionOrNull()?.message ?: "Erro ao carregar carteirinha"
            }
            _isLoading.value = false
        }
    }

    // Como os dados agora vêm do servidor, o "deletar" local apenas limpa a interface
    // (O AuthViewModel é quem faz o verdadeiro logout apagando o Token)
    fun deletarAluno() {
        _alunoState.value = null
    }

    // Função mantida temporariamente apenas para não quebrar a "DadosAlunoScreen" (Configurar)
    fun salvarAluno(aluno: Aluno) {
        _alunoState.value = aluno
    }
}