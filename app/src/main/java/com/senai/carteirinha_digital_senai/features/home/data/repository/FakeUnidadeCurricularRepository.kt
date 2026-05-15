package com.senai.carteirinha_digital_senai.features.home.data.repository

import com.senai.carteirinha_digital_senai.features.home.domain.model.UnidadeCurricular
import kotlinx.coroutines.delay

class FakeUnidadeCurricularRepository {
    // Simula uma chamada de rede com delay
    suspend fun buscarUnidadesCurriculares(): List<UnidadeCurricular> {
        delay(1000) // 1 segundo de carregamento falso
        return listOf(
            UnidadeCurricular(1, "Desenvolvimento de Sistemas Mobile", "120h", "Cursando"),
            UnidadeCurricular(2, "Testes de Software", "80h", "Aprovado"),
            UnidadeCurricular(3, "Internet das Coisas (IoT)", "100h", "Cursando")
        )
    }
}