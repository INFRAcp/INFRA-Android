package com.example.infraandroid.category

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.findNavController
import com.bumptech.glide.Glide
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

        // 시연영상찍고 지울거거
        mBinding?.let {
            Glide.with(this)
                .load("https://image.newsis.com/2022/01/05/NISI20220105_0000907289_web.jpg")
                .circleCrop()
                .into(it.categoryInfoUserProfileIv)
            Glide.with(this)
                .load("https://img6.yna.co.kr/photo/yna/YH/2020/02/10/PYH2020021020830001300_P4.jpg")
                .into(it.categoryInfoUserPhoto2Imageview)
        }
    }

    override fun onDestroyView() {
        mBinding = null
        super.onDestroyView()
    }
}