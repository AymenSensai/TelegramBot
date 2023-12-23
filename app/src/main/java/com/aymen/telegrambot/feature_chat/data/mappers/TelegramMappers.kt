package com.aymen.telegrambot.feature_chat.data.mappers

import com.aymen.telegrambot.feature_chat.data.remote.dto.MessageDto
import com.aymen.telegrambot.feature_chat.data.remote.dto.UpdateDto
import com.aymen.telegrambot.feature_chat.domain.model.Message
import com.aymen.telegrambot.feature_chat.domain.model.Update

fun UpdateDto.toUpdate(): Update {
    return Update(
        updateId = updateId,
        message = message.toMessage()
    )
}

fun MessageDto.toMessage(): Message {
    return Message(
        text = text
    )
}