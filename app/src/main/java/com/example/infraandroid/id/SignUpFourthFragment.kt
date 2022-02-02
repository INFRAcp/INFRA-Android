package com.example.infraandroid.id

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.infraandroid.databinding.FragmentSignUpFourthBinding


// 회원가입 fourth depth 페이지 (회원 가입 완료 페이지)
// 작성자 : 신승민
// 작성일 : 2022-02-02

class SignUpFourthFragment : Fragment(){
    private  var mBinding : FragmentSignUpFourthBinding? = null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val binding = FragmentSignUpFourthBinding.inflate(inflater, container, false)

        mBinding = binding

        return mBinding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


    }

    override fun onDestroyView() {
        mBinding = null
        super.onDestroyView()
    }
}