package com.aymen.telegrambot.core

data class Result<T>(
    val ok: Boolean,
    val result: T
)
