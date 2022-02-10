package com.example.infraandroid.myinfo.team

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.infraandroid.databinding.ItemEvalTeamListRecyclerviewBinding
import com.example.infraandroid.databinding.ItemEvalTeamMemberListRecyclerviewBinding

//context: Context, val teamEvalMemberList: MutableList<TeamEvalMemberList>
class TeamEvalMemberListAdapter(): RecyclerView.Adapter<RecyclerView.ViewHolder>() {
   val teamEvalMemberList = mutableListOf<TeamEvalMemberList>()
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val binding: ItemEvalTeamMemberListRecyclerviewBinding = ItemEvalTeamMemberListRecyclerviewBinding.inflate(
            LayoutInflater.from(parent.context),parent,false)

        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        (holder as ViewHolder).onBind(teamEvalMemberList[position])
    }

    override fun getItemCount(): Int = teamEvalMemberList.size
    inner class ViewHolder(
        private val binding: ItemEvalTeamMemberListRecyclerviewBinding
    ): RecyclerView.ViewHolder(binding.root){
        fun onBind(teamEvalMemberList: TeamEvalMemberList){
            binding.teamMemberNameTv.text = teamEvalMemberList.name
            //binding.teamMemberProfileIv.setImageResource(teamEvalMemberList.profileImg)
        }
    }
}
