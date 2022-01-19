package com.example.infraandroid.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.infraandroid.dataclass.RecommedProject
import com.example.infraandroid.databinding.FragmentHomeBinding
import com.example.infraandroid.adapter.RecomProjectRVAdapter

class HomeFragment : Fragment() {
    private  var mBinding : FragmentHomeBinding? = null
    private var recommedProjectData = ArrayList<RecommedProject>();

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
        //리사이클러뷰 어뎁터 연결
        mBinding?.homeHotProjectRecycleview?.adapter = RecomProjectRVAdapter()

        //레이아웃 매니저 설정
        //val layoutManager = LinearLayoutManager(this)
        //layoutManager.orientation = LinearLayoutManager.HORIZONTAL
        mBinding?.homeHotProjectRecycleview?.layoutManager = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)

    }

    override fun onDestroyView() {
        mBinding = null
        super.onDestroyView()
    }
}