package com.example.infraandroid.category.model

data class ResponseLookUpAllProjectData(
    val isSuccess: Boolean,
    val code: Int,
    val message: String,
    val result: ArrayList<Result>?,
){
    data class Result(
        val user_id: String,
        val pj_num: Int,
        val pj_header: String,
        val pj_categoryName: String,
        val pj_progress: String,
        val pj_deadline: String,
        val pj_totalPerson: Int,
        val pj_recruitPerson: Int,
        val pj_recruit: String,
        val pj_daysub: Int,
        val pj_like: Int,
        val hashtag : ArrayList<String>,
        val pj_photo: ArrayList<String>,
        )
}