package com.example.infraandroid.chat.model

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query

@Dao
interface ChatRoomDao {
    @Query("SELECT * FROM CHATROOM WHERE opponentNickName = :opponentNickName")
    fun checkOpponent(opponentNickName: String): List<ChatRoomEntity>

    @Insert()
    fun insert(chatRoom : ChatRoomEntity)
}