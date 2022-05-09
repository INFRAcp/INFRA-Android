package com.infra.infraandroid.myinfo.teammembereval.view.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.infra.infraandroid.databinding.FragmentTeamEvaluationWriteBinding
import com.infra.infraandroid.myinfo.teammembereval.model.TeamEvalMemberList
import com.infra.infraandroid.myinfo.teammembereval.model.TeamEvalTeamList
import com.infra.infraandroid.myinfo.teammembereval.view.adapter.TeamEvalTeamListAdapter

//내 정보>팀원 평가 관리 > 평가 작성 탭
class TeamMemberEvalWriteFragment :Fragment() {
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
                    TeamEvalMemberList(3,"외국인인척하는 사람"), TeamEvalMemberList(3, "덩킹도너츠"),
                    TeamEvalMemberList(3, "회의 러버")
                )),
                TeamEvalTeamList("어플제작","인프라","2022.1.10 - 2022.2.11", mutableListOf(
                    TeamEvalMemberList(3,"최웅")
                )
        )
            ))
    }

    override fun onDestroyView() {
        mBinding = null
        super.onDestroyView()
    }
}