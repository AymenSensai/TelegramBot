package com.aymen.telegrambot.feature_chat.presentation.chat

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.aymen.telegrambot.core.Resource
import com.aymen.telegrambot.feature_chat.domain.repository.TelegramRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.isActive
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ChatViewModel @Inject constructor(private val chatRepository: TelegramRepository) :
    ViewModel() {

    var state by mutableStateOf(ChatState())
        private set

    fun onEvent(event: ChatEvent) {
        when (event) {
            is ChatEvent.StartPoling -> startPoling()
            is ChatEvent.OnTextChange -> onTextChange(event.text)
            is ChatEvent.OnChatIdChange -> onChatIdChange(event.chatId)
            is ChatEvent.SendMessage -> sendMessage(event.text, event.chatId)
        }
    }

    private fun startPoling() {
        viewModelScope.launch {
            while (isActive) {
                performPoling()
            }
        }
    }

    private suspend fun performPoling() {
        val result = if (state.lastUpdate != null) {
            chatRepository.getUpdates(offset = state.lastUpdate!!.updateId + 1)
        } else {
            chatRepository.getUpdates()
        }
        when (result) {
            is Resource.Success -> {
                val updates = result.data!!
                state =
                    state.copy(lastUpdate = updates.last(), updates = state.updates + updates)
            }

            else -> {}
        }
    }

    private fun onTextChange(text: String) {
        state = state.copy(text = text)
    }

    private fun onChatIdChange(chatId: String) {
        state = state.copy(chatId = chatId)
    }

    private fun sendMessage(text: String, chatId: String) {
        viewModelScope.launch {
            chatRepository.sendMessage(chatId, text)

        }
    }

}