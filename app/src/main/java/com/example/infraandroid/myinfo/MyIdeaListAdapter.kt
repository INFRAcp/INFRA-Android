package com.example.infraandroid.myinfo

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.infraandroid.ImageRound
import com.example.infraandroid.R
import com.example.infraandroid.databinding.ItemMyIdeaListRecyclerviewBinding

class MyIdeaListAdapter(): RecyclerView.Adapter<RecyclerView.ViewHolder>() {
    val myideaList = mutableListOf<MyIdeaListInfo>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val binding = ItemMyIdeaListRecyclerviewBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false)
        return MyIdeaListViewHolder(binding)
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        (holder as MyIdeaListViewHolder).onBind(myideaList[position])
    }

    override fun getItemCount(): Int = myideaList.size

    inner class MyIdeaListViewHolder(
        private val binding: ItemMyIdeaListRecyclerviewBinding
    ): RecyclerView.ViewHolder(binding.root){
        fun onBind(myIdeaListInfo: MyIdeaListInfo){
            binding.projectCategoryTitle.text = myIdeaListInfo.projectName
            binding.projectCategoryTextView.text = myIdeaListInfo.projectCategory
            binding.hashTagOne.text = myIdeaListInfo.hashTagOne
            binding.hashTagTwo.text = myIdeaListInfo.hashTagTwo

            ImageRound.roundAll(binding.projectImageView, 36f)

            Glide.with(itemView)
                .load(myIdeaListInfo.projectImg)
                .into(binding.projectImageView)

            //내 아이디어 관리 클릭 이벤트
            itemView.setOnClickListener {
                it.findNavController().navigate(R.id.action_myInfoMyIdeaFragment_to_myInfoTeamIdeaFragment)
            }
        }
    }
}