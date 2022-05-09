package com.infra.infraandroid.myinfo.myinfointerest

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.findNavController
import com.infra.infraandroid.R
import com.infra.infraandroid.databinding.FragmentMyInfoInterestBinding

//내 정보 > 관심 분야 설정
class MyInfoInterestFragment: Fragment(){
    private var mBinding : FragmentMyInfoInterestBinding? = null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding = FragmentMyInfoInterestBinding.inflate(inflater, container, false)

        mBinding = binding

        return mBinding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        mBinding!!.myInfoInterestBackButton.setOnClickListener {
            it.findNavController().navigate(R.id.action_myInfoInterestFragment_to_my_info_fragment)
        }
    }

    override fun onDestroyView() {
        mBinding = null
        super.onDestroyView()
    }
}