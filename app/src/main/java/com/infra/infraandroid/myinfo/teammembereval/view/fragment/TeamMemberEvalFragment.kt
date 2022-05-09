package com.infra.infraandroid.myinfo.teammembereval.view.fragment

import android.content.ContentValues
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.viewpager2.widget.ViewPager2
import com.infra.infraandroid.databinding.MyInfoTeamMemberEvaluationBinding
import com.infra.infraandroid.myinfo.myideamanage.view.adapter.TeamPagerAdapter
import com.infra.infraandroid.myinfo.teammembereval.view.adapter.TeamEvalPagerAdapter
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator

//내 정보> 팀원 평가 관리 탭 레이아웃의 부모 탭
class TeamMemberEvalFragment: Fragment() {
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

        teamEvalPagerAdapter.addFragment(TeamMemberEvalWriteFragment())
        teamEvalPagerAdapter.addFragment(TeamMemberEvalManageFragment())

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