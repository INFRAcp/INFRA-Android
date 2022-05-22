package com.infra.infraandroid.chat.view

import android.annotation.SuppressLint
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.navigation.findNavController
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.infra.infraandroid.util.InfraApplication
import com.infra.infraandroid.R
import com.infra.infraandroid.chat.model.MessageInfo
import com.infra.infraandroid.databinding.FragmentChatBinding
import com.google.firebase.database.*
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
    private var chatRoomIndex : Int? = null
    private val mRef = database.getReference("chatting")
    private val args: ChatFragmentArgs by navArgs()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val binding = FragmentChatBinding.inflate(inflater, container, false)
        mBinding = binding

        return mBinding?.root
    }

    @SuppressLint("SimpleDateFormat")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val opponentNickName = args.writerId
        val opponentProfileImg = args.opponenetProfileImg

        val buttonToSend = mBinding?.messageSendButton as ImageButton
        mBinding?.chatOpponentNameTextview?.text = opponentNickName


        // 리사이클러뷰
        mBinding?.chatRecyclerview?.adapter = chatAdapter

        // 채팅 데이터의 변화를 감지 파이어베이스 -> 화면
        val childEventListener = object : ChildEventListener {
            var date = ""
            override fun onChildAdded(dataSnapshot: DataSnapshot, previousChildName: String?) {

                chatList.clear()
                val tempMessage = MessageInfo("","","", opponentProfileImg)
                for(snapshot in dataSnapshot.children){
                    Log.d(TAG, "onChildAdded: ${snapshot.value.toString()}")
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
                }
                if(tempMessage.senderId!="" && tempMessage.message!="" && tempMessage.sendTime!="")
                {
                    Log.d(TAG, "onChildAdded: $tempMessage")
                    chatList.add(tempMessage)
                }
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


        mRef.addValueEventListener(object:ValueEventListener{
            override fun onDataChange(snapshot: DataSnapshot) {
                // 이미 파이어베이스에 존재하는 채팅방인지 검사
                if(chatRoomIndex==null){
                    var i = 0
                    for(chatIndex in snapshot.children){
                        Log.d(TAG, "채팅방번호 ${chatIndex.key}")
                        i = chatIndex.key.toString().toInt()
                        if(chatIndex.child("users").child("user1").value.toString()==opponentNickName &&
                            chatIndex.child("users").child("user2").value.toString()==InfraApplication.prefs.getString("userNickName", "null")){
                            chatRoomIndex = i
                            break
                        }
                        if(chatIndex.child("users").child("user2").value.toString()==opponentNickName &&
                            chatIndex.child("users").child("user1").value.toString()==InfraApplication.prefs.getString("userNickName", "null")){
                            chatRoomIndex = i
                            break
                        }
                    }
                    // 처음 시작하는 채팅이면
                    if(chatRoomIndex==null){
                        chatRoomIndex = i+1
                    }
                    mRef.child(chatRoomIndex.toString()).addChildEventListener(childEventListener)
                    Log.d(TAG, "onDataChange: $chatRoomIndex")
                }
            }

            override fun onCancelled(error: DatabaseError) {
                TODO("Not yet implemented")
            }

        })

        // 보내기 버튼을 누르면 firebase realtime db에 저장
        buttonToSend.setOnClickListener {
            val messageToSend = mBinding?.inputMessageEdittext?.text
            val now: Long = System.currentTimeMillis()
            val date = Date(now)
            val dataFormat = SimpleDateFormat("yyyy-MM-dd HH:mm:ss")
            val dateTime = dataFormat.format(date)
            Log.d(TAG, "왜 여기는 되냐 "+mRef.child(chatRoomIndex.toString()))
            val myRef = mRef.child(chatRoomIndex.toString()).child(dateTime)

            // 대화하는 두 사람의 Id값을 firebase realtime db에 저장
            val userRef = mRef.child(chatRoomIndex.toString()).child("users")
            val lastMessageRef = mRef.child(chatRoomIndex.toString()).child("lastMessage")
            val userHashMap = HashMap<String, String>()
            userHashMap["user1"] = InfraApplication.prefs.getString("userNickName", "null")
            // 상대방의 아이디 가져와서 user2에 저장
            userHashMap["user2"] = opponentNickName.toString()
            userHashMap["user1ProfileImg"] = "user1의 프로필 이미지 url"
            userHashMap["user2ProfileImg"] = opponentProfileImg.toString()
            userRef.setValue(userHashMap)

            if (messageToSend != null) {
                if(messageToSend.isBlank())
                    Toast.makeText(activity, "input message!", Toast.LENGTH_SHORT).show()
                else {
                    // 채팅 관련 정보들 저장
                    val hashMap = HashMap<String, String>()
                    val lastMessageHashMap = HashMap<String, String>()

                    hashMap["senderId"] = InfraApplication.prefs.getString("userNickName", "null")
                    hashMap["message"] = messageToSend.toString()
                    hashMap["sendTime"] = dateTime
                    // 가장 최근 메시지 데이터 저장
                    lastMessageHashMap["senderId"]= InfraApplication.prefs.getString("userNickName", "null")
                    lastMessageHashMap["lastMessage"]=messageToSend.toString()
                    lastMessageHashMap["lastTime"]=dateTime

                    myRef.setValue(hashMap)
                    lastMessageRef.setValue(lastMessageHashMap)
                }

            }
            mBinding!!.inputMessageEdittext.setText("")
        }
//        val bottomSheetDialogFragment = ChatMoreMenuBottomSheetFragment()
//        mBinding!!.chatMoreMenuOpenButton.setOnClickListener {
//            activity?.supportFragmentManager?.let { it1 -> bottomSheetDialogFragment.show(it1, bottomSheetDialogFragment.tag) }
//        }

        mBinding?.backToChatListButton?.setOnClickListener {
            it.findNavController().navigate(R.id.action_chat_fragment_to_chatting_room_list_fragment)
        }

        mBinding?.reportChatImageButton?.setOnClickListener {
            val intent = Intent(Intent.ACTION_VIEW, Uri.parse("https://docs.google.com/forms/d/e/1FAIpQLSeiWgqtOehxOe-YCDpn5mFca9bWK3NcxfyVRC2O56xvz4xeLw/viewform?usp=sf_link"))
            startActivity(intent)
        }

        mBinding?.leaveChatImageButton?.setOnClickListener {
            val warningDialog = LeaveChatRoomDialog(chatRoomIdx = chatRoomIndex.toString())
            activity?.supportFragmentManager?.let{it1 -> warningDialog.show(it1, warningDialog.tag)}
        }
    }

    override fun onDestroyView() {
        mBinding = null
        super.onDestroyView()
    }
}