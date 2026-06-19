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
import com.senai.carteirinha_digital_senai.features.home.presentation.screen.UnidadesCurricularesScreen
import com.senai.carteirinha_digital_senai.features.home.viewmodel.HomeViewModel

@Composable
fun AppNavHost(
    navController: NavHostController,
    authViewModel: AuthViewModel,
    alunoViewModel: AlunoViewModel,
    homeViewModel: HomeViewModel = viewModel()
) {
    val authToken by authViewModel.authToken.collectAsState()

    // Sem token -> Login. Com token -> Home (menu intermediário).
    val destinoInicial = if (authToken.isNullOrEmpty()) Routes.Login.route else Routes.Home.route

    NavHost(
        navController = navController,
        startDestination = destinoInicial
    ) {

        // --- LOGIN ---
        composable(Routes.Login.route) {
            LoginScreen(viewModel = authViewModel) {
                // Login -> Home
                navController.navigate(Routes.Home.route) {
                    popUpTo(Routes.Login.route) { inclusive = true }
                }
            }
        }

        // --- HOME (menu intermediário) ---
        composable(Routes.Home.route) {
            HomeScreen(
                onNavigateToCarteirinha = { navController.navigate(Routes.Carteirinha.route) },
                onNavigateToUnidades = { navController.navigate(Routes.Unidades.route) }
            )
        }

        // --- CARTEIRINHA ---
        composable(Routes.Carteirinha.route) {
            val aluno by alunoViewModel.alunoState.collectAsState()
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

        // --- UNIDADES CURRICULARES ---
        composable(Routes.Unidades.route) {
            UnidadesCurricularesScreen(
                onVoltar = { navController.popBackStack() }
            )
        }

        // --- CONFIGURAR (editar dados) ---
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