package com.example.infraandroid.category

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.infraandroid.databinding.ItemTeamMemberRecyclerviewBinding

class TeamMemberAdapter(): RecyclerView.Adapter<RecyclerView.ViewHolder>() {
    val teamMemderlist = mutableListOf<TeamMemberInfo>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val binding = ItemTeamMemberRecyclerviewBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
        return TeamMemberViewHolder(binding)
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        (holder as TeamMemberViewHolder).onBind(teamMemderlist[position])
    }

    override fun getItemCount(): Int = teamMemderlist.size

    inner class TeamMemberViewHolder(
        private val binding: ItemTeamMemberRecyclerviewBinding
    ) : RecyclerView.ViewHolder(binding.root) {
        fun onBind(teamMemberInfo: TeamMemberInfo) {
            binding.teamMemberNameTv.text = teamMemberInfo.name
            //binding.teamMemberProfileIv.setImageResource(teamMemberInfo.profileImg)
        }
    }
}