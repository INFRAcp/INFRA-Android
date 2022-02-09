package com.example.infraandroid.myinfo

import android.app.Dialog
import android.content.Context
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import com.example.infraandroid.R

class WarningAcceptDialog(context: Context): Dialog(context) {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.my_info_my_idea_accept_warning)

        //배경을 투명하게 설정
        window!!.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
    }
}