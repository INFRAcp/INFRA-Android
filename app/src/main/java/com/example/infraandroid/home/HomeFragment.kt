package com.example.infraandroid.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.findNavController
import com.example.infraandroid.InfraApplication
import com.example.infraandroid.R
import com.example.infraandroid.databinding.FragmentHomeBinding
import com.example.infraandroid.id.data.SharedIdViewModel

class HomeFragment : Fragment() {
    private var mBinding : FragmentHomeBinding? = null
    private val RecomprojectRVAdapter = RecomProjectRVAdapter()
    private val HotProjectRVAdapter = HotProjectRVAdapter()
    private val SelfDevelopeRVAdapter = SelfDevelopeRVAdapter()
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
        mBinding?.homeUserHiTv?.text = getString(R.string.home_user_hi).format(InfraApplication.prefs.getString("userNickName", "null"))

        return mBinding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        //프로젝트 제안 리사이클러뷰 어뎁터
        mBinding?.homeRecommedProjectRecycleview?.adapter = RecomprojectRVAdapter
        val tempData = RecommedProject("공모전","멸종위기 동물","6/8명","마감임박!","동물","웹디자인", 3)
        recomprojectList.add(tempData)
        RecomprojectRVAdapter.recomprojectList.addAll(recomprojectList)
        RecomprojectRVAdapter.notifyDataSetChanged()

        //핫 프로젝트 리사이클러뷰 어뎁터
        mBinding?.homeHotProjectRecycleview?.adapter = HotProjectRVAdapter
        val tempData2 = HotProject("공모전","멸종위기 동물","6/8명","마감임박!","동물","웹디자인", 3)
        hotpjectList.add(tempData2)
        HotProjectRVAdapter.hotprojectList.addAll(hotpjectList)
        HotProjectRVAdapter.notifyDataSetChanged()

        //자기개발 리사이클러뷰 어뎁터
        mBinding?.homeSelfDevelopeRecycleview?.adapter = SelfDevelopeRVAdapter
        val tempData3 = SelfDevelope("공모전","멸종위기 동물","6/8명","마감임박!","동물","웹디자인", 3)
        selfdevList.add(tempData3)
        SelfDevelopeRVAdapter.selfdevelopeList.addAll(selfdevList)
        SelfDevelopeRVAdapter.notifyDataSetChanged()

        mBinding?.homeBtnMakeNewProj?.setOnClickListener {
            it.findNavController().navigate(R.id.action_home_fragment_to_createProjectFragment)
        }
    }
    override fun onDestroyView() {
        mBinding = null
        super.onDestroyView()
    }
}