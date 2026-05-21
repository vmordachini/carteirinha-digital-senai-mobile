package com.senai.carteirinha_digital_senai.data.local.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "alunos")
data class AlunoEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    val nome: String,
    val curso: String,
    val matricula: String,
    val fotoUri: String?,
    val codigoQr: String
)
