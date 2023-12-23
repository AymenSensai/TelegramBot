package com.aymen.telegrambot.feature_chat.data.remote.dto

import com.google.gson.annotations.SerializedName

data class UpdateDto(
    @SerializedName("update_id")
    val updateId: Long,
    val message: MessageDto
)