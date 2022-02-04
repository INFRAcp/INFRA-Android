package com.example.infraandroid.category

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.findNavController
import com.example.infraandroid.R
import com.example.infraandroid.databinding.FragmentCategoryTeamBinding

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
                CategoryFindTeamMemberInfo("","김프라","소프트웨어학과","4","10"),
                CategoryFindTeamMemberInfo("", "은진", "안드천재", "2", "9"),
                CategoryFindTeamMemberInfo("", "승민", "안드바보", "2", "9")
            )
        )

        categoryFindTeamMemberAdapter.notifyDataSetChanged()

    }

    override fun onDestroyView() {
        mBinding = null
        super.onDestroyView()
    }
}