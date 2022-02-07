package com.example.infraandroid.category

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.findNavController
import com.example.infraandroid.R
import com.example.infraandroid.databinding.FragmentIdeaListBinding

// Update
// 2022-02-07 뒤로가기버튼 누르면 이전 페이지로 이동 (작성자 : 신승민)

class IdeaListFragment : Fragment() {
    private  var mBinding : FragmentIdeaListBinding? = null
    private  val ideaListAdapter = IdeaListAdapter()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val binding = FragmentIdeaListBinding.inflate(inflater, container, false)

        mBinding = binding

        return mBinding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        mBinding?.ideaListRecyclerView?.adapter = ideaListAdapter
        ideaListAdapter.ideaList.addAll(
            listOf<IdeaListInfo>(
                IdeaListInfo("","멸종 위기 동물", "공모전","1/2", "동물", "웹디자인","마감임박!")
            )
        )
        ideaListAdapter.notifyDataSetChanged()

        mBinding?.ideaListBackButton?.setOnClickListener {
            it.findNavController().navigate(R.id.action_idea_list_fragment_to_category_fragment)
        }
    }

    override fun onDestroyView() {
        mBinding = null
        super.onDestroyView()
    }
}