package com.example.infraandroid.myinfo.myideamanage.model

import com.example.infraandroid.category.model.ResponseLookUpAllProjectData
import okhttp3.MultipartBody
import okhttp3.RequestBody
import retrofit2.Call
import retrofit2.http.*

interface MyProjectService {
    @GET("/project/myProject?")
    fun viewMyProject(
        @Header("X-ACCESS-TOKEN") jwt: String,
        @Header("X-REFRESH-TOKEN") refreshToken: Int,
        @Query("user_id") userId : String
    ): Call<ResponseMyProjectListData>

    @Multipart
    @PATCH("/project/modify")
    fun modifyProject(
        @Header("X-ACCESS-TOKEN") jwt: String,
        @Header("X-REFRESH-TOKEN") refreshToken: Int,
        @Part("jsonList") jsonList: RequestBody,
        @Part images: MultipartBody.Part?,
        ): Call<ResponseModifyProjectData>

    @GET("/project/apply-list?")
    fun viewProjectApply(
        @Header("X-ACCESS-TOKEN") jwt: String,
        @Header("X-REFRESH-TOKEN") refreshToken: Int,
        @Query("user_id") userId: String,
        @Query("pj_num") pjNum: Int?
    ): Call<ResponseViewProjectApplyData>

    @PATCH("/project/member")
    fun acceptTeamMember(
        @Header("X-ACCESS-TOKEN") jwt: String,
        @Header("X-REFRESH-TOKEN") refreshToken: Int,
        @Body body: RequestAcceptData
    ): Call<ResponseAcceptData>

    @HTTP(method="DELETE", hasBody=true, path="/project/del")
    fun deleteProject(
        @Header("X-ACCESS-TOKEN") jwt: String,
        @Header("X-REFRESH-TOKEN") refreshToken: Int,
        @Body body: RequestDeleteProjectData
    ): Call<ResponseDeleteProjectData>

    @POST("/project/team?")
    fun viewProjectMember(
        @Header("X-ACCESS-TOKEN") jwt: String,
        @Header("X-REFRESH-TOKEN") refreshToken: Int,
        @Query("user_id") userId:String,
        @Body body: RequestTeamMemberData
    ): Call<ResponseTeamMemberData>
}