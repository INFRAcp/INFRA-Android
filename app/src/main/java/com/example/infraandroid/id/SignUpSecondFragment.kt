package com.example.infraandroid.id

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.infraandroid.databinding.FragmentSignUpSecondBinding


// 회원가입 second depth 페이지 (휴대폰 본인인증)
// 작성자 : 신승민
// 작성일 : 2022-02-02

class SignUpSecondFragment : Fragment(){
    private  var mBinding : FragmentSignUpSecondBinding? = null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val binding = FragmentSignUpSecondBinding.inflate(inflater, container, false)

        mBinding = binding

        return mBinding?.root
    }

    override fun onDestroyView() {
        mBinding = null
        super.onDestroyView()
    }
}