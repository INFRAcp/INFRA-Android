package com.example.infraandroid.category

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.navigation.NavController
import androidx.navigation.Navigation
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.example.infraandroid.R
import com.example.infraandroid.UserId
import com.example.infraandroid.databinding.ItemCategoryFindTeamRecyclerviewBinding

class CategoryFindTeamMemberAdapter():RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    val teamMemberList = mutableListOf<CategoryFindTeamMemberInfo>()
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
        fun onBind(categoryFindTeamMemberInfo: CategoryFindTeamMemberInfo){
            binding.findTeamItemNameTextView.text = categoryFindTeamMemberInfo.name
            binding.findTeamItemMajorTextView.text = categoryFindTeamMemberInfo.major


//            navConstroller = Navigation.findNavController(itemView)
//            itemView.setOnClickListener {
//                navConstroller.navigate(R.id.action_categoryTeamFragment_to_categoryInformationFragment)
//            }

//            작성자 : 이은진
//            작성일 : 2022.02.01
            itemView.setOnClickListener {
                it.findNavController().navigate(R.id.action_category_team_fragment_to_category_information_fragment)
            }

        }
    }
}