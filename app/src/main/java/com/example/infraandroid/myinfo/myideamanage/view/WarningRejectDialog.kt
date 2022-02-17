package com.example.infraandroid.myinfo.myideamanage.view

import android.app.Dialog
import android.content.Context
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import com.example.infraandroid.R

// 내 정보 > 내 아이디어 > 팀원 탭 > 내 팀원 관리 > 강퇴하기 클릭 시 나오는 dialog
class WarningRejectDialog(context: Context): Dialog(context) {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.my_info_my_idea_reject_warning)

        //배경을 투명하게 설정
        window!!.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
    }
}