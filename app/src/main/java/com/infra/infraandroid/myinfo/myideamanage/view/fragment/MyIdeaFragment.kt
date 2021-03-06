package com.infra.infraandroid.myinfo.myideamanage.view.fragment

import android.content.ContentValues
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.findNavController
import androidx.navigation.fragment.navArgs
import androidx.viewpager2.widget.ViewPager2
import com.infra.infraandroid.R
import com.infra.infraandroid.databinding.MyInfoTeamIdeaBinding
import com.infra.infraandroid.myinfo.myideamanage.model.MyProjectViewModel
import com.infra.infraandroid.myinfo.myideamanage.view.adapter.TeamPagerAdapter
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator

// 내 정보 > 내 아이디어 관리 탭 레이아웃의 부모 레이아웃
class MyIdeaFragment : Fragment() {
    private lateinit var viewModel : MyProjectViewModel
    private  var mBinding : MyInfoTeamIdeaBinding? = null
    private lateinit var tabLayout: TabLayout
    private lateinit var viewPager: ViewPager2

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        activity?.run{
            viewModel = ViewModelProvider(this, ViewModelProvider.NewInstanceFactory())
                .get(MyProjectViewModel::class.java)
        }
    }

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

        val args: MyIdeaFragmentArgs by navArgs()
        viewModel.updateObservingProjectNum(args.myProjectNum)
        mBinding?.titleTextView?.text = args.myIdeaTitle

        val bottomSheetDialogFragment = MyProjectMoreMenuBottomSheetFragment()

        mBinding?.moreImageButton?.setOnClickListener {
            activity?.supportFragmentManager?.let { it1 -> bottomSheetDialogFragment.show(it1, bottomSheetDialogFragment.tag) }
        }

        mBinding?.categoryTeamBackButton?.setOnClickListener {
            it.findNavController().navigate(R.id.action_myInfoTeamIdeaFragment_to_myInfoMyIdeaFragment)
        }
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        val teamIdeaAdapter = TeamPagerAdapter(requireActivity())
        //val teamMemberAdapter = TeamPagerAdapter(requireActivity())

        teamIdeaAdapter.addFragment(MyIdeaModifyFragment())
        teamIdeaAdapter.addFragment(MyIdeaMemberFragment())

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