package com.example.infraandroid.myinfo

import android.content.ContentValues
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.viewpager2.widget.ViewPager2
import com.example.infraandroid.databinding.MyInfoTeamMemberEvaluationBinding
import com.example.infraandroid.myinfo.team.*
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator

class MyInfoTeamMemberEvaluationFragment: Fragment() {
    private var mBinding : MyInfoTeamMemberEvaluationBinding? = null
    private lateinit var tabLayout: TabLayout
    private lateinit var viewPager: ViewPager2

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding = MyInfoTeamMemberEvaluationBinding.inflate(inflater,container,false)
        mBinding = binding

        tabLayout = mBinding!!.teamMemberEvaluationTabLayout
        viewPager = mBinding!!.teamMemberEvaluationViewPager2

        return mBinding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        val teamEvalPagerAdapter = TeamEvalPagerAdapter(requireActivity())
        val teamMemberAdapter = TeamPagerAdapter(requireActivity())

        teamEvalPagerAdapter.addFragment(TeamEvaluationWriteFragment())
        teamEvalPagerAdapter.addFragment(TeamEvaluationManageFragment())

        mBinding?.teamMemberEvaluationViewPager2?.adapter = teamEvalPagerAdapter
        mBinding?.teamMemberEvaluationViewPager2?.registerOnPageChangeCallback(object: ViewPager2.OnPageChangeCallback(){
            override fun onPageSelected(position: Int) {
                super.onPageSelected(position)
                Log.d(ContentValues.TAG, "onPageSelected: " + (position+1))
            }
        })

        TabLayoutMediator(tabLayout, viewPager){tab, position ->
            when(position){
                0->{
                    tab.text = "평가 작성"
                }
                1->tab.text = "평가 관리"
            }
        }.attach()
    }

    override fun onDestroyView() {
        mBinding = null
        super.onDestroyView()
    }
}