package com.infra.infraandroid.home.view.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.infra.infraandroid.util.ImageRound
import com.infra.infraandroid.R
import com.infra.infraandroid.databinding.ItemProjectBinding
import com.infra.infraandroid.home.model.SelfDevelope

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
            binding.itemHashTagOne.text = selfDevelope.keyword1
            binding.itemHashTagTwo.text = selfDevelope.keyword2

            // 이미지 뷰 둥글게
            ImageRound.roundTop(binding.itemRecommedProjectPhotoIv, 36f)

            Glide.with(itemView)
                .load(selfDevelope.photo)
                .into(binding.itemRecommedProjectPhotoIv)
            itemView.setOnClickListener {
                it.findNavController().navigate(R.id.action_home_fragment_to_categoryViewIdeaFragment)
            }
        }
    }
}