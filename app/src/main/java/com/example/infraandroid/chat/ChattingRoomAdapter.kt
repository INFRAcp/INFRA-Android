package com.example.infraandroid.chat

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.infraandroid.R
import com.example.infraandroid.databinding.FragmentChattingRoomListBinding
import com.example.infraandroid.databinding.ItemChattingRoomListRecyclerviewBinding

class ChattingRoomAdapter(): RecyclerView.Adapter<RecyclerView.ViewHolder>() {
    val chattingList = mutableListOf<ChattingRoomInfo>()
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val binding = ItemChattingRoomListRecyclerviewBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false)
        return ChattingRoomViewHolder(binding)
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        (holder as ChattingRoomViewHolder).onBind(chattingList[position])
    }

    override fun getItemCount(): Int = chattingList.size

    inner class ChattingRoomViewHolder(
        private val binding: ItemChattingRoomListRecyclerviewBinding
    ) : RecyclerView.ViewHolder(binding.root){
        fun onBind(chattingRoomInfo: ChattingRoomInfo){
            binding.chattingRoomLastMessageTextview.text = chattingRoomInfo.lastMessage
            binding.chattingRoomListOpponentNameTextview.text = chattingRoomInfo.opponentName
            binding.lastChatTimeTextview.text = chattingRoomInfo.lastTime
            Glide.with(itemView)
                .load(R.drawable.other_user_photo)
                .into(binding.chattingRoomListImageview)
        }
    }
}