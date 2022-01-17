package com.example.infraandroid.fragments

import android.content.ContentValues.TAG
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.navigation.findNavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.fragment.findNavController
import com.example.infraandroid.R
import com.example.infraandroid.UserId
import com.example.infraandroid.databinding.FragmentLoginBinding

class LoginFragment : Fragment() {
    private  var mBinding : FragmentLoginBinding? = null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val binding = FragmentLoginBinding.inflate(inflater, container, false)

        mBinding = binding

        // 로그인 버튼을 눌렀을 때
        mBinding!!.loginButton.setOnClickListener {
            // 로그인할 때 유저 id를 전역변수에 저장
            val inputId = mBinding!!.idEdittext.text.toString()
            val inputPw = mBinding!!.pwEdittext.text.toString()
            UserId.setUserId(inputId)
            Log.d(TAG, "유저아이디 로그인 프레그먼트" + UserId.userId)
            // 로그인 버튼을 누르면 home_fragment로 이동
            it.findNavController().navigate(R.id.action_login_fragment_to_home_fragment)
        }

        // 회원가입 버튼을 눌렀을 때
        mBinding!!.registerButton.setOnClickListener {
        }

        return mBinding?.root
    }

    override fun onDestroyView() {
        mBinding = null
        super.onDestroyView()
    }
}