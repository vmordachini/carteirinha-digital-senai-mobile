package com.senai.carteirinha_digital_senai

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.compose.rememberNavController
import com.senai.carteirinha_digital_senai.core.navigation.AppNavHost
import com.senai.carteirinha_digital_senai.core.ui.theme.CarteirinhadigitalsenaiTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            CarteirinhadigitalsenaiTheme {
                val navController = rememberNavController()

                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    AppNavHost(
                        navController = navController,
                        authViewModel = hiltViewModel(),
                        alunoViewModel = hiltViewModel()
                        // homeViewModel é criado dentro da tela de UCs via viewModel()
                    )
                }
            }
        }
    }
}