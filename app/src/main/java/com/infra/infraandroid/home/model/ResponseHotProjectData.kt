package com.infra.infraandroid.home.model

data class ResponseHotProjectData(
    val isSuccess: Boolean,
    val code: Int,
    val message: String,
    val result: ArrayList<Result>
    )
{
    data class Result(
        val hashtag: ArrayList<String>,
        val pj_categoryName: String,
        val pj_daysub: Int,
        val pj_deadline: String,
        val pj_header: String,
        val pj_like: Int,
        val pj_num: Int,
        val pj_photo: ArrayList<String>,
        val pj_progress: String,
        val pj_recruit: String,
        val pj_recruitPerson: Int,
        val pj_subCategoryName: String,
        val pj_totalPerson: Int,
        val pj_views: Int,
        val pj_views_1day: Int,
        val user_id: String
    )
}