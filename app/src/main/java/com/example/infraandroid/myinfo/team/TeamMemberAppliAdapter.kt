package com.example.infraandroid.myinfo.team

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.infraandroid.databinding.ItemTeamMemberApplicationRecyclerviewBinding

class TeamMemberAppliAdapter(): RecyclerView.Adapter<RecyclerView.ViewHolder>() {
    val teamMemberAppliList = mutableListOf<TeamMemberApplicationInfo>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val binding = ItemTeamMemberApplicationRecyclerviewBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
        return TeamMemberAppliViewHolder(binding)
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        (holder as TeamMemberAppliViewHolder).onBind(teamMemberAppliList[position])
    }

    override fun getItemCount(): Int = teamMemberAppliList.size

    inner class TeamMemberAppliViewHolder(
        private val binding: ItemTeamMemberApplicationRecyclerviewBinding
    ): RecyclerView.ViewHolder(binding.root){
        fun onBind(teamMemberApplicationInfo: TeamMemberApplicationInfo){
            binding.teamMemberNameTv.text = teamMemberApplicationInfo.name
            //binding.teamMemberProfileIv.setImageResource(teamMemberApplicationInfo.profileImg)
        }
    }
}