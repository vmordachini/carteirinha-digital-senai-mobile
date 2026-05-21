package com.senai.carteirinha_digital_senai.di

import com.senai.carteirinha_digital_senai.data.repository.AlunoRepositoryImpl
import com.senai.carteirinha_digital_senai.data.repository.AuthRepositoryImpl
import com.senai.carteirinha_digital_senai.domain.repository.AlunoRepository
import com.senai.carteirinha_digital_senai.domain.repository.AuthRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class RepositoryModule {

    @Binds
    @Singleton
    abstract fun bindAuthRepository(
        authRepositoryImpl: AuthRepositoryImpl
    ): AuthRepository

    @Binds
    @Singleton
    abstract fun bindAlunoRepository(
        alunoRepositoryImpl: AlunoRepositoryImpl
    ): AlunoRepository
}