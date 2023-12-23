package com.aymen.telegrambot.feature_chat.presentation.chat

import com.aymen.telegrambot.feature_chat.domain.model.Update

data class ChatState(
    val updates: List<Update> = emptyList(),
    val lastUpdate: Update? = null,
    val text: String = "",
    val chatId: String = "",
    val isLoading: Boolean = false
)