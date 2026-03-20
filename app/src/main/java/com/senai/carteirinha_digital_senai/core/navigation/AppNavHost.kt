package com.senai.carteirinha_digital_senai.core.navigation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.senai.carteirinha_digital_senai.features.auth.presentation.screen.LoginScreen
import com.senai.carteirinha_digital_senai.features.auth.viewmodel.AuthViewModel
import com.senai.carteirinha_digital_senai.features.carteirinha.presentation.screen.CarteirinhaScreen
import com.senai.carteirinha_digital_senai.features.carteirinha.viewmodel.AlunoViewModel
import com.senai.carteirinha_digital_senai.features.configuracao.ui.DadosAlunoScreen

@Composable
fun AppNavHost(
    navController: NavHostController,
    authViewModel: AuthViewModel,
    alunoViewModel: AlunoViewModel
) {
    // Observamos os estados para saber para onde enviar o usuário
    val authToken by authViewModel.authToken.collectAsState()
    val aluno by alunoViewModel.alunoState.collectAsState()

    // 2. Lógica Inteligente de Rota:
    // Se não tem token -> Tela de Login
    // Se tem token, mas não tem aluno -> Configurar (provisório)
    // Se tem token e tem aluno -> Direto para a Carteirinha!
    val destinoInicial = when {
        authToken.isNullOrEmpty() -> Routes.Login.route
        aluno == null -> Routes.Configurar.route
        else -> Routes.Carteirinha.route
    }

    NavHost(
        navController = navController,
        startDestination = destinoInicial
    ) {

        // --- TELA DE LOGIN ---
        composable(Routes.Login.route) {
            LoginScreen(viewModel = authViewModel) {
                // Ao fazer login com sucesso na API, decide para onde ir
                val proximaRota = if (aluno == null) Routes.Configurar.route else Routes.Carteirinha.route
                navController.navigate(proximaRota) {
                    // Remove a tela de login do histórico para não voltar com o botão "Voltar" do celular
                    popUpTo(Routes.Login.route) { inclusive = true }
                }
            }
        }

        // --- TELA DA CARTEIRINHA ---
        composable(Routes.Carteirinha.route) {
            aluno?.let { dados ->
                CarteirinhaScreen(
                    aluno = dados,
                    onEditar = { navController.navigate(Routes.Configurar.route) },
                    onDeletar = {
                        // Agora o deletar faz logout da API e limpa os dados
                        authViewModel.fazerLogout()
                        alunoViewModel.deletarAluno()
                        navController.navigate(Routes.Login.route) {
                            popUpTo(Routes.Carteirinha.route) { inclusive = true }
                        }
                    }
                )
            }
        }

        // --- TELA DE FORMULÁRIO (Configurar) ---
        composable(Routes.Configurar.route) {
            DadosAlunoScreen(
                viewModel = alunoViewModel,
                onDadosSalvos = {
                    navController.navigate(Routes.Carteirinha.route) {
                        popUpTo(Routes.Configurar.route) { inclusive = true }
                    }
                }
            )
        }
    }
}