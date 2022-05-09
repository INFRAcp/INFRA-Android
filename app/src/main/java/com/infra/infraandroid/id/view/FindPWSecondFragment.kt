package com.infra.infraandroid.id.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.fragment.app.Fragment
import androidx.navigation.findNavController
import com.infra.infraandroid.R
import com.infra.infraandroid.databinding.FragmentSecondFindPwBinding

class FindPWSecondFragment : Fragment() {
    private  var mBinding : FragmentSecondFindPwBinding? = null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding = FragmentSecondFindPwBinding.inflate(inflater, container, false)
        mBinding = binding
        return mBinding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val backButton = mBinding?.findPwBackButton as ImageView

        //뒤로 가기 버튼 눌렀을 때 로그인 화면
        backButton.setOnClickListener {
            it.findNavController().navigate(R.id.action_findPWSecondFragment_to_login_fragment)
        }
    }

    override fun onDestroyView() {
        mBinding = null
        super.onDestroyView()
    }

}