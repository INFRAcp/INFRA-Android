package com.example.infraandroid.myinfo

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.NavController
import androidx.navigation.Navigation
import androidx.navigation.findNavController
import com.example.infraandroid.R
import com.example.infraandroid.databinding.FragmentHomeBinding
import com.example.infraandroid.databinding.FragmentMyInfoBinding
//작성자 : 이은진
//작성일 : 2022.02.03
class MyInfoFragment : Fragment() {
    private  var mBinding : FragmentMyInfoBinding? = null
//    lateinit var navController: NavController

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val binding = FragmentMyInfoBinding.inflate(inflater, container, false)

        mBinding = binding

        return mBinding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

//        navController = Navigation.findNavController(view)
//        mBinding!!.myInfoMyInfoTv.setOnClickListener {
//            navController.navigate(R.id.action_my_info_fragment_to_my_info_modify_fragment)
//        }
//함수로 간단하게 묶기
        mBinding!!.myInfoMyInfoLayout.setOnClickListener {
            it.findNavController().navigate(R.id.action_my_info_fragment_to_my_info_modify_fragment)
        }
        mBinding!!.myInfoIntroPageLayout.setOnClickListener {
            it.findNavController().navigate(R.id.action_my_info_fragment_to_category_information_fragment)
        }
        //내 정보에 임시로 넣어둠(팀 아이디어 프래그먼트) 수정 필요
        mBinding!!.myInfoBookmarkLayout.setOnClickListener {
            it.findNavController().navigate(R.id.action_my_info_fragment_to_category_team_idea_fragment)
        }
    }

    override fun onDestroyView() {
        mBinding = null
        super.onDestroyView()
    }
}