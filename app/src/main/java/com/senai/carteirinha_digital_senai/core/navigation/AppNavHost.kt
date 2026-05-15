package com.senai.carteirinha_digital_senai.core.navigation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.senai.carteirinha_digital_senai.features.auth.presentation.screen.LoginScreen
import com.senai.carteirinha_digital_senai.features.auth.viewmodel.AuthViewModel
import com.senai.carteirinha_digital_senai.features.carteirinha.presentation.screen.CarteirinhaScreen
import com.senai.carteirinha_digital_senai.features.carteirinha.viewmodel.AlunoViewModel
import com.senai.carteirinha_digital_senai.features.configuracao.ui.DadosAlunoScreen
import com.senai.carteirinha_digital_senai.features.home.presentation.screen.HomeScreen
import com.senai.carteirinha_digital_senai.features.home.viewmodel.HomeViewModel

@Composable
fun AppNavHost(
    navController: NavHostController,
    authViewModel: AuthViewModel,
    alunoViewModel: AlunoViewModel,
    homeViewModel: HomeViewModel = viewModel() // Instanciado aqui ou passado pela MainActivity
) {
    val authToken by authViewModel.authToken.collectAsState()
    val aluno by alunoViewModel.alunoState.collectAsState()

    // Lógica de Destino Inicial:
    // 1. Sem token -> Login
    // 2. Com token mas sem perfil preenchido -> Configurar
    // 3. Tudo ok -> Home (Lista de Disciplinas)
    val destinoInicial = when {
        authToken.isNullOrEmpty() -> Routes.Login.route
        aluno == null -> Routes.Configurar.route
        else -> Routes.Home.route
    }

    NavHost(
        navController = navController,
        startDestination = destinoInicial
    ) {

        // --- TELA DE LOGIN ---
        composable(Routes.Login.route) {
            LoginScreen(viewModel = authViewModel) {
                // Ao logar, vai para Home ou Configurar
                val proximaRota = if (aluno == null) Routes.Configurar.route else Routes.Home.route
                navController.navigate(proximaRota) {
                    popUpTo(Routes.Login.route) { inclusive = true }
                }
            }
        }

        // --- TELA DE FORMULÁRIO (Configurar) ---
        composable(Routes.Configurar.route) {
            DadosAlunoScreen(
                viewModel = alunoViewModel,
                onDadosSalvos = {
                    navController.navigate(Routes.Home.route) {
                        popUpTo(Routes.Configurar.route) { inclusive = true }
                    }
                }
            )
        }

        // --- TELA DE HOME (Lista de UCs) ---
        composable(Routes.Home.route) {
            HomeScreen(
                viewModel = homeViewModel,
                onNavigateToCarteirinha = {
                    navController.navigate(Routes.Carteirinha.route)
                }
            )
        }

        // --- TELA DA CARTEIRINHA ---
        composable(Routes.Carteirinha.route) {
            aluno?.let { dados ->
                CarteirinhaScreen(
                    aluno = dados,
                    onEditar = { navController.navigate(Routes.Configurar.route) },
                    onDeletar = {
                        authViewModel.fazerLogout()
                        alunoViewModel.deletarAluno()
                        navController.navigate(Routes.Login.route) {
                            popUpTo(Routes.Home.route) { inclusive = true }
                        }
                    }
                )
            }
        }
    }
}