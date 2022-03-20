package com.example.infraandroid.home.model

data class ResponseSearchProjectData(
    val code: Int,
    val isSuccess: Boolean,
    val message: String,
    val result: List<Result>
){
    data class Result(
        val hashtag: Any,
        val pj_categoryName: String,
        val pj_daysub: Int,
        val pj_deadline: String,
        val pj_header: String,
        val pj_like: Int,
        val pj_num: Int,
        val pj_photo: List<String>,
        val pj_progress: String,
        val pj_recruit: String,
        val pj_recruitPerson: Int,
        val pj_totalPerson: Int,
        val user_id: String
    )
}