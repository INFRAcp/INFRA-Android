package com.infra.infraandroid.util

import com.infra.infraandroid.category.model.ProjectService
import com.infra.infraandroid.home.model.CreateProjectService
import com.infra.infraandroid.id.model.*
import com.infra.infraandroid.myinfo.myintroductionpage.model.ProfileViewService
import com.infra.infraandroid.myinfo.myinfomodify.model.MyInfoService
import com.infra.infraandroid.myinfo.myideamanage.model.MyProjectService
import com.infra.infraandroid.myinfo.myinfomodify.model.NicknameDoubleCheckService
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object ServiceCreator {
    private const val BASE_URL = "https://www.qbeom.shop"

    private val interceptor = HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY)

    private val client = OkHttpClient.Builder()
        .addInterceptor(interceptor)
        .build()

    private val retrofit: Retrofit = Retrofit.Builder()
        .baseUrl(BASE_URL)
        .addConverterFactory(GsonConverterFactory.create())
        .client(client)
        .build()

    val userDoubleCheckService : UserDoubleCheckService = retrofit.create(UserDoubleCheckService::class.java)
    val signUpService : SignUpService = retrofit.create(SignUpService::class.java)
    val pwService : PWService = retrofit.create(PWService::class.java)
    val loginService : LoginService = retrofit.create(LoginService::class.java)
    val sendSMSService : SMSService = retrofit.create(SMSService::class.java)
    val profileViewService : ProfileViewService = retrofit.create(ProfileViewService::class.java)
    val createProjectService : CreateProjectService = retrofit.create(CreateProjectService::class.java)
    val myinfoService : MyInfoService = retrofit.create(MyInfoService::class.java)
    val projectService : ProjectService = retrofit.create(ProjectService::class.java)
    val myProjectService : MyProjectService = retrofit.create(MyProjectService::class.java)
    val nicknameDoubleCheckService : NicknameDoubleCheckService = retrofit.create(NicknameDoubleCheckService::class.java)
}