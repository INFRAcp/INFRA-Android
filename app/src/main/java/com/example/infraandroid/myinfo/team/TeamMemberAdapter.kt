package com.example.infraandroid.myinfo.team

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.infraandroid.databinding.ItemTeamMemberRecyclerviewBinding
import com.example.infraandroid.myinfo.WarningKickOutDialog

class TeamMemberAdapter(): RecyclerView.Adapter<RecyclerView.ViewHolder>() {
    val teamMemderlist = mutableListOf<TeamMemberInfo>()
    private lateinit var context: Context

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val binding = ItemTeamMemberRecyclerviewBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
        context = parent.context
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

            //강퇴하기 버튼 클릭시
            binding.teamMemberKickOutButton.setOnClickListener {
                val warningKickOutDialog = WarningKickOutDialog(context)
                warningKickOutDialog.show()
            }


        }
    }
}