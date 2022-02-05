package com.example.infraandroid.myinfo

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.infraandroid.databinding.FragmentMyInfoModifyBinding

class MyInfoModifyFragment : Fragment() {
    private var mBinding : FragmentMyInfoModifyBinding? = null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding = FragmentMyInfoModifyBinding.inflate(inflater, container, false)

        mBinding = binding

        return mBinding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val bottomSheetDialogFragment = MyInfoPhotoMoreMenuBottomSheetFragment()
        mBinding!!.myInfoModifyPhotoMoreIv.setOnClickListener {
            activity?.supportFragmentManager?.let { it1 -> bottomSheetDialogFragment.show(it1, bottomSheetDialogFragment.tag) }
        }
    }

    override fun onDestroyView() {
        mBinding = null
        super.onDestroyView()
    }
}