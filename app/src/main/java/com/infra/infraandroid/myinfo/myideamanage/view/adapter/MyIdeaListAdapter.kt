package com.infra.infraandroid.myinfo.myideamanage.view.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.infra.infraandroid.databinding.ItemMyIdeaListRecyclerviewBinding
import com.infra.infraandroid.myinfo.myideamanage.model.ResponseMyProjectListData
import com.infra.infraandroid.myinfo.myideamanage.view.fragment.MyIdeaManageFragmentDirections

class MyIdeaListAdapter(): RecyclerView.Adapter<RecyclerView.ViewHolder>() {
    var myIdeaList = mutableListOf<ResponseMyProjectListData.Result>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val binding = ItemMyIdeaListRecyclerviewBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false)
        return MyIdeaListViewHolder(binding)
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        (holder as MyIdeaListViewHolder).onBind(myIdeaList[position])
    }

    override fun getItemCount(): Int = myIdeaList.size

    inner class MyIdeaListViewHolder(
        private val binding: ItemMyIdeaListRecyclerviewBinding
    ): RecyclerView.ViewHolder(binding.root){
        fun onBind(myIdeaListInfo: ResponseMyProjectListData.Result){
            binding.myProject = myIdeaListInfo
            //내 아이디어 관리 클릭 이벤트
            itemView.setOnClickListener {
                val action = MyIdeaManageFragmentDirections.actionMyInfoMyIdeaFragmentToMyInfoTeamIdeaFragment(myIdeaTitle = myIdeaListInfo.pj_header, myProjectNum = myIdeaListInfo.pj_num)
                it.findNavController().navigate(action)
            }
        }
    }
}