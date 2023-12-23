package com.aymen.telegrambot.di

import com.aymen.telegrambot.core.Constants.BASE_URL
import com.aymen.telegrambot.core.Constants.BOT_TOKEN
import com.aymen.telegrambot.feature_chat.data.remote.TelegramApi
import com.aymen.telegrambot.feature_chat.data.repository.TelegramRepositoryImpl
import com.aymen.telegrambot.feature_chat.domain.repository.TelegramRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.create
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun provideTelegramApi(): TelegramApi {
        return Retrofit.Builder()
            .baseUrl("$BASE_URL$BOT_TOKEN/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create()
    }

    @Provides
    @Singleton
    fun provideTelegramRepository(
        api: TelegramApi
    ): TelegramRepository {
        return TelegramRepositoryImpl(api)
    }

}