package com.example.infraandroid.chat.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "chatRoom")
data class ChatRoomEntity (
    @PrimaryKey(autoGenerate = true) var index: Int?,
    @ColumnInfo(name = "opponentNickName") var opponentNickName: String?) {
}