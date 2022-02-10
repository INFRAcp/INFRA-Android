package com.example.infraandroid.home

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

// 프로젝트의 종류를 선택하는 spinner와 관련된 .kt파일
// 작성자 : 신승민
// 작성일 : 2022-02-09

class CreateProjectSelectCategory : Fragment(){
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
        val categoryDetailItems = resources.getStringArray(R.array.category_detail_array)
        val categoryAdapter = ArrayAdapter(requireActivity(), android.R.layout.simple_spinner_dropdown_item, categoryItems)
        val categoryDetailAdapter = ArrayAdapter(requireActivity(), android.R.layout.simple_spinner_dropdown_item, categoryDetailItems)
        val categorySpinner = mBinding?.categorySpinner
        val categoryDetailSpinner = mBinding?.categoryDetailSpinner

        mBinding?.goBackImageButton?.setOnClickListener {
            it.findNavController().navigate(R.id.action_createProjectSelectCategory_to_createProjectFragment)
        }

        categorySpinner?.adapter = categoryAdapter
        categoryDetailSpinner?.adapter = categoryDetailAdapter

    }

    override fun onDestroyView() {
        mBinding = null
        super.onDestroyView()
    }
}