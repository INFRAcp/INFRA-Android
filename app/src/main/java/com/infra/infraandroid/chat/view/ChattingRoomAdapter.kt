package com.infra.infraandroid.chat.view

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.infra.infraandroid.R
import com.infra.infraandroid.chat.model.ChattingRoomInfo
import com.infra.infraandroid.databinding.ItemChattingRoomListRecyclerviewBinding
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
                .load(chattingRoomInfo.opponentProfileImg)
                .circleCrop()
                .error(R.drawable.other_user_photo)
                .into(binding.chattingRoomListImageview)
            itemView.setOnClickListener {
                val action = ChattingRoomListFragmentDirections.actionChattingRoomListFragmentToChatFragment(chattingRoomInfo.opponentName, chattingRoomInfo.opponentProfileImg)
                it.findNavController().navigate(action)
            }

            val now: Long = System.currentTimeMillis()
            val date = Date(now)
            val dataFormat = SimpleDateFormat("yyyy-MM-dd HH:mm:ss")
            val dateTime = dataFormat.format(date)

            if(chattingRoomInfo.lastTime.substring(0..9)!=dateTime.substring(0..9)){
                val printTime = chattingRoomInfo.lastTime.substring(5..6) + "월 "+chattingRoomInfo.lastTime.substring(8..9) + "일"
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