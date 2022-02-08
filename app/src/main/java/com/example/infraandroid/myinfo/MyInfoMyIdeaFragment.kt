package com.example.infraandroid.myinfo

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.findNavController
import com.example.infraandroid.R
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

        mBinding?.myIdeaListBackButton?.setOnClickListener {
            it.findNavController().navigate(R.id.action_myInfoMyIdeaFragment_to_my_info_fragment)
        }
    }

    override fun onDestroyView() {
        mBinding = null
        super.onDestroyView()
    }
}