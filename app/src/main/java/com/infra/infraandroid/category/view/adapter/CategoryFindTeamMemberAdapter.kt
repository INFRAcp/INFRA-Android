package com.infra.infraandroid.category.view.adapter

import android.view.LayoutInflater
import android.view.ViewGroup


import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.infra.infraandroid.R
import com.infra.infraandroid.category.model.ResponseViewUserProfileData

//import com.example.infraandroid.UserId

import com.infra.infraandroid.databinding.ItemCategoryFindTeamRecyclerviewBinding
import com.infra.infraandroid.util.ImageRound

class CategoryFindTeamMemberAdapter():RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    var teamMemberList = mutableListOf<ResponseViewUserProfileData.Result>()
//    lateinit var navConstroller : NavController

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
        fun onBind(categoryFindTeamMemberInfo: ResponseViewUserProfileData.Result){

            ImageRound.roundAll(binding.findTeamItemImageView, 36f)
            binding.viewUserProfile  = categoryFindTeamMemberInfo

            /*binding.findTeamItemNameTextView.text = categoryFindTeamMemberInfo.name
            binding.findTeamItemMajorTextView.text = categoryFindTeamMemberInfo.major
            Glide.with(itemView)
                .load(categoryFindTeamMemberInfo.profileImg)
                .circleCrop()
                .into(binding.findTeamItemImageView)*/


//            itemView.setOnClickListener {
//                it.findNavController().navigate(R.id.action_category_fragment_to_category_information_fragment)
//            }
        }
    }
}
