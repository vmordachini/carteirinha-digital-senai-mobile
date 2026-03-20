package com.senai.carteirinha_digital_senai.features.carteirinha.presentation.screen

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.asImageBitmap
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.senai.carteirinha_digital_senai.R
import com.senai.carteirinha_digital_senai.core.util.gerarQrCode
import com.senai.carteirinha_digital_senai.data.remote.model.Aluno
import com.senai.carteirinha_digital_senai.features.carteirinha.presentation.component.InfoAluno
import com.senai.carteirinha_digital_senai.features.carteirinha.presentation.component.PerfilAluno

@Composable
fun CarteirinhaContent(
    aluno: Aluno,
    onEditarClick: () -> Unit,
    onDeletarClick: () -> Unit
) {
    Column(
        modifier = Modifier.fillMaxSize().padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        // Cabeçalho com Logo e Botões
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Image(
                painter = painterResource(id = R.drawable.senai_s_o_paulo_logo),
                contentDescription = "Logo SENAI",
                modifier = Modifier.height(40.dp)
            )
            Row {
                IconButton(onClick = onEditarClick) {
                    Icon(Icons.Default.Edit, contentDescription = "Editar Dados")
                }
                IconButton(onClick = onDeletarClick) {
                    Icon(
                        painter = painterResource(id = android.R.drawable.ic_menu_delete),
                        contentDescription = "Excluir",
                        tint = Color.Red
                    )
                }
            }
        }

        Spacer(modifier = Modifier.height(24.dp))

        // O Cartão Principal da Carteirinha
        Card(
            modifier = Modifier.fillMaxWidth().height(450.dp),
            shape = RoundedCornerShape(16.dp),
            colors = CardDefaults.cardColors(containerColor = Color(0xFFF5F5F5)),
            elevation = CardDefaults.cardElevation(8.dp)
        ) {
            Column(
                modifier = Modifier.fillMaxSize().padding(16.dp),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.SpaceBetween
            ) {
                // Utilizando os componentes criados
                PerfilAluno(fotoUri = aluno.fotoUri)

                InfoAluno(nome = aluno.nome, curso = aluno.curso, matricula = aluno.matricula)

                val qrCodeBitmap = gerarQrCode(aluno.codigoQr)
                if (qrCodeBitmap != null) {
                    Image(
                        bitmap = qrCodeBitmap.asImageBitmap(),
                        contentDescription = "QR Code",
                        modifier = Modifier.size(120.dp)
                    )
                }

                Text(
                    text = "CARTEIRINHA DIGITAL",
                    fontWeight = FontWeight.Light,
                    letterSpacing = 2.sp,
                    color = Color.Red
                )
            }
        }
    }
}