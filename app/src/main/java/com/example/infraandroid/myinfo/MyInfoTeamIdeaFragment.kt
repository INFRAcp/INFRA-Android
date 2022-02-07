package com.example.infraandroid.myinfo

import android.content.ContentValues
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.viewpager2.widget.ViewPager2
import com.example.infraandroid.databinding.MyInfoTeamIdeaBinding
import com.example.infraandroid.myinfo.team.TeamIdeaFragment
import com.example.infraandroid.myinfo.team.TeamMemberFragment
import com.example.infraandroid.myinfo.team.TeamPagerAdapter
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator

class MyInfoTeamIdeaFragment : Fragment() {
    private  var mBinding : MyInfoTeamIdeaBinding? = null
    private lateinit var tabLayout: TabLayout
    private lateinit var viewPager: ViewPager2

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding = MyInfoTeamIdeaBinding.inflate(inflater, container, false)
        mBinding = binding

        tabLayout = mBinding!!.teamIdeaTabLayout
        viewPager = mBinding!!.teamIdeaViewPager2

        return mBinding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        val teamIdeaAdapter = TeamPagerAdapter(requireActivity())

        teamIdeaAdapter.addFragment(TeamIdeaFragment())
        teamIdeaAdapter.addFragment(TeamMemberFragment())

        mBinding?.teamIdeaViewPager2?.adapter = teamIdeaAdapter
        mBinding?.teamIdeaViewPager2?.registerOnPageChangeCallback(object: ViewPager2.OnPageChangeCallback(){
            override fun onPageSelected(position: Int) {
                super.onPageSelected(position)
                Log.d(ContentValues.TAG, "onPageSelected: " + (position+1))
            }
        })

        TabLayoutMediator(tabLayout, viewPager){tab, position ->
            when(position){
                0->{
                    tab.text = "아이디어"
                }
                1->tab.text = "팀원"
            }
        }.attach()
    }

    override fun onDestroyView() {
        mBinding = null
        super.onDestroyView()
    }
}