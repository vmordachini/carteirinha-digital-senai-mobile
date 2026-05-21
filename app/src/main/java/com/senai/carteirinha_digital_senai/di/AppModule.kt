package com.senai.carteirinha_digital_senai.di

import android.content.Context
import com.senai.carteirinha_digital_senai.data.local.DataStoreManager
import com.senai.carteirinha_digital_senai.data.remote.RetrofitClient
import com.senai.carteirinha_digital_senai.data.remote.api.AuthApi
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun provideDataStoreManager(@ApplicationContext context: Context): DataStoreManager {
        return DataStoreManager(context)
    }

    @Provides
    @Singleton
    fun provideAuthApi(dataStoreManager: DataStoreManager): AuthApi {
        return RetrofitClient.getApi(dataStoreManager)
    }
}