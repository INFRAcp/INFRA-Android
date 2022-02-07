package com.example.infraandroid.myinfo

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.infraandroid.databinding.FragmentMyInfoProjectModifyBinding

// 2022-02-08 내 정보 들어가서 프로젝트 수정하는 .kt파일 (작성자 : 신승민)

class MyInfoProjectModifyFragment : Fragment() {
    private var mBinding : FragmentMyInfoProjectModifyBinding? = null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding = FragmentMyInfoProjectModifyBinding.inflate(inflater, container, false)

        mBinding = binding

        return mBinding?.root
    }

    override fun onDestroyView() {
        mBinding = null
        super.onDestroyView()
    }
}