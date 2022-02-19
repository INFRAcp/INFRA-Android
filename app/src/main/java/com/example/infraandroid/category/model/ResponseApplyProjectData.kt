package com.example.infraandroid.category.model

import org.w3c.dom.Comment

data class ResponseApplyProjectData(
    val isSuccess: Boolean,
    val code: Int,
    val message: String,
    val result: Comment?
){
    data class Comment(
        val comment: String,
    )
}