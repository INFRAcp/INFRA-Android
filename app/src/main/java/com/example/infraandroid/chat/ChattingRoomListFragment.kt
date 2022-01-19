package com.example.infraandroid.chat

import android.annotation.SuppressLint
import android.content.ContentValues.TAG
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.navigation.findNavController
import com.example.infraandroid.R
import com.example.infraandroid.UserId
import com.example.infraandroid.databinding.FragmentChattingRoomListBinding
import com.google.firebase.database.ChildEventListener
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.ValueEventListener
import com.google.firebase.database.ktx.database
import com.google.firebase.database.ktx.getValue
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
                val tempChattingRoom = ChattingRoomInfo("상대방 프로필 이미지","","","")
                for(shot in snapshot.children){
                    Log.d(TAG, "onDataChange: " + shot.key)
                    if(shot.key == "lastMessage"){
                        tempChattingRoom.lastMessage = shot.child("lastMessage").value.toString()
                        tempChattingRoom.lastTime = shot.child("lastTime").value.toString()
                    }
                    if(shot.key == "users"){
                        if(shot.child("user1").value==UserId.userId){
                            tempChattingRoom.opponentName = shot.child("user2").value.toString()
                        }
                        if(shot.child("user2").value==UserId.userId){
                            tempChattingRoom.opponentName = shot.child("user1").value.toString()
                        }
                    }
                }
                Log.d(TAG, "chattinglist: "+tempChattingRoom.lastMessage+" // " + tempChattingRoom.lastTime+ " // "+tempChattingRoom.opponentName)
                if(tempChattingRoom.lastMessage!="" && tempChattingRoom.lastTime!="" && tempChattingRoom.opponentName!="")
                    chattingRoomList.add(tempChattingRoom)
                chattingRoomAdapter.chattingList.addAll(chattingRoomList)
                chattingRoomAdapter.notifyDataSetChanged()
            }

            override fun onCancelled(error: DatabaseError) {
                TODO("Not yet implemented")
            }

        }

        val databaseReference = database.getReference("1")
        databaseReference.addValueEventListener(valueEventListener)
    }

    override fun onDestroyView() {
        mBinding = null
        super.onDestroyView()
    }
}