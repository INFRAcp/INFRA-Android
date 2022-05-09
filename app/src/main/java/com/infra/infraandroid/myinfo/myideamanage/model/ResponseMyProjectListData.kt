package com.infra.infraandroid.myinfo.myideamanage.model

data class ResponseMyProjectListData(
    val code: Int,
    val isSuccess: Boolean,
    val message: String,
    val result: ArrayList<Result>
    ) {
        data class Result(
            val hashtag: ArrayList<String>,
            val pj_categoryName: String,
            val pj_daysub: Int,
            val pj_deadline: String,
            val pj_header: String,
            val pj_num: Int,
            val pj_photo: ArrayList<String>,
            val pj_progress: String,
            val pj_recruit: String,
            val pj_recruitPerson: Int,
            val pj_subCategoryNum: Int,
            val pj_totalPerson: Int
        )
}