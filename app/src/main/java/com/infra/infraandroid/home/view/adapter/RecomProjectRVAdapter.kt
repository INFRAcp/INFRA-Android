package com.infra.infraandroid.home.view.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.infra.infraandroid.util.ImageRound
import com.infra.infraandroid.R
import com.infra.infraandroid.databinding.ItemProjectBinding
import com.infra.infraandroid.home.model.RecommedProject

class RecomProjectRVAdapter(): RecyclerView.Adapter<RecyclerView.ViewHolder>(){

    val recomprojectList= mutableListOf<RecommedProject>()

    override fun onCreateViewHolder(
        viewGroup: ViewGroup,
        viewType: Int
    ): ViewHolder {
        val binding: ItemProjectBinding = ItemProjectBinding.inflate(LayoutInflater.from(viewGroup.context),viewGroup, false)

        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        (holder as ViewHolder).onBind(recomprojectList[position])
    }

    override fun getItemCount(): Int = recomprojectList.size

    //뷰 홀더 준비
    inner class ViewHolder(
        private val binding: ItemProjectBinding
    ): RecyclerView.ViewHolder(binding.root){
        fun onBind(recomproject: RecommedProject){
            binding.itemProjectGroupTv.text = recomproject.group
            binding.itemProjectNameTv.text = recomproject.name
            binding.itemProjectMemberNumTv.text = recomproject.member
            binding.itemProjectStateTv.text = recomproject.state
            binding.itemHashTagOne.text = recomproject.keyword1
            binding.itemHashTagTwo.text = recomproject.keyword2

            // 이미지 뷰 둥글게
            ImageRound.roundTop(binding.itemRecommedProjectPhotoIv, 36f)

            Glide.with(itemView)
                .load(recomproject.photo)
                .into(binding.itemRecommedProjectPhotoIv)
            itemView.setOnClickListener {
                it.findNavController().navigate(R.id.action_home_fragment_to_categoryViewIdeaFragment)
            }
            binding.itemRecommedProjectPhotoIv.clipToOutline = true
        }
        
    }
}