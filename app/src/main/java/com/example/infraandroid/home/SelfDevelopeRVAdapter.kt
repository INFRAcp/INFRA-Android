package com.example.infraandroid.home

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.infraandroid.databinding.ItemProjectBinding

class SelfDevelopeRVAdapter (): RecyclerView.Adapter<RecyclerView.ViewHolder>(){
    val selfdevelopeList = mutableListOf<SelfDevelope>()
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val binding: ItemProjectBinding = ItemProjectBinding.inflate(LayoutInflater.from(parent.context),parent,false)

        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        (holder as ViewHolder).onBind(selfdevelopeList[position])
    }

    override fun getItemCount(): Int = selfdevelopeList.size

    inner class ViewHolder(
        private val binding: ItemProjectBinding
    ): RecyclerView.ViewHolder(binding.root){
        fun onBind(selfDevelope: SelfDevelope){
            binding.itemProjectGroupTv.text = selfDevelope.group
            binding.itemProjectNameTv.text = selfDevelope.name
            binding.itemProjectMemberNumTv.text = selfDevelope.member
            binding.itemProjectStateTv.text = selfDevelope.state
            binding.itemKeyword1Chip.text = selfDevelope.keyword1
            binding.itemKeyword1Chip.text = selfDevelope.keyword2
        }
    }
}