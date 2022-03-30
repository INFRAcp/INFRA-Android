package com.example.infraandroid.myinfo.teammembereval.view.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.infraandroid.databinding.ItemTeamMemberRecyclerviewBinding
import com.example.infraandroid.myinfo.myideamanage.view.WarningKickOutDialog
import com.example.infraandroid.myinfo.myideamanage.model.MyIdeaMemberManageInfo
import com.example.infraandroid.myinfo.myideamanage.model.ResponseTeamMemberData

class TeamMemberAdapter(): RecyclerView.Adapter<RecyclerView.ViewHolder>() {
    var teamMemberList = mutableListOf<ResponseTeamMemberData.Result>()
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
        (holder as TeamMemberViewHolder).onBind(teamMemberList[position])

    }

    override fun getItemCount(): Int = teamMemberList.size

    inner class TeamMemberViewHolder(
        private val binding: ItemTeamMemberRecyclerviewBinding
    ) : RecyclerView.ViewHolder(binding.root) {
        fun onBind(myIdeaMemberManageInfo: ResponseTeamMemberData.Result) {
            binding.projectMember = myIdeaMemberManageInfo
            //binding.teamMemberProfileIv.setImageResource(teamMemberInfo.profileImg)

            //강퇴하기 버튼 클릭시
//            binding.teamMemberKickOutButton.setOnClickListener {
////                val warningKickOutDialog = WarningKickOutDialog(context)
////                warningKickOutDialog.show()
//            }


        }
    }
}