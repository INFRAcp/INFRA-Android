package com.example.infraandroid.myinfo.team

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.infraandroid.databinding.FragmentTeamEvaluationManagementBinding

class TeamEvaluationManageFragment: Fragment() {
    private var mBinding: FragmentTeamEvaluationManagementBinding? = null
    private val teamEvalManagementAdapter = TeamEvalManagementAdapter()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding = FragmentTeamEvaluationManagementBinding.inflate(inflater, container, false)
        mBinding = binding
        return mBinding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        mBinding?.teamEvalManagementRecyclerview?.adapter = teamEvalManagementAdapter
        teamEvalManagementAdapter.teamEvalManagementList.addAll(
            listOf(
                TeamEvalManagementList(3,"이프라","4.5")
            )
        )
        teamEvalManagementAdapter.notifyDataSetChanged()
    }

    override fun onDestroyView() {
        mBinding = null
        super.onDestroyView()
    }
}