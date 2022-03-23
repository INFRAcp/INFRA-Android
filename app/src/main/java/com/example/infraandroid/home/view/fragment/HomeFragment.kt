package com.example.infraandroid.home.view.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.navigation.findNavController
import com.example.infraandroid.util.InfraApplication
import com.example.infraandroid.R
import com.example.infraandroid.databinding.FragmentHomeBinding
import com.example.infraandroid.home.model.HotProject
import com.example.infraandroid.home.model.RecommedProject
import com.example.infraandroid.home.model.ResponseHotProjectData
import com.example.infraandroid.home.model.SelfDevelope
import com.example.infraandroid.home.view.adapter.HotProjectRVAdapter
import com.example.infraandroid.home.view.adapter.RecomProjectRVAdapter
import com.example.infraandroid.home.view.adapter.SelfDevelopeRVAdapter
import com.example.infraandroid.util.BaseFragment
import com.example.infraandroid.util.ServiceCreator
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class HomeFragment : BaseFragment<FragmentHomeBinding>(R.layout.fragment_home) {
    private val RecomprojectRVAdapter = RecomProjectRVAdapter()
    private val hotProjectRVAdapter = HotProjectRVAdapter()
    private val SelfDevelopeRVAdapter = SelfDevelopeRVAdapter()
    private val recomprojectList = mutableListOf<RecommedProject>()
    private val hotpjectList = mutableListOf<HotProject>()
    private val selfdevList = mutableListOf<SelfDevelope>()

    override fun FragmentHomeBinding.onCreateView(){
        binding.homeUserHiTv.text = getString(R.string.home_user_hi).format(InfraApplication.prefs.getString("userNickName", "null"))
    }

    override fun FragmentHomeBinding.onViewCreated() {

        //프로젝트 제안 리사이클러뷰 어뎁터
//        mBinding?.homeRecommedProjectRecycleview?.adapter = RecomprojectRVAdapter
//        val tempData = RecommedProject("공모전","멸종위기 동물","6/8명","마감임박!","동물","웹디자인", "https://img.insight.co.kr/static/2019/11/06/700/kzbv1474r107jj01027m.jpg")
//        val tempData5 = RecommedProject("스터디","안드로이드 스터디","3/4명","마감임박!","앱개발","안드로이드", "https://images.kbench.com/kbench/article/2018_03/k186172p1n1-s.jpg")
//        val tempData6 = RecommedProject("스터디","자바 정복하기","3/8명","모집중!","개발","초보스터디", "https://cdn.imweb.me/upload/S2019102419e77585ab9fd/512cfc2adfd37.jpg")
//        recomprojectList.add(tempData)
//        recomprojectList.add(tempData5)
//        recomprojectList.add(tempData6)
//        RecomprojectRVAdapter.recomprojectList.addAll(recomprojectList)
//        RecomprojectRVAdapter.notifyDataSetChanged()
//
//        //핫 프로젝트 리사이클러뷰 어뎁터
//        mBinding?.homeHotProjectRecycleview?.adapter = HotProjectRVAdapter
//        val tempData10 = HotProject("스터디","작심 3일","1/4명","모집중","영어","공부","https://image.shutterstock.com/image-photo/multiethnic-group-children-english-concept-260nw-197720093.jpg")
//        val tempData7 = HotProject("스터디","자바 정복하기","3/8명","모집중!","개발","초보스터디", "https://cdn.imweb.me/upload/S2019102419e77585ab9fd/512cfc2adfd37.jpg")
//        val tempData8 = HotProject("공모전","멸종위기 동물","6/8명","마감임박!","동물","웹디자인", "https://img.insight.co.kr/static/2019/11/06/700/kzbv1474r107jj01027m.jpg")
//        hotpjectList.add(tempData10)
//        hotpjectList.add(tempData7)
//        hotpjectList.add(tempData8)
//        HotProjectRVAdapter.hotprojectList.addAll(hotpjectList)
//        HotProjectRVAdapter.notifyDataSetChanged()
//
//        //자기개발 리사이클러뷰 어뎁터
//        mBinding?.homeSelfDevelopeRecycleview?.adapter = SelfDevelopeRVAdapter
//        val tempData3 = SelfDevelope("서포터즈","우리마을 서포터즈","3/6명","모집중!","봉사","멘토링","https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcRy0_llBUhq4PJVBRyybA49QN0tpnrmSx_rHQ&usqp=CAU")
//        val tempData2 = SelfDevelope("스터디","작심 3일","1/4명","모집중","영어","공부","https://image.shutterstock.com/image-photo/multiethnic-group-children-english-concept-260nw-197720093.jpg")
//        val tempData9 = SelfDevelope("스터디","안드로이드 스터디","3/4명","마감임박!","앱개발","안드로이드", "https://images.kbench.com/kbench/article/2018_03/k186172p1n1-s.jpg")
//
//        selfdevList.add(tempData3)
//        selfdevList.add(tempData2)
//        selfdevList.add(tempData9)
//        SelfDevelopeRVAdapter.selfdevelopeList.addAll(selfdevList)
//        SelfDevelopeRVAdapter.notifyDataSetChanged()

        binding.homeHotProjectRecycleview.adapter = hotProjectRVAdapter

        val call: Call<ResponseHotProjectData> = ServiceCreator.createProjectService
            .hotProject(InfraApplication.prefs.getString("jwt", "null"), InfraApplication.prefs.getString("refreshToken", "null").toInt(),
                InfraApplication.prefs.getString("userId", "null"))

        call.enqueue(object: Callback<ResponseHotProjectData> {
            override fun onResponse(
                call: Call<ResponseHotProjectData>,
                response: Response<ResponseHotProjectData>
            ) {
                if(response.isSuccessful){
                    val body = response.body()
                    if(body!=null){
                        when(body.code){
                            1000 -> {
                                val data = body.result
                                if(data!=null){
                                    hotProjectRVAdapter.hotprojectList = data
                                }
                                hotProjectRVAdapter.notifyDataSetChanged()
                            }
                        }
                    }
                }
            }

            override fun onFailure(call: Call<ResponseHotProjectData>, t: Throwable) {
            }

        })



        binding.homeBtnMakeNewProj.setOnClickListener {
            it.findNavController().navigate(R.id.action_home_fragment_to_createProjectFragment)
        }

        binding.homeKeywordSearchIv.setOnClickListener{
            if(binding.homeSearchEt.text.toString().length>=2){
                val action = HomeFragmentDirections.actionHomeFragmentToIdeaListFragment(binding.homeSearchEt.text.toString())
                it.findNavController().navigate(action)
            }
            else{
                Toast.makeText(requireActivity(), "검색어 2글자 이상 입력해주세요", Toast.LENGTH_SHORT).show()
            }
        }
    }
}