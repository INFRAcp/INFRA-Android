package com.example.infraandroid.myinfo.myideamanage.view.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.findNavController
import com.example.infraandroid.R
import com.example.infraandroid.databinding.FragmentMyInfoMyIdeaBinding
import com.example.infraandroid.myinfo.myideamanage.model.MyIdeaListInfo
import com.example.infraandroid.myinfo.myideamanage.view.adapter.MyIdeaListAdapter

//내 정보 > 내 아이디어 관리 파일.kt
class MyIdeaManageFragment : Fragment() {
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
        myIdeaListAdapter.myideaList.clear()
        myIdeaListAdapter.myideaList.addAll(
            listOf(
                MyIdeaListInfo("https://img.insight.co.kr/static/2019/11/06/700/kzbv1474r107jj01027m.jpg","멸종위기 동물", "공모전","6/8", "동물", "웹디자인","마감임박!")
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