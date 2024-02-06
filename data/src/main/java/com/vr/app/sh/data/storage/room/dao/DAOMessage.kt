package com.vr.app.sh.data.storage.room.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.vr.app.sh.data.storage.model.chat.MessageEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface DAOMessage {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertMessage(message: MessageEntity)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertMessages(messages: List<MessageEntity>)

    @Query("select * from Message WHERE chat_id = :chatId ORDER BY id DESC")
    fun getMessagesInChat(chatId:Int): Flow<List<MessageEntity>>

}