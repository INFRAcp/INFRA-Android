package com.example.infraandroid.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.infraandroid.dataclass.RecommedProject
import com.example.infraandroid.adapter.RecomProjectRVAdapter
import com.example.infraandroid.databinding.FragmentHomeBinding

class HomeFragment : Fragment() {
    private var mBinding : FragmentHomeBinding? = null
    private val projectRVAdapter = RecomProjectRVAdapter()
    private val projectList = mutableListOf<RecommedProject>()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val binding = FragmentHomeBinding.inflate(inflater, container, false)
        mBinding = binding

        return mBinding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        //리사이클러뷰 어뎁터
        mBinding?.homeHotProjectRecycleview?.adapter = projectRVAdapter
        val tempData = RecommedProject("test","test","test","test", 3)
        projectList.add(tempData)
        projectRVAdapter.recomprojectList.addAll(projectList)
        projectRVAdapter.notifyDataSetChanged()
    }
    override fun onDestroyView() {
        mBinding = null
        super.onDestroyView()
    }
}