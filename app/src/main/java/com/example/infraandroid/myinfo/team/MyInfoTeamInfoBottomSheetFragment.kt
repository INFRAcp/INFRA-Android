package com.example.infraandroid.myinfo.team

import android.app.Dialog
import android.view.View
import com.example.infraandroid.R
import com.google.android.material.bottomsheet.BottomSheetDialogFragment

//작성자 : 이은진
//작성일 : 2022.02.07
class MyInfoTeamInfoBottomSheetFragment: BottomSheetDialogFragment() {
    override fun setupDialog(dialog: Dialog, style: Int) {
        val contentView = View.inflate(context, R.layout.my_info_team_info, null)
        dialog?.setContentView(contentView)
    }
}