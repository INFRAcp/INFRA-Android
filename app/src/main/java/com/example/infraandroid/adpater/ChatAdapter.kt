package com.example.infraandroid.adpater

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.infraandroid.R
import com.example.infraandroid.databinding.ItemChatRecyclerviewBinding

class ChatAdapter : RecyclerView.Adapter<ChatAdapter.ChatViewHolder>(){

    val messageList = mutableListOf<MessageInfo>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ChatViewHolder {
        val binding = ItemChatRecyclerviewBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
        return ChatViewHolder(binding)
    }

    override fun getItemCount(): Int = messageList.size

    override fun onBindViewHolder(holder: ChatViewHolder, position: Int) {
        holder.onBind(messageList[position])
    }

    class ChatViewHolder(
        private val binding: ItemChatRecyclerviewBinding
    ) : RecyclerView.ViewHolder(binding.root){
        fun onBind(messageInfo: MessageInfo){
            binding.chatMessageTextview.text = messageInfo.message
            binding.chatSenderIdTextview.text = messageInfo.senderId
        }
    }
}
