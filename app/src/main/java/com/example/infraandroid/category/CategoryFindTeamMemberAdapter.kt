package com.example.infraandroid.category

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.infraandroid.databinding.ItemCategoryFindTeamRecyclerviewBinding

class CategoryFindTeamMemberAdapter():RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    val teamMemberList = mutableListOf<CategoryFindTeamMemberInfo>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val binding = ItemCategoryFindTeamRecyclerviewBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false)
        return CategoryFindTeamMemberViewHolder(binding)
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        (holder as CategoryFindTeamMemberViewHolder).onBind(teamMemberList[position])
    }

    override fun getItemCount(): Int = teamMemberList.size

    inner class CategoryFindTeamMemberViewHolder(
        private val binding:ItemCategoryFindTeamRecyclerviewBinding
    ) : RecyclerView.ViewHolder(binding.root){
        fun onBind(categoryFindTeamMemberInfo: CategoryFindTeamMemberInfo){
            binding.findTeamItemNameTextView.text = categoryFindTeamMemberInfo.name
            binding.findTeamItemMajorTextView.text = categoryFindTeamMemberInfo.major
        }
    }
}