package com.example.infraandroid.chat

import android.annotation.SuppressLint
import android.app.Dialog
import android.view.View
import com.example.infraandroid.R
import com.google.android.material.bottomsheet.BottomSheetDialogFragment

class ChatMoreMenuBottomSheetFragment:BottomSheetDialogFragment() {
    @SuppressLint("RestrictedApi")
    override fun setupDialog(dialog: Dialog, style: Int) {
        super.setupDialog(dialog, style)
        val contentView = View.inflate(context, R.layout.chat_more_menu, null)
        dialog?.setContentView(contentView)
    }
}