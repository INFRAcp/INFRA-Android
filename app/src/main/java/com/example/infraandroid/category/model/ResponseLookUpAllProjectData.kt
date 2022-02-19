package com.example.infraandroid.category.model

data class ResponseLookUpAllProjectData(
    val code: Int,
    val isSuccess: Boolean,
    val message: String,
    val result: ArrayList<Result>
)
data class Result(
    val pj_categoryName: String,
    val pj_daysub: Int,
    val pj_deadline: String,
    val pj_header: String,
    val pj_like: Int,
    val pj_name: String,
    val pj_num: Int,
    val pj_progress: String,
    val pj_recruit: String,
    val pj_recruitPerson: Int,
    val pj_totalPerson: Int,
    val user_id: String
)