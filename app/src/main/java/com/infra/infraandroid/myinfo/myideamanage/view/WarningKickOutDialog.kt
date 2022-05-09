package com.infra.infraandroid.myinfo.myideamanage.view

import android.content.res.Resources
import android.graphics.Rect
import android.os.Bundle
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.DialogFragment
import com.infra.infraandroid.R
import com.infra.infraandroid.databinding.MyInfoMyIdeaKickOutWarningBinding

// 내 정보 > 내 아이디어 > 팀원 탭 > 신청관리 > 강퇴 시 나오는 dialog
class WarningKickOutDialog : DialogFragment() {
    private var mBinding : MyInfoMyIdeaKickOutWarningBinding?=null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        setWidthPercent(90)
    }

    fun DialogFragment.setWidthPercent(percentage: Int) {
        val percent = percentage.toFloat() / 100
        val dm = Resources.getSystem().displayMetrics
        val rect = dm.run { Rect(0, 0, widthPixels, heightPixels) }
        val percentWidth = rect.width() * percent
        dialog?.window?.setLayout(percentWidth.toInt(), ViewGroup.LayoutParams.WRAP_CONTENT)
        dialog?.window?.setBackgroundDrawableResource(R.drawable.warning_background)
    }
}