package com.example.infraandroid.id

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.widget.AppCompatButton
import androidx.fragment.app.Fragment
import androidx.navigation.findNavController
import com.example.infraandroid.R
import com.example.infraandroid.databinding.FragmentSignUpFourthBinding


// 회원가입 fourth depth 페이지 (회원 가입 완료 페이지)
// 작성자 : 신승민
// 작성일 : 2022-02-02
// Update
// 2022-02-05 다음버튼 누르면 로그인 페이지로 이동 (작성자 : 신승민)

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

        val nextButton = mBinding?.goToLoginPageButton as AppCompatButton
        nextButton.setOnClickListener{
            it.findNavController().navigate(R.id.action_sign_up_fourth_fragment_to_login_fragment)
        }
    }

    override fun onDestroyView() {
        mBinding = null
        super.onDestroyView()
    }
}