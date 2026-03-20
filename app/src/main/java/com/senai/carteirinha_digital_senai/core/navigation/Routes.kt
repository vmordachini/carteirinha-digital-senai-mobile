package com.senai.carteirinha_digital_senai.core.navigation

// Uma classe selada garante que só existam estas rotas no app inteiro
sealed class Routes (val route: String) {

    object CadastrarPin : Routes("cadastrar_pin")
    object Login : Routes("login")
    object Carteirinha : Routes("carteirinha")

    // Manteremos a rota de configurar por enquanto para não quebrar o app atual,
    // mas ela será removida no Passo 5 quando a API for conectada.
    object Configurar : Routes("configurar")
}