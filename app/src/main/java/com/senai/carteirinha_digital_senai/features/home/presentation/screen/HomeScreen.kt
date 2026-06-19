package com.senai.carteirinha_digital_senai.features.home.presentation.screen

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.List
import androidx.compose.material.icons.filled.AccountBox
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

private val AzulSenai = Color(0xFF032055)
private val AzulPrimario = Color(0xFF2E33FF)

/**
 * Tela Home (intermediária) do fluxo:
 * Login -> Home -> Carteirinha   |   Login -> Home -> Unidades Curriculares
 *
 * Funciona como um menu principal com dois destinos de navegação.
 */
@Composable
fun HomeScreen(
    onNavigateToCarteirinha: () -> Unit,
    onNavigateToUnidades: () -> Unit
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(24.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Text(
            text = "Bem-vindo!",
            style = MaterialTheme.typography.headlineLarge,
            fontWeight = FontWeight.Bold,
            color = AzulSenai
        )
        Spacer(modifier = Modifier.height(4.dp))
        Text(
            text = "O que você deseja acessar?",
            style = MaterialTheme.typography.bodyLarge,
            color = Color.Gray
        )

        Spacer(modifier = Modifier.height(40.dp))

        MenuCard(
            titulo = "Carteirinha Digital",
            descricao = "Visualize sua identificação estudantil",
            icone = Icons.Filled.AccountBox,
            onClick = onNavigateToCarteirinha
        )

        Spacer(modifier = Modifier.height(16.dp))

        MenuCard(
            titulo = "Unidades Curriculares",
            descricao = "Acompanhe suas disciplinas",
            icone = Icons.AutoMirrored.Filled.List,
            onClick = onNavigateToUnidades
        )
    }
}

@Composable
private fun MenuCard(
    titulo: String,
    descricao: String,
    icone: ImageVector,
    onClick: () -> Unit
) {
    Card(
        onClick = onClick,
        modifier = Modifier.fillMaxWidth(),
        shape = RoundedCornerShape(16.dp),
        colors = CardDefaults.cardColors(containerColor = Color.White),
        elevation = CardDefaults.cardElevation(4.dp)
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(20.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Icon(
                imageVector = icone,
                contentDescription = null,
                tint = AzulPrimario,
                modifier = Modifier.size(40.dp)
            )
            Spacer(modifier = Modifier.width(16.dp))
            Column {
                Text(
                    text = titulo,
                    fontSize = 18.sp,
                    fontWeight = FontWeight.Bold,
                    color = AzulSenai
                )
                Text(
                    text = descricao,
                    fontSize = 13.sp,
                    color = Color.Gray
                )
            }
        }
    }
}