package com.example.infraandroid.RVA

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.infraandroid.DataClass.RecommedProject
import com.example.infraandroid.databinding.ActivityMainBinding
import com.example.infraandroid.databinding.ItemProjectBinding

class RecomProjectRVAdapter(private val recomprojectList: ArrayList<RecommedProject>):
    RecyclerView.Adapter<RecomProjectRVAdapter.ViewHolder>(){
    override fun onCreateViewHolder(
        viewGroup: ViewGroup,
        viewType: Int
    ): RecomProjectRVAdapter.ViewHolder {
        val binding: ItemProjectBinding = ItemProjectBinding.inflate(LayoutInflater.from(viewGroup.context),viewGroup, false)

        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: RecomProjectRVAdapter.ViewHolder, position: Int) {
        holder.bind(recomprojectList[position])
    }

    override fun getItemCount(): Int = recomprojectList.size

    //뷰 홀더 준비
    inner class ViewHolder(val binding: ItemProjectBinding): RecyclerView.ViewHolder(binding.root){
        fun bind(recomproject: RecommedProject){
            binding.itemProjectGroupTv.text = recomproject.group
            binding.itemProjectNameTv.text = recomproject.name
            binding.itemProjectMemberNumTv.text = recomproject.member
            binding.itemProjectStateTv.text = recomproject.state
            binding.itemRecommedProjectPhotoIv.setImageResource(recomproject.photo!!)

        }
    }
}