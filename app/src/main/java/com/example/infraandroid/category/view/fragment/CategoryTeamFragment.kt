package com.example.infraandroid.category.view.fragment

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.infraandroid.R
import com.example.infraandroid.category.model.CategoryFindTeamMemberInfo
import com.example.infraandroid.category.model.ResponseViewUserProfileData
import com.example.infraandroid.category.view.adapter.CategoryFindTeamMemberAdapter
import com.example.infraandroid.databinding.FragmentCategoryTeamBinding
import com.example.infraandroid.databinding.FragmentMyInfoModifyBinding
import com.example.infraandroid.id.viewmodel.SignUpViewModel
import com.example.infraandroid.util.BaseFragment
import com.example.infraandroid.util.ServiceCreator
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

// 카테고리의 팀원 탭

class CategoryTeamFragment : BaseFragment<FragmentCategoryTeamBinding>(R.layout.fragment_category_team) {
    private val categoryFindTeamMemberAdapter = CategoryFindTeamMemberAdapter()

    override fun FragmentCategoryTeamBinding.onCreateView() {
    }

    override fun FragmentCategoryTeamBinding.onViewCreated() {

        binding.categoryFindTeamRecyclerView.adapter = categoryFindTeamMemberAdapter
        /*categoryFindTeamMemberAdapter.teamMemberList.addAll(
            listOf<CategoryFindTeamMemberInfo>(
                CategoryFindTeamMemberInfo("https://dszw1qtcnsa5e.cloudfront.net/community/20210125/60ac35ec-1511-4763-9cb9-a456c3b29814/%ED%8E%AD%EA%B7%84.png","김프라","소프트웨어학과","4","10"),
                CategoryFindTeamMemberInfo("https://image.newsis.com/2022/01/05/NISI20220105_0000907289_web.jpg","최웅","Spring개발","5","5"),
                CategoryFindTeamMemberInfo("https://littledeep.com/wp-content/uploads/2019/04/littledeep_sprout_style2.png","새싹이","인공지능학과","4","7"),
                CategoryFindTeamMemberInfo("https://interbalance.org/wp-content/uploads/2021/08/flouffy-VBkIK3qj3QE-unsplash-scaled-e1631077364762.jpg", "은진", "안드천재", "2", "9"),
                CategoryFindTeamMemberInfo("https://jjalbang.today/files/jjalboxthumb/2017/12/102_6579.jpg", "승민", "안드바보", "2", "9")
            )
        )*/

        //팀원 조회 api 연결
        val call: Call<ResponseViewUserProfileData> = ServiceCreator.projectService
            .viewUserProfile()
        call.enqueue(object : Callback<ResponseViewUserProfileData> {
            override fun onResponse(
                call: Call<ResponseViewUserProfileData>,
                response: Response<ResponseViewUserProfileData>
            ) {
                if(response.isSuccessful){
                    val body = response.body()
                    if (body != null) {
                        when(body.code){
                            1000 -> {
                                val data = body.result
                                if (data != null) {
                                    categoryFindTeamMemberAdapter.teamMemberList = data
                                }
                                categoryFindTeamMemberAdapter.notifyDataSetChanged()
                            }
                            3000 -> {
                                Log.d(SignUpViewModel.TAG, "값을 불러오는데 실패하였습니다.")
                            }
                            4000 -> {
                                Log.d(SignUpViewModel.TAG, "데이터베이스 연결에 실패하였습니다.")
                            }
                        }
                    }
                }
            }

            override fun onFailure(call: Call<ResponseViewUserProfileData>, t: Throwable) {
                Log.d(SignUpViewModel.TAG, "onFailure: $t")
            }

        })

    }

}