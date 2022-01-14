package com.example.infraandroid

import android.app.Application

class UserId : Application() {
    companion object{
        lateinit var userId : String
        @JvmName("setUserId1")
        fun setUserId(id: String){
            this.userId = id
        }
    }
}