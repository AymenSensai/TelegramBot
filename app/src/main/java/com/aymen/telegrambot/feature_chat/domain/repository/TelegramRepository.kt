package com.aymen.telegrambot.feature_chat.domain.repository

import com.aymen.telegrambot.core.Resource
import com.aymen.telegrambot.feature_chat.domain.model.Update

interface TelegramRepository {
    suspend fun getUpdates(offset: Long): Resource<List<Update>>
    suspend fun getUpdates(): Resource<List<Update>>
    suspend fun sendMessage(chatId: String, text: String): Resource<Any>
}
