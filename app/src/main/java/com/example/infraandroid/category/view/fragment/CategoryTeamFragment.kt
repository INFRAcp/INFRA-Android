package com.example.infraandroid.category.view.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.infraandroid.category.model.CategoryFindTeamMemberInfo
import com.example.infraandroid.category.view.adapter.CategoryFindTeamMemberAdapter
import com.example.infraandroid.databinding.FragmentCategoryTeamBinding

// 카테고리의 팀원 탭

class CategoryTeamFragment : Fragment() {
    private var mBinding : FragmentCategoryTeamBinding? = null
    private val categoryFindTeamMemberAdapter = CategoryFindTeamMemberAdapter()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val binding = FragmentCategoryTeamBinding.inflate(inflater, container, false)

        mBinding = binding

        return mBinding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


        mBinding?.categoryFindTeamRecyclerView?.adapter = categoryFindTeamMemberAdapter
        categoryFindTeamMemberAdapter.teamMemberList.addAll(
            listOf<CategoryFindTeamMemberInfo>(
                CategoryFindTeamMemberInfo("https://dszw1qtcnsa5e.cloudfront.net/community/20210125/60ac35ec-1511-4763-9cb9-a456c3b29814/%ED%8E%AD%EA%B7%84.png","김프라","소프트웨어학과","4","10"),
                CategoryFindTeamMemberInfo("https://image.newsis.com/2022/01/05/NISI20220105_0000907289_web.jpg","최웅","Spring개발","5","5"),
                CategoryFindTeamMemberInfo("https://littledeep.com/wp-content/uploads/2019/04/littledeep_sprout_style2.png","새싹이","인공지능학과","4","7"),
                CategoryFindTeamMemberInfo("https://interbalance.org/wp-content/uploads/2021/08/flouffy-VBkIK3qj3QE-unsplash-scaled-e1631077364762.jpg", "은진", "안드천재", "2", "9"),
                CategoryFindTeamMemberInfo("https://jjalbang.today/files/jjalboxthumb/2017/12/102_6579.jpg", "승민", "안드바보", "2", "9")
            )
        )

        categoryFindTeamMemberAdapter.notifyDataSetChanged()

    }

    override fun onDestroyView() {
        mBinding = null
        super.onDestroyView()
    }
}