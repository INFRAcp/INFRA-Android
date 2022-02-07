package com.example.infraandroid.myinfo

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.infraandroid.databinding.MyInfoInformationBinding

class MyInfoInformationFragment: Fragment() {
    private var mBinding: MyInfoInformationBinding? = null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding = MyInfoInformationBinding.inflate(inflater,container,false)
        mBinding = binding
        return mBinding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val bottomSheetDialogFragment = MyInfoModifyMenuBottomSheetFragment()
        mBinding!!.myInfoInfoModifyTv.setOnClickListener{
            activity?.supportFragmentManager?.let { it1 -> bottomSheetDialogFragment.show(it1, bottomSheetDialogFragment.tag) }
        }
    }

    override fun onDestroyView() {
        mBinding = null
        super.onDestroyView()
    }
}