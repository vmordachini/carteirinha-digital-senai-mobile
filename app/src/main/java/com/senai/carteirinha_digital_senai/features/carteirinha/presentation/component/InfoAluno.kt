package com.senai.carteirinha_digital_senai.features.carteirinha.presentation.component

import androidx.compose.foundation.layout.Column
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp

@Composable
fun InfoAluno(
    nome: String,
    curso: String,
    matricula: String,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(text = nome.uppercase(), fontSize = 20.sp, fontWeight = FontWeight.Bold, color = Color.Black)
        Text(text = curso, fontSize = 16.sp, color = Color.DarkGray)
        Text(text = "Matrícula: $matricula", fontSize = 14.sp, color = Color.Gray)
    }
}