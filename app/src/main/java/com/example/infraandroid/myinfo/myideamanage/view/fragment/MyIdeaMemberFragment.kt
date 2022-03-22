package com.example.infraandroid.myinfo.myideamanage.view.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.infraandroid.databinding.FragmentTeamMemberBinding
import com.example.infraandroid.myinfo.myideamanage.model.MyIdeaMemberManageInfo
import com.example.infraandroid.myinfo.myideamanage.model.MyIdeaMemberApplyManageInfo
import com.example.infraandroid.myinfo.myideamanage.view.adapter.MyIdeaMemberApplyAdapter
import com.example.infraandroid.myinfo.teammembereval.view.adapter.TeamMemberAdapter

// 내 정보 > 내 아이디어 탭 레이아웃의 팀원 탭
class MyIdeaMemberFragment : Fragment() {
    private var mBinding : FragmentTeamMemberBinding? = null
    //private lateinit var mFragmentManager: FragmentManager
    private val TeamMemberAdapter = TeamMemberAdapter()
    private val TeamMemberAppliAdapter = MyIdeaMemberApplyAdapter()
    private val teamMemberInfo = mutableListOf<MyIdeaMemberManageInfo>()
    private val teamMemberApplicationInfo = mutableListOf<MyIdeaMemberApplyManageInfo>()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding = FragmentTeamMemberBinding.inflate(inflater, container, false)
        mBinding = binding
        return mBinding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

//        //팀원 관리 어뎁터 연결
//        mBinding?.teamMemberManagementRecyclerview?.adapter = TeamMemberAdapter
//        val tempDataMember = MyIdeaMemberManageInfo(3,"김프라")
//        teamMemberInfo.add(tempDataMember)
//        TeamMemberAdapter.teamMemderlist.addAll(teamMemberInfo)
//        TeamMemberAdapter.notifyDataSetChanged()

        //신청 관리 어뎁터 연결
        mBinding?.applicationManagementRecyclerview?.adapter = TeamMemberAppliAdapter
        val tempDataAppli = MyIdeaMemberApplyManageInfo(3,"나받아줘")
        teamMemberApplicationInfo.add(tempDataAppli)
        TeamMemberAppliAdapter.teamMemberAppliList.addAll(teamMemberApplicationInfo)
        TeamMemberAppliAdapter.notifyDataSetChanged()
    }

    override fun onDestroyView() {
        mBinding = null
        super.onDestroyView()
    }
}