package com.example.infraandroid.myinfo.myideamanage.view.fragment

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import androidx.constraintlayout.widget.ConstraintLayoutStates.TAG
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.findNavController
import androidx.navigation.fragment.findNavController
import com.example.infraandroid.R
import com.example.infraandroid.databinding.FragmentModifyCategoryBinding
import com.example.infraandroid.home.view.fragment.CreateProjectSelectCategoryFragmentDirections
import com.example.infraandroid.myinfo.myideamanage.model.MyProjectViewModel
import com.example.infraandroid.util.BaseFragment

class MyIdeaCategoryModifyFragment : Fragment(){
    private lateinit var viewModel : MyProjectViewModel
    private var mBinding : FragmentModifyCategoryBinding? = null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding = FragmentModifyCategoryBinding.inflate(inflater, container, false)
        mBinding = binding

        activity?.run{
            viewModel = ViewModelProvider(this, ViewModelProvider.NewInstanceFactory())
                .get(MyProjectViewModel::class.java)
        }

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
            viewModel.updateMyProjectCategory(categorySpinner?.selectedItem.toString())
            viewModel.updateMyProjectSubCategory(categoryDetailSpinner?.selectedItem.toString())
            Log.d(TAG, "onViewCreated: ${viewModel.currentMyProjectSubCategory.value}")
            it.findNavController().navigate(R.id.action_myIdeaCategoryModifyFragment_to_myInfoProjectModifyFragment)
        }
    }

    override fun onDestroyView() {
        mBinding = null
        super.onDestroyView()
    }
}