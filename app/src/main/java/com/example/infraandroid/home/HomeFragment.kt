package com.example.infraandroid.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.infraandroid.databinding.FragmentHomeBinding

class HomeFragment : Fragment() {
    private var mBinding : FragmentHomeBinding? = null
    private val RecomprojectRVAdapter = RecomProjectRVAdapter()
    private val HotProjectRVAdapter = com.example.infraandroid.home.HotProjectRVAdapter()
    private val SelfDevelopeRVAdapter = com.example.infraandroid.home.SelfDevelopeRVAdapter()
    private val recomprojectList = mutableListOf<RecommedProject>()
    private val hotpjectList = mutableListOf<HotProject>()
    private val selfdevList = mutableListOf<SelfDevelope>()

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

        //프로젝트 제안 리사이클러뷰 어뎁터
        mBinding?.homeRecommedProjectRecycleview?.adapter = RecomprojectRVAdapter
        val tempData = RecommedProject("test","test","test","test","test","test", 3)
        recomprojectList.add(tempData)
        RecomprojectRVAdapter.recomprojectList.addAll(recomprojectList)
        RecomprojectRVAdapter.notifyDataSetChanged()

        //핫 프로젝트 리사이클러뷰 어뎁터
        mBinding?.homeHotProjectRecycleview?.adapter = HotProjectRVAdapter
        val tempData2 = HotProject("hottest","hottest","hottest","hottest","hottest","hottest", 3)
        hotpjectList.add(tempData2)
        HotProjectRVAdapter.hotprojectList.addAll(hotpjectList)
        HotProjectRVAdapter.notifyDataSetChanged()

        //자기개발 리사이클러뷰 어뎁터
        mBinding?.homeSelfDevelopeRecycleview?.adapter = SelfDevelopeRVAdapter
        val tempData3 = SelfDevelope("selftest","selftest","selftest","selftest","selftest","selftest", 3)
        selfdevList.add(tempData3)
        SelfDevelopeRVAdapter.selfdevelopeList.addAll(selfdevList)
        SelfDevelopeRVAdapter.notifyDataSetChanged()
    }
    override fun onDestroyView() {
        mBinding = null
        super.onDestroyView()
    }
}