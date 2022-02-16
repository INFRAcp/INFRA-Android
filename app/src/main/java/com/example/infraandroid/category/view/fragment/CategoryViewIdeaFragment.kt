package com.example.infraandroid.category.view.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.infraandroid.databinding.FragmentViewIdeaBinding

// 다른 사람의 아이디어를 눌렀을 때 볼 수 있는 자세히 보기 뷰

class CategoryViewIdeaFragment : Fragment(){
    private var mBinding : FragmentViewIdeaBinding? = null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding = FragmentViewIdeaBinding.inflate(inflater, container, false)
        mBinding = binding
        return mBinding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
    }

    override fun onDestroyView() {
        mBinding = null
        super.onDestroyView()
    }
}