package com.example.infraandroid.chat

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.infraandroid.InfraApplication
import com.example.infraandroid.R
import com.example.infraandroid.databinding.ItemChattingRoomListRecyclerviewBinding
import java.text.SimpleDateFormat
import java.util.*

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
            Glide.with(itemView)
                .load(R.drawable.other_user_photo)
                .into(binding.chattingRoomListImageview)
            itemView.setOnClickListener {
                InfraApplication.chatRoomIndex = chattingRoomInfo.chattingRoomIndex
                it.findNavController().navigate(R.id.action_chatting_room_list_fragment_to_chat_fragment)
            }

            val now: Long = System.currentTimeMillis()
            val date = Date(now)
            val dataFormat = SimpleDateFormat("yyyy-MM-dd HH:mm:ss")
            val dateTime = dataFormat.format(date)

            if(chattingRoomInfo.lastTime.substring(0..9)!=dateTime.substring(0..9)){
                val printTime = chattingRoomInfo.lastTime.substring(5..6) + "월 "+chattingRoomInfo.lastTime.substring(8..9)
                binding.lastChatTimeTextview.text = printTime
            }
            else{
                var hour = chattingRoomInfo.lastTime.substring(11..12)
                var min = chattingRoomInfo.lastTime.substring(14..15)
                if(hour.toInt() > 12){
                    binding.lastChatTimeTextview.text = (hour.toInt()-12).toString()+":"+min+" PM"
                }
                else{
                    binding.lastChatTimeTextview.text = hour[1]+":"+min+" AM"
                }
            }
        }
    }
}