package com.aymen.telegrambot.feature_chat.data.remote

import com.aymen.telegrambot.feature_chat.data.remote.dto.UpdateDto
import retrofit2.Response
import com.aymen.telegrambot.core.Result
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Query

interface TelegramApi {

    @GET("getUpdates")
    suspend fun getUpdates(@Query("offset") offset: Long, @Query("timeout") timeout: Int = 60):
            Response<Result<List<UpdateDto>>>

    @GET("getUpdates")
    suspend fun getUpdates(@Query("timeout") timeout: Int = 60):
            Response<Result<List<UpdateDto>>>

    @POST("sendMessage")
    @FormUrlEncoded
    suspend fun sendMessage(
        @Field("chat_id") chatId: String,
        @Field("text") text: String
    ): Response<Result<Any>>

}