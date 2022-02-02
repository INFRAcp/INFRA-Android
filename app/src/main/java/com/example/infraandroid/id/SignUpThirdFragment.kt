package com.example.infraandroid.id

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.infraandroid.databinding.FragmentSignUpThirdBinding


// 회원가입 third depth 페이지 (닉네임 설정)
// 작성자 : 신승민
// 작성일 : 2022-02-02

class SignUpThirdFragment : Fragment(){
    private  var mBinding : FragmentSignUpThirdBinding? = null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val binding = FragmentSignUpThirdBinding.inflate(inflater, container, false)

        mBinding = binding

        return mBinding?.root
    }

    override fun onDestroyView() {
        mBinding = null
        super.onDestroyView()
    }
}