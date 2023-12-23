package com.aymen.telegrambot.feature_chat.data.repository

import com.aymen.telegrambot.core.Resource
import com.aymen.telegrambot.feature_chat.data.mappers.toUpdate
import com.aymen.telegrambot.feature_chat.data.remote.TelegramApi
import com.aymen.telegrambot.feature_chat.domain.model.Update
import com.aymen.telegrambot.feature_chat.domain.repository.TelegramRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class TelegramRepositoryImpl(private val api: TelegramApi) :
    TelegramRepository {

    override suspend fun getUpdates(offset: Long): Resource<List<Update>> {
        return withContext(Dispatchers.IO) {
            runCatching {
                val response = api.getUpdates(offset)
                if (response.isSuccessful) {
                    Resource.Success(response.body()!!.result.map { it.toUpdate() })
                } else {
                    Resource.Error(response.errorBody()?.string().toString())
                }
            }.getOrElse {
                Resource.Error(it.message.toString())
            }
        }
    }

    override suspend fun getUpdates(): Resource<List<Update>> {
        return withContext(Dispatchers.IO) {
            runCatching {
                val response = api.getUpdates()
                if (response.isSuccessful) {
                    Resource.Success(response.body()!!.result.map { it.toUpdate() })
                } else {
                    Resource.Error(response.errorBody()?.string().toString())
                }
            }.getOrElse {
                Resource.Error(it.message.toString())
            }
        }
    }

    override suspend fun sendMessage(chatId: String, text: String): Resource<Any> {
        return withContext(Dispatchers.IO) {
            runCatching {
                val response = api.sendMessage(chatId, text)
                if (response.isSuccessful) {
                    Resource.Success(response.body()!!.result)
                } else {
                    Resource.Error(response.errorBody()?.string().toString())
                }
            }.getOrElse {
                Resource.Error(it.message.toString())
            }
        }
    }

}
