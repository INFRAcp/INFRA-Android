package com.example.infraandroid.category.view.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.findNavController
import com.example.infraandroid.R
import com.example.infraandroid.databinding.FragmentCategoryIdeaBinding

// 카테고리의 아이디어 탭

class CategoryIdeaFragment : Fragment() {
    private  var mBinding : FragmentCategoryIdeaBinding? = null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val binding = FragmentCategoryIdeaBinding.inflate(inflater, container, false)

        mBinding = binding

        return mBinding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        mBinding?.ideaListLookAllButton?.setOnClickListener {
            it.findNavController().navigate(R.id.action_category_fragment_to_idea_list_fragment)
        }

        mBinding?.lookMyIdeaButton?.setOnClickListener {
            it.findNavController().navigate(R.id.action_category_fragment_to_myInfoMyIdeaFragment)
        }
    }

    override fun onDestroyView() {
        mBinding = null
        super.onDestroyView()
    }
}