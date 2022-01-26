package com.example.infraandroid.home

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.infraandroid.databinding.ItemProjectBinding

class HotProjectRVAdapter (): RecyclerView.Adapter<RecyclerView.ViewHolder>(){
    val hotprojectList = mutableListOf<HotProject>()
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val binding: ItemProjectBinding = ItemProjectBinding.inflate(LayoutInflater.from(parent.context),parent,false)

        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        (holder as ViewHolder).onBind(hotprojectList[position])
    }

    override fun getItemCount(): Int = hotprojectList.size

    inner class ViewHolder(
        private val binding: ItemProjectBinding
    ): RecyclerView.ViewHolder(binding.root){
        fun onBind(hotproject: HotProject){
            binding.itemProjectGroupTv.text = hotproject.group
            binding.itemProjectNameTv.text = hotproject.name
            binding.itemProjectMemberNumTv.text = hotproject.member
            binding.itemProjectStateTv.text = hotproject.state
            binding.itemHashTagOne.text = hotproject.keyword1
            binding.itemHashTagTwo.text = hotproject.keyword2
        }
    }

}