package com.infra.infraandroid.category.view.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.navigation.findNavController
import com.infra.infraandroid.R
import com.infra.infraandroid.databinding.FragmentCategoryIdeaBinding

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

        mBinding?.categorySearchImageView?.setOnClickListener {
            if(mBinding?.categoryIdeaSearchEditText?.text.toString().length>=2){
                val action = CategoryFragmentDirections.actionCategoryFragmentToIdeaListFragment(mBinding?.categoryIdeaSearchEditText?.text.toString())
                it.findNavController().navigate(action)
            }
            else{
                Toast.makeText(requireActivity(), "검색어 2글자 이상 입력해주세요", Toast.LENGTH_SHORT).show()
            }
        }
    }

    override fun onDestroyView() {
        mBinding = null
        super.onDestroyView()
    }
}