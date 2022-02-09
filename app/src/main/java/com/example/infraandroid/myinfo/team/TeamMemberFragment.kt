package com.example.infraandroid.myinfo.team

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import com.example.infraandroid.databinding.FragmentTeamMemberBinding
import com.example.infraandroid.myinfo.MyInfoPhotoMoreMenuBottomSheetFragment

class TeamMemberFragment : Fragment() {
    private var mBinding : FragmentTeamMemberBinding? = null
    //private lateinit var mFragmentManager: FragmentManager
    private val TeamMemberAdapter = TeamMemberAdapter()
    private val TeamMemberAppliAdapter = TeamMemberAppliAdapter()
    private val teamMemberInfo = mutableListOf<TeamMemberInfo>()
    private val teamMemberApplicationInfo = mutableListOf<TeamMemberApplicationInfo>()

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

        //팀원 관리 어뎁터 연결
        mBinding?.teamMemberManagementRecyclerview?.adapter = TeamMemberAdapter
        val tempDataMember = TeamMemberInfo(3,"김프라")
        teamMemberInfo.add(tempDataMember)
        TeamMemberAdapter.teamMemderlist.addAll(teamMemberInfo)
        TeamMemberAdapter.notifyDataSetChanged()

        //신청 관리 어뎁터 연결
        mBinding?.applicationManagementRecyclerview?.adapter = TeamMemberAppliAdapter
        val tempDataAppli = TeamMemberApplicationInfo(3,"나받아줘")
        teamMemberApplicationInfo.add(tempDataAppli)
        TeamMemberAppliAdapter.teamMemberAppliList.addAll(teamMemberApplicationInfo)
        TeamMemberAppliAdapter.notifyDataSetChanged()
    }

    override fun onDestroyView() {
        mBinding = null
        super.onDestroyView()
    }
}