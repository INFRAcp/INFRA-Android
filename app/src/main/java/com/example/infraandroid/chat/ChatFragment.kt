package com.example.infraandroid.chat

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.example.infraandroid.InfraApplication
import com.example.infraandroid.databinding.FragmentChatBinding
import com.google.firebase.database.ChildEventListener
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.ktx.database
import com.google.firebase.database.ktx.getValue
import com.google.firebase.ktx.Firebase
import java.text.SimpleDateFormat
import java.util.*
import kotlin.collections.HashMap

class ChatFragment : Fragment() {
    private val TAG = "ChatFragment"
    private  var mBinding : FragmentChatBinding? = null
    private val chatAdapter = ChatMultiViewAdapter()
    private val database = Firebase.database
    val chatList = mutableListOf<MessageInfo>()
    private var opponentId = ""

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val binding = FragmentChatBinding.inflate(inflater, container, false)
        mBinding = binding

        return mBinding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val buttonToSend = mBinding?.messageSendButton as ImageButton


        // 리사이클러뷰
        mBinding?.chatRecyclerview?.adapter = chatAdapter


        // 채팅 데이터의 변화를 감지
        val childEventListener = object : ChildEventListener {
            var date = ""
            override fun onChildAdded(dataSnapshot: DataSnapshot, previousChildName: String?) {

                // A new comment has been added, add it to the displayed list
                chatList.clear()
                val tempMessage = MessageInfo("","","")
                for(snapshot in dataSnapshot.children){
                    Log.d(TAG, "onChildAdded: "+ snapshot.key.toString())
                    if(snapshot.key=="senderId"){
                        tempMessage.senderId=snapshot.value.toString()
                    }
                    if(snapshot.key=="message"){
                        tempMessage.message=snapshot.value.toString()
                    }
                    if(snapshot.key=="sendTime"){
                        tempMessage.sendTime=snapshot.value.toString()
                        val thisMessageTime = snapshot.value.toString().substring(0..9)
                        if(date==thisMessageTime){
                            tempMessage.dateLine = false
                        }
                        else{
                            date = thisMessageTime
                        }
                    }
                    if(snapshot.key=="user1") {
                        opponentId = snapshot.value.toString()
                    }
                }
                if(tempMessage.senderId!="" && tempMessage.message!="" && tempMessage.sendTime!="")
                    chatList.add(tempMessage)
                chatAdapter.messageList.addAll(chatList)
                chatAdapter.notifyDataSetChanged()
                // ...
            }

            override fun onChildChanged(dataSnapshot: DataSnapshot, previousChildName: String?) {
                Log.d(TAG, "onChildChanged: ${dataSnapshot.key}")

                // A comment has changed, use the key to determine if we are displaying this
                // comment and if so displayed the changed comment.
                val newComment = dataSnapshot.getValue<MessageInfo>()
                val commentKey = dataSnapshot.key


                // ...
            }

            override fun onChildRemoved(dataSnapshot: DataSnapshot) {
                Log.d(TAG, "onChildRemoved:" + dataSnapshot.key!!)

                // A comment has changed, use the key to determine if we are displaying this
                // comment and if so remove it.
                val commentKey = dataSnapshot.key

                // ...
            }

            override fun onChildMoved(dataSnapshot: DataSnapshot, previousChildName: String?) {
                Log.d(TAG, "onChildMoved:" + dataSnapshot.key!!)

                // A comment has changed position, use the key to determine if we are
                // displaying this comment and if so move it.
                val movedComment = dataSnapshot.getValue<MessageInfo>()
                val commentKey = dataSnapshot.key

                // ...
            }

            override fun onCancelled(databaseError: DatabaseError) {
                Log.w(TAG, "postComments:onCancelled", databaseError.toException())
                Toast.makeText(context, "Failed to load comments.",
                    Toast.LENGTH_SHORT).show()
            }
        }

        val chatRoomIndexString = InfraApplication.chatRoomIndex.toString()
        val databaseReference = database.getReference("chatting").child(chatRoomIndexString)
        databaseReference.addChildEventListener(childEventListener)

        // 보내기 버튼을 누르면 firebase realtime db에 저장
        buttonToSend.setOnClickListener {
            val messageToSend = mBinding?.inputMessageEdittext?.text
            val now: Long = System.currentTimeMillis()
            val date = Date(now)
            val dataFormat = SimpleDateFormat("yyyy-MM-dd HH:mm:ss")
            val dateTime = dataFormat.format(date)
            val myRef = database.getReference("chatting").child(chatRoomIndexString).child(dateTime)

            // 대화하는 두 사람의 Id값을 firebase realtime db에 저장
            val userRef = database.getReference("chatting").child(chatRoomIndexString).child("users")
            val lastMessageRef = database.getReference("chatting").child(chatRoomIndexString).child("lastMessage")
            val userHashMap = HashMap<String, String>()
            userHashMap["user1"] = InfraApplication.userId
            // 상대방의 아이디 가져와서 user2에 저장
            userHashMap["user2"] = opponentId
            userHashMap["user1ProfileImg"] = "user1의 프로필 이미지 url"
            userHashMap["user2ProfileImg"] = "user2의 프로필 이미지 url"
            userRef.setValue(userHashMap)

            if (messageToSend != null) {
                if(messageToSend.isBlank())
                    Toast.makeText(activity, "input message!", Toast.LENGTH_SHORT).show()
                else {
                    // 채팅 관련 정보들 저장
                    val hashMap = HashMap<String, String>()
                    val lastMessageHashMap = HashMap<String, String>()

                    hashMap["senderId"] = InfraApplication.userId
                    hashMap["message"] = messageToSend.toString()
                    hashMap["sendTime"] = dateTime
                    // 가장 최근 메시지 데이터 저장
                    lastMessageHashMap["senderId"]=InfraApplication.userId
                    lastMessageHashMap["lastMessage"]=messageToSend.toString()
                    lastMessageHashMap["lastTime"]=dateTime

                    myRef.setValue(hashMap)
                    lastMessageRef.setValue(lastMessageHashMap)
                }

            }
            mBinding!!.inputMessageEdittext.setText("")
        }
        val bottomSheetDialogFragment = ChatMoreMenuBottomSheetFragment()
        mBinding!!.chatMoreMenuOpenButton.setOnClickListener {
            activity?.supportFragmentManager?.let { it1 -> bottomSheetDialogFragment.show(it1, bottomSheetDialogFragment.tag) }
        }
    }

    override fun onDestroyView() {
        mBinding = null
        super.onDestroyView()
    }
}