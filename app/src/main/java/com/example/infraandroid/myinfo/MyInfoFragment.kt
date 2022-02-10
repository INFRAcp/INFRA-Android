package com.example.infraandroid.myinfo

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.NavController
import androidx.navigation.Navigation
import androidx.navigation.findNavController
import com.example.infraandroid.InfraApplication
import com.example.infraandroid.R
import com.example.infraandroid.databinding.FragmentMyInfoBinding
import com.example.infraandroid.id.data.SharedIdViewModel

//작성자 : 이은진
//작성일 : 2022.02.03
// Update
// 내 정보에 이름 출력 (작성자 : 신승민)
class MyInfoFragment : Fragment() {
    private  var mBinding : FragmentMyInfoBinding? = null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val binding = FragmentMyInfoBinding.inflate(inflater, container, false)

        mBinding = binding
        mBinding?.myInfoUserNameTv?.text = getString(R.string.my_info_name).format(InfraApplication.prefs.getString("userNickName","null"))
        return mBinding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

//함수로 간단하게 묶기
        mBinding!!.myInfoMyInfoLayout.setOnClickListener {
            it.findNavController().navigate(R.id.action_my_info_fragment_to_my_info_modify_fragment)
        }
        mBinding!!.myInfoIntroPageLayout.setOnClickListener {
            it.findNavController().navigate(R.id.action_my_info_fragment_to_myInfoInformationFragment)
        }
        mBinding!!.myInfoMyIdeaLinearlayout.setOnClickListener {
            it.findNavController().navigate(R.id.action_my_info_fragment_to_myInfoMyIdeaFragment)
        }
        mBinding!!.myInfoEvaluationLinearlayout.setOnClickListener {
            it.findNavController().navigate(R.id.action_my_info_fragment_to_myInfoTeamMemberEvaluationFragment)
        }
    }

    override fun onDestroyView() {
        mBinding = null
        super.onDestroyView()
    }
}