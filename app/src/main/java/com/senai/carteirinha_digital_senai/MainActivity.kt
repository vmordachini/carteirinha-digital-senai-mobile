package com.senai.carteirinha_digital_senai

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.ui.Modifier
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.rememberNavController
import com.senai.carteirinha_digital_senai.core.navigation.AppNavHost
import com.senai.carteirinha_digital_senai.core.ui.theme.CarteirinhadigitalsenaiTheme
import com.senai.carteirinha_digital_senai.data.repository.AlunoRepository
import com.senai.carteirinha_digital_senai.data.repository.AuthRepository
import com.senai.carteirinha_digital_senai.features.auth.viewmodel.AuthViewModel
import com.senai.carteirinha_digital_senai.features.auth.viewmodel.AuthViewModelFactory
import com.senai.carteirinha_digital_senai.features.carteirinha.viewmodel.AlunoViewModel
import com.senai.carteirinha_digital_senai.features.carteirinha.viewmodel.AlunoViewModelFactory

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val dataStoreManager = com.senai.carteirinha_digital_senai.data.local.DataStoreManager(this)

        val alunoRepository = AlunoRepository(dataStoreManager)
        val alunoFactory = AlunoViewModelFactory(alunoRepository)

        val authApi = com.senai.carteirinha_digital_senai.data.remote.RetrofitClient.getApi(dataStoreManager)
        val authRepository = AuthRepository(authApi, dataStoreManager)
        val authFactory = AuthViewModelFactory(authRepository)

        setContent {
            CarteirinhadigitalsenaiTheme {
                val navController = rememberNavController()

                // 2. Inicialização das ViewModels
                val authViewModel: AuthViewModel = viewModel(factory = authFactory)
                val alunoViewModel: AlunoViewModel = viewModel(factory = alunoFactory)

                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    // 3. A Navegação centraliza tudo
                    AppNavHost(
                        navController = navController,
                        authViewModel = authViewModel,
                        alunoViewModel = alunoViewModel
                    )
                }
            }
        }
    }
}