package com.infra.infraandroid.category.view.fragment

import android.content.ContentValues.TAG
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.findNavController
import androidx.viewpager2.widget.ViewPager2
import com.infra.infraandroid.R
import com.infra.infraandroid.category.view.adapter.CategoryPagerAdapter
import com.infra.infraandroid.databinding.FragmentCategoryBinding
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator

// 카테고리의 부모 탭 레이아웃의 부모 레이아웃

class CategoryFragment : Fragment() {
    private  var mBinding : FragmentCategoryBinding? = null
    private lateinit var tabLayout: TabLayout
    private lateinit var viewPager: ViewPager2

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val binding = FragmentCategoryBinding.inflate(inflater, container, false)

        mBinding = binding

        tabLayout = mBinding!!.categoryTabLayout
        viewPager = mBinding!!.categoryViewPager

        return mBinding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {

        mBinding?.createProjectConstraintLayout?.setOnClickListener {
            it.findNavController().navigate(R.id.action_category_fragment_to_createProjectFragment)
        }

        super.onViewCreated(view, savedInstanceState)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        val categoryPagerAdapter = CategoryPagerAdapter(requireActivity())

        categoryPagerAdapter.addFragment(CategoryIdeaFragment())
        categoryPagerAdapter.addFragment(CategoryTeamFragment())

        mBinding?.categoryViewPager?.adapter = categoryPagerAdapter
        mBinding?.categoryViewPager?.registerOnPageChangeCallback(object: ViewPager2.OnPageChangeCallback(){
            override fun onPageSelected(position: Int) {
                super.onPageSelected(position)
                Log.d(TAG, "onPageSelected: " + (position+1))
            }
        })

        TabLayoutMediator(tabLayout, viewPager){tab, position ->
            when(position){
                0-> tab.text = "아이디어"
                1-> tab.text = "팀원"
            }
        }.attach()


    }


    override fun onDestroyView() {
        mBinding = null
        super.onDestroyView()
    }
}