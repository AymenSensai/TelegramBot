package com.aymen.telegrambot

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import com.aymen.telegrambot.feature_chat.presentation.chat.ChatScreen
import com.aymen.telegrambot.feature_chat.presentation.chat.ChatViewModel
import com.aymen.telegrambot.ui.theme.TelegramBotTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            TelegramBotTheme {
                Surface(modifier = Modifier.fillMaxSize(), color = MaterialTheme.colorScheme.background) {
                    val viewModel: ChatViewModel = hiltViewModel()
                    val state = viewModel.state
                    ChatScreen(state = state, onEvent = { viewModel.onEvent(it) })
                }
            }
        }
    }
}