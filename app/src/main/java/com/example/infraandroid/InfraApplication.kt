package com.example.infraandroid

import android.app.Application
import android.content.Context
import com.example.infraandroid.id.api.RequestUserData
import com.example.infraandroid.id.data.Prefs
import com.kakao.sdk.common.KakaoSdk

class InfraApplication : Application() {
    companion object{
        var appContext : Context? = null

        lateinit var prefs: Prefs
        var chatRoomIndex = 1
        var createdRoomIndex = 1
    }

    override fun onCreate() {
        prefs=Prefs(applicationContext)
        super.onCreate()
        KakaoSdk.init(this, getString(R.string.kakao_app_key))
    }
}