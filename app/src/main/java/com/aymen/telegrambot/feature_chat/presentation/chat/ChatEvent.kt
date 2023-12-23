package com.aymen.telegrambot.feature_chat.presentation.chat

sealed class ChatEvent {
    data object StartPoling : ChatEvent()
    data class SendMessage(val text: String, val chatId: String) : ChatEvent()
    data class OnTextChange(val text: String) : ChatEvent()
    data class OnChatIdChange(val chatId: String) : ChatEvent()
}