package com.senai.carteirinha_digital_senai.data.remote.api

import com.senai.carteirinha_digital_senai.data.remote.model.Aluno
import retrofit2.Response
import retrofit2.http.GET

interface CarteirinhaApi {
    // Rota fictícia que devolve os dados do aluno logado
    // O Token JWT que configuramos no Interceptor será enviado automaticamente aqui!
    @GET("aluno/perfil")
    suspend fun getDadosAluno(): Response<Aluno>
}