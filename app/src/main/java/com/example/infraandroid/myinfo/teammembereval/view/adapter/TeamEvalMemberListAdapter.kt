package com.example.infraandroid.myinfo.teammembereval.view.adapter

import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.example.infraandroid.R
import com.example.infraandroid.databinding.ItemEvalTeamMemberListRecyclerviewBinding
import com.example.infraandroid.id.viewmodel.SignUpViewModel.Companion.TAG
import com.example.infraandroid.myinfo.teammembereval.model.TeamEvalMemberList

//context: Context, val teamEvalMemberList: MutableList<TeamEvalMemberList>
class TeamEvalMemberListAdapter(private val itemList : MutableList<TeamEvalMemberList>): RecyclerView.Adapter<RecyclerView.ViewHolder>() {
   private val teamEvalMemberList = itemList
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val binding: ItemEvalTeamMemberListRecyclerviewBinding = ItemEvalTeamMemberListRecyclerviewBinding.inflate(
            LayoutInflater.from(parent.context),parent,false)

        binding.teamEvalWriteButton.setOnClickListener {
            it.findNavController().navigate(R.id.action_myInfoTeamMemberEvaluationFragment_to_teamMemberEvalEditFragment)
        }

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
        }
    }
}
