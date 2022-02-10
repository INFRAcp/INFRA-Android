package com.example.infraandroid.myinfo.team

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.infraandroid.databinding.FragmentTeamEvaluationWriteBinding

class TeamEvaluationWriteFragment :Fragment() {
    private var mBinding : FragmentTeamEvaluationWriteBinding? = null
    //private var teamList = MutableList<TeamEvalTeamList>()
    //private val teamEvalTeamListAdapter = TeamEvalMemberListAdapter(requireContext())
    private val teamEvalTeamListAdapter = TeamEvalTeamListAdapter()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding = FragmentTeamEvaluationWriteBinding.inflate(inflater,container,false)
        mBinding = binding
        return mBinding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        mBinding?.teamEvalWriteRecyclerview?.adapter = teamEvalTeamListAdapter
        teamEvalTeamListAdapter.teamEvalTeamList.addAll(
            mutableListOf(
                TeamEvalTeamList("어플제작","건축모드","2021.10.1 - 2022.1.30", mutableListOf(
                    TeamEvalMemberList(3,"외국인인척하는 사람")
                ))
            )
        )

        TeamEvalMemberListAdapter().notifyDataSetChanged()
    }

    override fun onDestroyView() {
        mBinding = null
        super.onDestroyView()
    }
}