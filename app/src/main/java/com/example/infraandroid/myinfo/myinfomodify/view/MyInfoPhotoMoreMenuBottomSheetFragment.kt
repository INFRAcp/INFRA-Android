package com.example.infraandroid.myinfo.myinfomodify.view

import android.app.Dialog
import android.view.View
import com.example.infraandroid.R
import com.google.android.material.bottomsheet.BottomSheetDialogFragment

//작성자 : 이은진
//작성일 : 2022.02.04
//내 정보 > 내 정보 > 프로필에 있는 + 버튼 클릭시 나오는 bottomsheet
class MyInfoPhotoMoreMenuBottomSheetFragment : BottomSheetDialogFragment() {
    override fun setupDialog(dialog: Dialog, style: Int) {
        val contentView = View.inflate(context, R.layout.photo_more_menu, null)
        dialog?.setContentView(contentView)
    }
}