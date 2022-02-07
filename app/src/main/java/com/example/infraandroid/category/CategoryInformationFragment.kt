package com.example.infraandroid.category

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.findNavController
import com.example.infraandroid.R
import com.example.infraandroid.databinding.CategoryInformationBinding
import com.example.infraandroid.myinfo.MyInfoModifyMenuBottomSheetFragment


class CategoryInformationFragment: Fragment() {
    private  var mBinding : CategoryInformationBinding? = null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding = CategoryInformationBinding.inflate(inflater, container, false)
        mBinding = binding
        return mBinding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val bottomSheetDialogFragment = MyInfoModifyMenuBottomSheetFragment()
        mBinding!!.categoryInformationModifyTv.setOnClickListener{
//            val bottomSheet = MyInfoModifyMenuBottomSheetFragment()
//            bottomSheet.show(parentFragmentManager, bottomSheet.tag)
            activity?.supportFragmentManager?.let { it1 -> bottomSheetDialogFragment.show(it1, bottomSheetDialogFragment.tag) }
        }




    }

    override fun onDestroyView() {
        mBinding = null
        super.onDestroyView()
    }
}