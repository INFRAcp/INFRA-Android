package com.example.infraandroid.home.model

import com.google.gson.annotations.SerializedName
import java.lang.reflect.Array
import java.time.LocalDate

data class RequestCreateProjectData(
    @SerializedName("user_id")
    val userId : String,
    @SerializedName("pj_header")
    val projectHeader : String,
    @SerializedName("pj_categoryName")
    val projectCategory : String,
    @SerializedName("pj_content")
    val projectContent : String,
    @SerializedName("pj_name")
    val projectName : String,
    @SerializedName("pj_subCategoryName")
    val projectCategoryDetail : String,
    @SerializedName("pj_progress")
    val projectProgress : String,
    @SerializedName("pj_startTerm")
    val projectStartMaking : String,
    @SerializedName("pj_endTerm")
    val projectEndMaking : String,
    @SerializedName("pj_deadline")
    val projectEndRecruit : String,
    @SerializedName("pj_totalPerson")
    val numberOfTeam : Int,
    @SerializedName("hashtag")
    val hashTags : ArrayList<String>,
    )
