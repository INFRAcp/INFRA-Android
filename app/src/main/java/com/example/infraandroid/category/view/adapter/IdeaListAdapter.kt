package com.example.infraandroid.category.view.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.infraandroid.util.ImageRound
import com.example.infraandroid.R
import com.example.infraandroid.category.model.IdeaListInfo
import com.example.infraandroid.category.model.Result
import com.example.infraandroid.databinding.ItemIdeaListRecyclerviewBinding

class IdeaListAdapter(): RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    val ideaList = mutableListOf<IdeaListInfo>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val binding = ItemIdeaListRecyclerviewBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false)
        return IdeaListViewHolder(binding)
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        (holder as IdeaListViewHolder).onBind(ideaList[position])
    }

    override fun getItemCount(): Int = ideaList.size

    inner class IdeaListViewHolder(
        private val binding:ItemIdeaListRecyclerviewBinding
    ): RecyclerView.ViewHolder(binding.root){
        fun onBind(ideaListInfo: IdeaListInfo){
            binding.projectCategoryTitle.text = ideaListInfo.projectName
            binding.projectCategoryTextView.text = ideaListInfo.projectCategory
            binding.hashTagOne.text = ideaListInfo.hashTagOne
            binding.hashTagTwo.text = ideaListInfo.hashTagTwo

            ImageRound.roundAll(binding.projectImageView, 36f)

            Glide.with(itemView)
                .load(ideaListInfo.projectImg)
                .into(binding.projectImageView)
            itemView.setOnClickListener {
                it.findNavController().navigate(R.id.action_idea_list_fragment_to_categoryViewIdeaFragment)
            }
        }
    }
}