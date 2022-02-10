package com.example.infraandroid.myinfo.team

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.infraandroid.databinding.ItemEvalTeamListRecyclerviewBinding
import com.example.infraandroid.databinding.ItemEvalTeamMemberListRecyclerviewBinding
import kotlin.contracts.contract

class TeamEvalTeamListAdapter(): RecyclerView.Adapter<RecyclerView.ViewHolder>() {
    val teamEvalTeamList = mutableListOf<TeamEvalTeamList>()
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val binding: ItemEvalTeamListRecyclerviewBinding = ItemEvalTeamListRecyclerviewBinding.inflate(
            LayoutInflater.from(parent.context),parent,false)

        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        (holder as ViewHolder).onBind(teamEvalTeamList[position])
    }

    override fun getItemCount(): Int = teamEvalTeamList.size

    inner class ViewHolder(
        private val binding: ItemEvalTeamListRecyclerviewBinding
    ): RecyclerView.ViewHolder(binding.root){
        fun onBind(teamEvalTeamList: TeamEvalTeamList){
            binding.teamEvalTitleTv.text = teamEvalTeamList.title
            binding.teamEvalDateTv.text = teamEvalTeamList.date
            binding.teamEvalProjectCategoryTv.text = teamEvalTeamList.category
            binding.teamEvalMemberListRecyclerview.adapter = TeamEvalMemberListAdapter()
            //binding.teamEvalMemberListRecyclerview.layoutManager = LinearLayoutManager

//            binding.teamEvalMemberListRecyclerview.adapter = TeamEvalMemberListAdapter(context,teamEvalTeamList.innerlist)
//            binding.teamEvalMemberListRecyclerview.layoutManager = LinearLayoutManager(context)

        }
    }
}
