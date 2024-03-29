package com.infra.infraandroid.chat.view

import android.content.ContentValues.TAG
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.infra.infraandroid.util.InfraApplication
import com.infra.infraandroid.R
import com.infra.infraandroid.chat.model.MessageInfo
import com.infra.infraandroid.databinding.ItemChatRecyclerviewBinding
import com.infra.infraandroid.databinding.ItemMyChatRecyclerviewBinding

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
        if(messageList[position].senderId == InfraApplication.prefs.getString("userNickName", "null"))
            return 1
        Log.d(TAG, "getItemViewType: "+messageList[position])
        return 2
    }
    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        if(messageList[position].senderId== InfraApplication.prefs.getString("userNickName", "null")){
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

            val date = messageInfo.sendTime.substring(0..3)+"년 "+messageInfo.sendTime.substring(5..6)+"월 "+messageInfo.sendTime.substring(8..9)+"일"
            binding.opponentDateLineTextview.text = date
            if(!messageInfo.dateLine){
                binding.opponentDateLineConstraintLayout.visibility = View.GONE
            }

            Glide.with(itemView)
                .load(messageInfo.profileImg)
                .circleCrop()
                .error(R.drawable.other_user_photo)
                .into(binding.opponentProfileImageview)

            var hour = messageInfo.sendTime.substring(11..12)
            var min = messageInfo.sendTime.substring(14..15)
            if(hour.toInt() > 12){
                binding.chatSendTimeTextview.text = (hour.toInt()-12).toString()+":"+min+" PM"
            }
            else{
                binding.chatSendTimeTextview.text = hour[1]+":"+min+" AM"
            }
        }
    }

    inner class MyChatViewHolder(
        private val binding: ItemMyChatRecyclerviewBinding
    ) : RecyclerView.ViewHolder(binding.root){
        fun onBind(messageInfo: MessageInfo){
            val date = messageInfo.sendTime.substring(0..3)+"년 "+messageInfo.sendTime.substring(5..6)+"월 "+messageInfo.sendTime.substring(8..9)+"일"
            binding.dateLineTextview.text = date
            if(!messageInfo.dateLine){
                binding.dateLineConstraintLayout.visibility = View.GONE
            }

            binding.myChatMessageTextview.text = messageInfo.message
            var hour = messageInfo.sendTime.substring(11..12)
            var min = messageInfo.sendTime.substring(14..15)
            if(hour.toInt() > 12){
                binding.myChatSendTimeTextview.text = (hour.toInt()-12).toString()+":"+min+" PM"
            }
            else{
                binding.myChatSendTimeTextview.text = hour[1]+":"+min+" AM"
            }
        }
    }
}
