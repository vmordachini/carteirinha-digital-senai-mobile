package com.senai.carteirinha_digital_senai.features.carteirinha.presentation.component

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.senai.carteirinha_digital_senai.R

@Composable
fun PerfilAluno(
    fotoUri: String?,
    modifier: Modifier = Modifier
) {
    AsyncImage(
        model = fotoUri ?: R.drawable.avatar_vazio,
        contentDescription = "Foto do Aluno",
        modifier = modifier
            .size(150.dp)
            .clip(CircleShape)
            .background(Color.Gray),
        contentScale = ContentScale.Crop,
        error = painterResource(R.drawable.avatar_vazio)
    )
}