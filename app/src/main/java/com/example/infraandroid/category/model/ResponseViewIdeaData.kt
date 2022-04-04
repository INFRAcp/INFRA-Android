package com.example.infraandroid.category.model

data class ResponseViewIdeaData(
    val code: Int,
    val isSuccess: Boolean,
    val message: String,
    val result: Result
){
    data class Result(
        val hashtag: ArrayList<String>,
        val pjLikeCount: Int,
        val pj_categoryName: String,
        val pj_content: String,
        val pj_deadline: String,
        val pj_endTerm: String,
        val pj_header: String,
        val pj_progress: String,
        val pj_recruit: String,
        val pj_recruitPerson: String,
        val pj_startTerm: String,
        val pj_subCategoryName: String,
        val pj_totalPerson: String,
        val pj_views: Int,
        val user_id: String,
        val user_nickname: String,
        val user_prPhoto: String,
        val pj_photo: ArrayList<String>,
        val pj_like: Int,
        val pj_num: Int
    )
}