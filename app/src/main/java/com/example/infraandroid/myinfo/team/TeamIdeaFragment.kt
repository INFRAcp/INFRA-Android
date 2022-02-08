package com.example.infraandroid.myinfo.team

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.findNavController
import com.example.infraandroid.R
import com.example.infraandroid.databinding.FragmentTeamIdeaBinding

class TeamIdeaFragment : Fragment() {
    private var mBinding : FragmentTeamIdeaBinding? = null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding = FragmentTeamIdeaBinding.inflate(inflater, container, false)
        mBinding = binding
        return mBinding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        mBinding?.teamIdeaModifyBtnTv?.setOnClickListener{
            it.findNavController().navigate(R.id.action_myInfoTeamIdeaFragment_to_myInfoProjectModifyFragment)
        }
    }

    override fun onDestroyView() {
        mBinding = null
        super.onDestroyView()
    }
}