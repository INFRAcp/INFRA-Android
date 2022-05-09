package com.infra.infraandroid.myinfo.teammembereval.view.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.infra.infraandroid.databinding.ItemManagementTeamRecyclerviewBinding
import com.infra.infraandroid.myinfo.teammembereval.model.TeamEvalManagementList

class TeamEvalManagementAdapter(): RecyclerView.Adapter<RecyclerView.ViewHolder>() {
    val teamEvalManagementList = mutableListOf<TeamEvalManagementList>()
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val binding: ItemManagementTeamRecyclerviewBinding = ItemManagementTeamRecyclerviewBinding.inflate(
            LayoutInflater.from(parent.context),parent,false)

        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        (holder as ViewHolder).onBind(teamEvalManagementList[position])
    }

    override fun getItemCount(): Int = teamEvalManagementList.size

    inner class ViewHolder(
        private val binding: ItemManagementTeamRecyclerviewBinding
    ): RecyclerView.ViewHolder(binding.root){
        fun onBind(teamEvalManagementList: TeamEvalManagementList){
            binding.teamMemberNameTv.text = teamEvalManagementList.name
            binding.teamMemberScoreTv.text = teamEvalManagementList.score
            //binding.teamMemberProfileIv.setImageResource(teamEvalMemberList.profileImg)
        }
    }
}