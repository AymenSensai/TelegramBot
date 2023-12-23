package com.aymen.telegrambot.feature_chat.presentation.chat

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.unit.dp
import com.aymen.telegrambot.feature_chat.presentation.chat.components.MessageText
import com.aymen.telegrambot.feature_chat.presentation.chat.components.TelegramOutlinedTextField

@Composable
fun ChatScreen(
    state: ChatState,
    onEvent: (ChatEvent) -> Unit
) {

    val focusManager = LocalFocusManager.current

    LaunchedEffect(Unit) {
        onEvent(ChatEvent.StartPoling)
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        TelegramOutlinedTextField(
            text = state.chatId,
            onTextChange = { chatId ->
                onEvent(ChatEvent.OnChatIdChange(chatId))
            },
            modifier = Modifier.fillMaxWidth(),
            label = "Chat Id"
        )
        TelegramOutlinedTextField(
            text = state.text,
            onTextChange = { text ->
                onEvent(ChatEvent.OnTextChange(text))
            },
            modifier = Modifier.fillMaxWidth(),
            label = "Text"
        )
        Spacer(modifier = Modifier.height(8.dp))
        Button(
            onClick = {
                onEvent(ChatEvent.SendMessage(state.text, state.chatId))
                onEvent(ChatEvent.OnTextChange(""))
                focusManager.clearFocus()
            },
            shape = RoundedCornerShape(8.dp),
            colors = ButtonDefaults.buttonColors(
                containerColor = MaterialTheme.colorScheme.primary,
                contentColor = MaterialTheme.colorScheme.secondary
            ),
            modifier = Modifier.fillMaxWidth()
        ) {
            Text(text = "Send Message")
        }
        Text(text = "Updates:")
        Spacer(modifier = Modifier.height(8.dp))
        AnimatedVisibility(visible = state.updates.isNotEmpty()) {
            LazyColumn(
                modifier = Modifier.fillMaxSize(),
                verticalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                items(state.updates) { update ->
                    MessageText(update.message.text)
                }
            }
        }
        AnimatedVisibility(visible = state.updates.isEmpty()) {
            Text(text = "No Updates Yet!")
        }
    }

}