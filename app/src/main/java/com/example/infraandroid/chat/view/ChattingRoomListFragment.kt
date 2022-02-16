package com.example.infraandroid.chat.view

import android.annotation.SuppressLint
import android.content.ContentValues.TAG
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.infraandroid.util.InfraApplication
import com.example.infraandroid.chat.model.ChattingRoomInfo
import com.example.infraandroid.databinding.FragmentChattingRoomListBinding
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.ValueEventListener
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase

class ChattingRoomListFragment : Fragment() {
    private var mBinding : FragmentChattingRoomListBinding? = null
    private val chattingRoomAdapter = ChattingRoomAdapter()
    private val database = Firebase.database
    val chattingRoomList = mutableListOf<ChattingRoomInfo>()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val binding = FragmentChattingRoomListBinding.inflate(inflater, container,false)

        mBinding = binding

        return mBinding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        mBinding?.chattingRoomListRecyclerview?.adapter = chattingRoomAdapter

        val valueEventListener = object: ValueEventListener{
            @SuppressLint("NotifyDataSetChanged")
            override fun onDataChange(snapshot: DataSnapshot) {
                chattingRoomAdapter.chattingList.clear()
                chattingRoomList.clear()
                for(shot in snapshot.children){
                    val tempChattingRoom = ChattingRoomInfo("상대방 프로필 이미지","","","", 0)
                    Log.d(TAG, "onDataChange: " + shot.key)
                   if(shot.child("users").child("user1").value.toString()== InfraApplication.prefs.getString("userNickName", "null")){
                       tempChattingRoom.lastMessage = shot.child("lastMessage").child("lastMessage").value.toString()
                       tempChattingRoom.lastTime = shot.child("lastMessage").child("lastTime").value.toString()
                       tempChattingRoom.opponentName = shot.child("users").child("user2").value.toString()
                       tempChattingRoom.chattingRoomIndex = shot.key?.toInt() ?: 0
                       tempChattingRoom.opponentProfileImg = shot.child("users").child("user2ProfileImg").value.toString()
                   }
                    if(shot.child("users").child("user2").value.toString()== InfraApplication.prefs.getString("userNickName", "null")){
                        tempChattingRoom.lastMessage = shot.child("lastMessage").child("lastMessage").value.toString()
                        tempChattingRoom.lastTime = shot.child("lastMessage").child("lastTime").value.toString()
                        tempChattingRoom.opponentName = shot.child("users").child("user1").value.toString()
                        tempChattingRoom.chattingRoomIndex = shot.key?.toInt() ?: 0
                        tempChattingRoom.opponentProfileImg = shot.child("users").child("user1ProfileImg").value.toString()
                    }
                    Log.d(TAG, "chattinglist: "+tempChattingRoom.lastMessage+" // " + tempChattingRoom.lastTime+ " // "+tempChattingRoom.opponentName)
                    if(tempChattingRoom.lastMessage!="" && tempChattingRoom.lastTime!="" && tempChattingRoom.opponentName!="")
                        chattingRoomList.add(tempChattingRoom)
                }
                chattingRoomAdapter.chattingList.addAll(chattingRoomList)
                chattingRoomAdapter.notifyDataSetChanged()
            }

            override fun onCancelled(error: DatabaseError) {
                TODO("Not yet implemented")
            }

        }

        val databaseReference = database.getReference("chatting")
        databaseReference.addValueEventListener(valueEventListener)
    }

    override fun onDestroyView() {
        mBinding = null
        super.onDestroyView()
    }
}