package com.senai.carteirinha_digital_senai.data.mapper

import com.senai.carteirinha_digital_senai.data.local.entity.AlunoEntity
import com.senai.carteirinha_digital_senai.data.remote.dto.AlunoResponseDto
import com.senai.carteirinha_digital_senai.domain.model.Aluno

// Converte a resposta da API (DTO) para o modelo de regra de negócio (Domain)
fun AlunoResponseDto.toDomain(): Aluno {
    return Aluno(
        id = this.id,
        nome = this.nome,
        curso = this.curso,
        matricula = this.matricula,
        fotoUri = this.fotoUri,
        codigoQr = this.codigoQr
    )
}

// Converte o modelo de regra de negócio para a Entidade do Banco Local (Room)
fun Aluno.toEntity(): AlunoEntity {
    return AlunoEntity(
        id = this.id,
        nome = this.nome,
        curso = this.curso,
        matricula = this.matricula,
        fotoUri = this.fotoUri,
        codigoQr = this.codigoQr
    )
}

// Converte a Entidade do Banco Local (Room) de volta para Regra de Negócio
fun AlunoEntity.toDomain(): Aluno {
    return Aluno(
        id = this.id,
        nome = this.nome,
        curso = this.curso,
        matricula = this.matricula,
        fotoUri = this.fotoUri,
        codigoQr = this.codigoQr
    )
}