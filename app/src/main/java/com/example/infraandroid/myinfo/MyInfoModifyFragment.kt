package com.example.infraandroid.myinfo

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.findNavController
import com.example.infraandroid.R
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

        // 뒤로가기 버튼 눌렀을때
        mBinding?.myInfoModifyBackButton?.setOnClickListener {
            it.findNavController().navigate(R.id.action_my_info_modify_fragment_to_my_info_fragment)
        }
    }

    override fun onDestroyView() {
        mBinding = null
        super.onDestroyView()
    }
}