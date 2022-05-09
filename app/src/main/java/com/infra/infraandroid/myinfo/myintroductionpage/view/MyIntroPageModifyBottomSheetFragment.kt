package com.infra.infraandroid.myinfo.myintroductionpage.view

import android.app.Activity
import android.app.Dialog
import android.os.Bundle
import android.util.DisplayMetrics
import android.view.View
import com.infra.infraandroid.R
import com.infra.infraandroid.databinding.MyInfoModifyMenuBinding
import com.google.android.material.bottomsheet.BottomSheetBehavior
import com.google.android.material.bottomsheet.BottomSheetDialog
import com.google.android.material.bottomsheet.BottomSheetDialogFragment

//작성자 : 이은진
//작성일 : 2022.02.05
//내 정보 > 소개페이지 > 수정 시 나오는 bottomsheet
class MyIntroPageModifyBottomSheetFragment : BottomSheetDialogFragment() {
    private  var mBinding : MyInfoModifyMenuBinding? = null

//    override fun onCreateView(
//        inflater: LayoutInflater,
//        container: ViewGroup?,
//        savedInstanceState: Bundle?
//    ): View? {
//        return super.onCreateView(inflater, container, savedInstanceState)
//        return inflater.inflate(R.layout.my_info_modify_menu, container, false)
//    }

    override fun setupDialog(dialog: Dialog, style: Int) {
        val contentView = View.inflate(context, R.layout.my_info_modify_menu, null)
        dialog?.setContentView(contentView)

    }

//    override fun onActivityCreated(savedInstanceState: Bundle?) {
//        super.onActivityCreated(savedInstanceState)
//        mBinding!!.myInfoCancelButton.setOnClickListener {
//            dismiss()
//        }
//        mBinding!!.myInfoSaveButton.setOnClickListener {
//            dismiss()
//        }
//    }

    //bottomsheet size 90/100으로 설정해주는 코드
    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        val dialog: Dialog = super.onCreateDialog(savedInstanceState)
        dialog.setOnShowListener {
            val bottomSheetDialog = it as BottomSheetDialog
            setupRatio(bottomSheetDialog)
        }
        return dialog
    }
    private fun setupRatio(bottomSheetDialog: BottomSheetDialog) {
        //id = com.google.android.material.R.id.design_bottom_sheet for Material Components
        //id = android.support.design.R.id.design_bottom_sheet for support librares
        val bottomSheet = bottomSheetDialog.findViewById<View>(com.google.android.material.R.id.design_bottom_sheet) as View
        val behavior = BottomSheetBehavior.from(bottomSheet)
        val layoutParams = bottomSheet!!.layoutParams
        layoutParams.height = getBottomSheetDialogDefaultHeight()
        bottomSheet.layoutParams = layoutParams
        behavior.state = BottomSheetBehavior.STATE_EXPANDED
    }
    private fun getBottomSheetDialogDefaultHeight(): Int {
        return getWindowHeight() * 90 / 100
    }
    private fun getWindowHeight(): Int {
        // Calculate window height for fullscreen use
        val displayMetrics = DisplayMetrics()
        (context as Activity?)!!.windowManager.defaultDisplay.getMetrics(displayMetrics)
        return displayMetrics.heightPixels
    }
}