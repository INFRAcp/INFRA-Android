package com.example.infraandroid.home.view.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ArrayAdapter
import androidx.fragment.app.Fragment
import androidx.navigation.findNavController
import com.example.infraandroid.R
import com.example.infraandroid.databinding.FragmentCreateProjectSelectCategoryBinding
import com.example.infraandroid.myinfo.myideamanage.view.fragment.MyIdeaManageFragmentDirections

// 프로젝트의 종류를 선택하는 spinner와 관련된 .kt파일
// 작성자 : 신승민
// 작성일 : 2022-02-09

class CreateProjectSelectCategoryFragment : Fragment(){
    private var mBinding : FragmentCreateProjectSelectCategoryBinding? = null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding = FragmentCreateProjectSelectCategoryBinding.inflate(inflater, container, false)
        mBinding = binding
        return mBinding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val categoryItems = resources.getStringArray(R.array.category_array)
        val categoryDetailDevelopItems = resources.getStringArray(R.array.category_detail_develop_array)
//        val categoryDetailStudyItems = resources.getStringArray(R.array.category_detail_study_array)
//        val categoryDetailActivityItems = resources.getStringArray(R.array.category_detail_activity_array)
        val categoryAdapter = ArrayAdapter(requireActivity(), android.R.layout.simple_spinner_dropdown_item, categoryItems)
        var categoryDetailAdapter = ArrayAdapter(requireActivity(), android.R.layout.simple_spinner_dropdown_item, categoryDetailDevelopItems)
        val categorySpinner = mBinding?.categorySpinner
        var categoryDetailSpinner = mBinding?.categoryDetailSpinner


        categorySpinner?.adapter = categoryAdapter
        categoryDetailSpinner?.adapter = categoryDetailAdapter

        mBinding?.goBackImageButton?.setOnClickListener {
            val action = CreateProjectSelectCategoryFragmentDirections.actionCreateProjectSelectCategoryToCreateProjectFragment(categorySpinner?.selectedItem.toString(), categoryDetailSpinner?.selectedItem.toString())
            it.findNavController().navigate(action)
        }
    }

    override fun onDestroyView() {
        mBinding = null
        super.onDestroyView()
    }
}