package com.example.infraandroid.chat

import android.content.ContentValues.TAG
import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.infraandroid.R
import com.example.infraandroid.UserId
import com.example.infraandroid.databinding.ItemChatRecyclerviewBinding
import com.example.infraandroid.databinding.ItemMyChatRecyclerviewBinding

class ChatMultiViewAdapter() : RecyclerView.Adapter<RecyclerView.ViewHolder>(){

    val messageList = mutableListOf<MessageInfo>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        when(viewType){
            1 -> {
                val binding = ItemMyChatRecyclerviewBinding.inflate(
                    LayoutInflater.from(parent.context),
                    parent,
                    false)
                return MyChatViewHolder(binding)
            }
            else -> {
                val binding = ItemChatRecyclerviewBinding.inflate(
                    LayoutInflater.from(parent.context),
                    parent,
                    false)
                return ChatViewHolder(binding)
            }
        }
    }

    override fun getItemCount(): Int = messageList.size

    override fun getItemViewType(position: Int): Int {
        if(messageList[position].senderId == UserId.userId)
            return 1
        Log.d(TAG, "getItemViewType: "+messageList[position])
        return 2
    }
    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        if(messageList[position].senderId==UserId.userId){
            (holder as MyChatViewHolder).onBind(messageList[position])
            holder.setIsRecyclable(false)
        }
        else{
            (holder as ChatViewHolder).onBind(messageList[position])
            holder.setIsRecyclable(false)
        }
    }

    inner class ChatViewHolder(
        private val binding: ItemChatRecyclerviewBinding
    ) : RecyclerView.ViewHolder(binding.root){
        fun onBind(messageInfo: MessageInfo){
            binding.chatMessageTextview.text = messageInfo.message
            binding.chatSendTimeTextview.text = messageInfo.senderId
            Glide.with(itemView)
                .load(R.drawable.other_user_photo)
                .into(binding.opponentProfileImageview)
        }
    }

    inner class MyChatViewHolder(
        private val binding: ItemMyChatRecyclerviewBinding
    ) : RecyclerView.ViewHolder(binding.root){
        fun onBind(messageInfo: MessageInfo){
            binding.myChatMessageTextview.text = messageInfo.message
            binding.myChatSendTimeTextview.text = messageInfo.senderId
        }
    }
}
