package com.example.infraandroid.home.view.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.infraandroid.util.ImageRound
import com.example.infraandroid.R
import com.example.infraandroid.category.view.fragment.IdeaListFragmentDirections
import com.example.infraandroid.databinding.ItemProjectBinding
import com.example.infraandroid.home.model.HotProject
import com.example.infraandroid.home.model.ResponseHotProjectData
import com.example.infraandroid.home.view.fragment.HomeFragmentDirections

class HotProjectRVAdapter (): RecyclerView.Adapter<RecyclerView.ViewHolder>(){

    var hotprojectList = mutableListOf<ResponseHotProjectData.Result>()

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
        fun onBind(hotproject: ResponseHotProjectData.Result){
            ImageRound.roundTop(binding.itemRecommedProjectPhotoIv, 36f)
            binding.projectItem = hotproject

            itemView.setOnClickListener {
                val action = HomeFragmentDirections.actionHomeFragmentToCategoryViewIdeaFragment(hotproject.pj_num)
                it.findNavController().navigate(action)
            }
        }
    }

}