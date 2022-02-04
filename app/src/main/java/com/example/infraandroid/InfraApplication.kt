package com.example.infraandroid

import android.app.Application
import android.content.Context
import com.kakao.sdk.common.KakaoSdk

class InfraApplication : Application() {
    companion object{
        var appContext : Context? = null

        var userId = ""
        var chatRoomIndex = 1
        var createdRoomIndex = 1
        @JvmName("setUserId1")
        fun setUserId(id: String){
            this.userId = id
        }
    }

    override fun onCreate() {
        super.onCreate()
        KakaoSdk.init(this, getString(R.string.kakao_app_key))
    }
}