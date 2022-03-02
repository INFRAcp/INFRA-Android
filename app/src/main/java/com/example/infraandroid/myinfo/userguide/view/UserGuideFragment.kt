package com.example.infraandroid.myinfo.userguide.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.fragment.app.Fragment
import androidx.navigation.findNavController
import com.example.infraandroid.R
import com.example.infraandroid.databinding.FragmentUserGuideBinding

class UserGuideFragment: Fragment() {
    lateinit var binding: FragmentUserGuideBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentUserGuideBinding.inflate(inflater,container,false)

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val registerDownIv = binding.registerDownIv
        val registerUpIv = binding.registerUpIv
        val userGuideRegisterTextView = binding.userGuideRegisterTextView

        val interestDownIv = binding.interestDownIv
        val interestUpIv = binding.interestUpIv
        val userGuideInterestTextView = binding.userGuideInterestTextView

        val memberDownIv = binding.memberDownIv
        val memberUpIv = binding.memberUpIv
        val userGuideMemberTextView = binding.userGuideMemberTextView

        val otherProjectDownIv = binding.otherProjectDownIv
        val otherProjectUpIv = binding.otherProjectUpIv
        val userGuideOtherProjectTextView = binding.userGuideOtherProjectTextView

        //myinfo로 이동
        binding.userGuideBackButton.setOnClickListener {
            it.findNavController().navigate(R.id.action_userGuideFragment_to_my_info_fragment)

        }

        //함수로 빼는 방법 생각해보기
        //프로젝트 등록 방법
        //open
        registerDownIv.setOnClickListener {
            registerDownIv.visibility=View.GONE
            registerUpIv.visibility=View.VISIBLE
            userGuideRegisterTextView.visibility=View.VISIBLE
        }
        //close
        registerUpIv.setOnClickListener {
            registerDownIv.visibility=View.VISIBLE
            registerUpIv.visibility=View.GONE
            userGuideRegisterTextView.visibility=View.GONE
        }

        //관심분야 설정
        //open
        interestDownIv.setOnClickListener {
            interestDownIv.visibility=View.GONE
            interestUpIv.visibility=View.VISIBLE
            userGuideInterestTextView.visibility=View.VISIBLE
        }
        //close
        interestUpIv.setOnClickListener {
            interestDownIv.visibility=View.VISIBLE
            interestUpIv.visibility=View.GONE
            userGuideInterestTextView.visibility=View.GONE
        }

        //새로운 팀원 찾는 방법
        //open
        memberDownIv.setOnClickListener {
            memberDownIv.visibility=View.GONE
            memberUpIv.visibility=View.VISIBLE
            userGuideMemberTextView.visibility=View.VISIBLE
        }
        //close
        memberUpIv.setOnClickListener {
            memberDownIv.visibility=View.VISIBLE
            memberUpIv.visibility=View.GONE
            userGuideMemberTextView.visibility=View.GONE
        }

        //다른 프로젝트도 동시에 가능한지
        //open
        otherProjectDownIv.setOnClickListener {
            otherProjectDownIv.visibility=View.GONE
            otherProjectUpIv.visibility=View.VISIBLE
            userGuideOtherProjectTextView.visibility=View.VISIBLE
        }
        //close
        otherProjectUpIv.setOnClickListener {
            otherProjectDownIv.visibility=View.VISIBLE
            otherProjectUpIv.visibility=View.GONE
            userGuideOtherProjectTextView.visibility=View.GONE
        }

    }

    override fun onDestroyView() {
        super.onDestroyView()
    }
}