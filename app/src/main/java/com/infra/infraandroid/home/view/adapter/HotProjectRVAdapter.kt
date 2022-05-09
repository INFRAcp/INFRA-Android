package com.infra.infraandroid.home.view.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.infra.infraandroid.util.ImageRound
import com.infra.infraandroid.databinding.ItemProjectBinding
import com.infra.infraandroid.home.model.ResponseHotProjectData
import com.infra.infraandroid.home.view.fragment.HomeFragmentDirections

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