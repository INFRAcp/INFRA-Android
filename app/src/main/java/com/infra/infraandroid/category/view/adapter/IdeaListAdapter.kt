package com.infra.infraandroid.category.view.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.infra.infraandroid.util.ImageRound
import com.infra.infraandroid.category.model.ResponseLookUpAllProjectData
import com.infra.infraandroid.category.view.fragment.IdeaListFragmentDirections
import com.infra.infraandroid.databinding.ItemIdeaListRecyclerviewBinding

class IdeaListAdapter(): RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    var ideaList = mutableListOf<ResponseLookUpAllProjectData.Result>()

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
        fun onBind(ideaListInfo: ResponseLookUpAllProjectData.Result){
            ImageRound.roundAll(binding.projectImageView, 36f)
            binding.projectList  = ideaListInfo
            itemView.setOnClickListener {
                val action = IdeaListFragmentDirections.actionIdeaListFragmentToCategoryViewIdeaFragment(ideaListInfo.pj_num)
                it.findNavController().navigate(action)
            }
        }
    }
}