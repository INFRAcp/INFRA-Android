package com.example.infraandroid.chat.model

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [ChatRoomEntity::class], version = 1)
abstract class ChatRoomDB: RoomDatabase() {
    abstract fun chatRoomDAO(): ChatRoomDao

    companion object{
        private var INSTANCE: ChatRoomDB? = null

        @Synchronized
        fun getInstance(context: Context): ChatRoomDB? {
            if (INSTANCE == null) {
                synchronized(ChatRoomDB::class) {
                    INSTANCE = Room.databaseBuilder(
                        context.applicationContext,
                        ChatRoomDB::class.java,
                        "chatRoomDB"
                    ).build()
                }
            }
            return INSTANCE
        }
    }
}