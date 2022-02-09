package com.example.infraandroid.myinfo

import android.app.Dialog
import android.content.Context
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import com.example.infraandroid.R
import com.example.infraandroid.myinfo.team.TeamMemberAdapter

class WarningKickOutDialog(context: Context): Dialog(context) {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.my_info_my_idea_kick_out_warning)

        //배경을 투명하게 설정
        window!!.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
    }
}