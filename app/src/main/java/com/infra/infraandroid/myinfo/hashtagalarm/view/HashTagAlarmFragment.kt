package com.infra.infraandroid.myinfo.hashtagalarm.view

import android.content.Context.INPUT_METHOD_SERVICE
import android.view.inputmethod.InputMethodManager
import android.widget.EditText
import androidx.core.view.isVisible
import com.infra.infraandroid.R
import com.infra.infraandroid.databinding.FragmentHashTagAlarmBinding
import com.infra.infraandroid.util.BaseFragment
import com.google.android.material.chip.Chip

// 2022-02-24 해시태크 알람페이지 (작성자 : 신승민)

class HashTagAlarmFragment : BaseFragment<FragmentHashTagAlarmBinding>(R.layout.fragment_hash_tag_alarm) {
    override fun FragmentHashTagAlarmBinding.onCreateView(){

    }

    override fun FragmentHashTagAlarmBinding.onViewCreated(){
        binding.addHashTagImageButton.setOnClickListener {
            binding.addConstraintLayout.isVisible = true
            binding.inputHashTagEdittext.requestFocus()
            binding.inputHashTagEdittext.showSoftKeyboard()
        }

        binding.addHashTagButton.setOnClickListener {
            if(binding.inputHashTagEdittext.text.isNotEmpty()){
                binding.hashTagChipGroup.addView(Chip(context).apply {
                    text = binding.inputHashTagEdittext.text.toString() // text 세팅
                    isCloseIconVisible = false // x 버튼 보이게 하기
                    setTextAppearanceResource(R.style.CustomChipStyle)
                    setChipBackgroundColorResource(R.color.white)
                    setChipStrokeColorResource(R.color.infra_skyblue_a)
                    chipStrokeWidth = 2f
                })
                binding.inputHashTagEdittext.setText("")
            }
        }
    }

    private fun EditText.showSoftKeyboard(){
        (this.context.getSystemService(INPUT_METHOD_SERVICE) as InputMethodManager)
            .showSoftInput(this, InputMethodManager.SHOW_IMPLICIT)
    }
}