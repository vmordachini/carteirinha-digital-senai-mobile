package com.senai.carteirinha_digital_senai.core.navigation

// Uma classe selada garante que só existam estas rotas no app inteiro
sealed class Routes(val route: String) {

    object Login : Routes("login")
    object Home : Routes("home")
    object Carteirinha : Routes("carteirinha")
    object Unidades : Routes("unidades")
    object Configurar : Routes("configurar")
}