package com.example.infraandroid.category.view.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.findNavController
import com.example.infraandroid.R
import com.example.infraandroid.category.model.IdeaListInfo
import com.example.infraandroid.category.view.adapter.IdeaListAdapter
import com.example.infraandroid.databinding.FragmentIdeaListBinding

// 아이디어의 목록을 볼 수 있는 뷰
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
                IdeaListInfo("https://img.insight.co.kr/static/2019/11/06/700/kzbv1474r107jj01027m.jpg","멸종 위기 동물", "공모전","1/2", "동물", "웹디자인","마감임박!"),
                IdeaListInfo("https://image.shutterstock.com/image-photo/multiethnic-group-children-english-concept-260nw-197720093.jpg","작심 3일","스터디","1/4","영어","공부","모집중!"),
                IdeaListInfo("https://cdn.imweb.me/upload/S2019102419e77585ab9fd/512cfc2adfd37.jpg","자바 정복하기","스터디","3/8","개발","초보스터디", "모집중!"),
                IdeaListInfo("https://images.kbench.com/kbench/article/2018_03/k186172p1n1-s.jpg","안드로이드 스터디","스터디","3/5","앱개발","안드로이드", "마감임박!")
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