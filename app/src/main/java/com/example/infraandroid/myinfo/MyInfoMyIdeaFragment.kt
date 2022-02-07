package com.example.infraandroid.myinfo

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.infraandroid.databinding.FragmentMyInfoMyIdeaBinding

class MyInfoMyIdeaFragment : Fragment() {
    private var mBinding: FragmentMyInfoMyIdeaBinding? = null
    private val myIdeaListAdapter = MyIdeaListAdapter()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding = FragmentMyInfoMyIdeaBinding.inflate(inflater, container, false)
        mBinding = binding
        return mBinding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        mBinding?.myIdeaListRecyclerView?.adapter = myIdeaListAdapter
        myIdeaListAdapter.myideaList.addAll(
            listOf(
                MyIdeaListInfo("","식물나라", "동아리","10/20", "식물", "조경학과","모집중!")
            )
        )
        myIdeaListAdapter.notifyDataSetChanged()
    }

    override fun onDestroyView() {
        mBinding = null
        super.onDestroyView()
    }
}