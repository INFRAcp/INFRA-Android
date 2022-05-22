package com.infra.infraandroid.myinfo.teammembereval.view.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.infra.infraandroid.R
import com.infra.infraandroid.databinding.ItemTeamMemberRecyclerviewBinding
import com.infra.infraandroid.myinfo.myideamanage.model.ResponseTeamMemberData
import com.infra.infraandroid.myinfo.myideamanage.model.ResponseViewProjectApplyData

class TeamMemberAdapter(): RecyclerView.Adapter<RecyclerView.ViewHolder>() {
    var teamMemberList = mutableListOf<ResponseViewProjectApplyData.Result>()
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
        fun onBind(myIdeaMemberManageInfo: ResponseViewProjectApplyData.Result) {
            Glide.with(itemView)
                .load(myIdeaMemberManageInfo.user_prphoto)
                .error(R.drawable.user_photo)
                .circleCrop()
                .into(binding.teamMemberProfileIv)
            binding.teamMemberNameTv.text = myIdeaMemberManageInfo.user_nickname

            //강퇴하기 버튼 클릭시
//            binding.teamMemberKickOutButton.setOnClickListener {
////                val warningKickOutDialog = WarningKickOutDialog(context)
////                warningKickOutDialog.show()
//            }


        }
    }
}