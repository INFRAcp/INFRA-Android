package com.example.infraandroid.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.navigation.findNavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.fragment.findNavController
import com.example.infraandroid.R
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

        mBinding!!.loginButton.setOnClickListener {
            // 로그인할 때 유저 id를 ChatFragment에 전달
            it.findNavController().navigate(
                LoginFragmentDirections.actionLoginFragmentToChatFragment(
                    mBinding!!.idEdittext.text.toString()
                )
            )
            //it.findNavController().navigate(R.id.action_login_fragment_to_home_fragment)
        }

        mBinding!!.registerButton.setOnClickListener {
            val inputId = mBinding!!.idEdittext.text.toString()
            val inputPw = mBinding!!.pwEdittext.text.toString()
        }

        return mBinding?.root
    }



    override fun onDestroyView() {
        mBinding = null
        super.onDestroyView()
    }
}