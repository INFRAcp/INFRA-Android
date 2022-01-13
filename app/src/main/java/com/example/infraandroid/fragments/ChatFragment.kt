package com.example.infraandroid.fragments

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.infraandroid.adpater.ChatAdapter
import com.example.infraandroid.adpater.MessageInfo
import com.example.infraandroid.databinding.FragmentChatBinding
import com.example.infraandroid.databinding.FragmentHomeBinding
import com.google.firebase.database.ChildEventListener
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.ktx.database
import com.google.firebase.database.ktx.getValue
import com.google.firebase.ktx.Firebase
import org.w3c.dom.Comment
import java.text.SimpleDateFormat
import java.util.*
import kotlin.collections.HashMap

class ChatFragment : Fragment() {
    private val TAG = "ChatFragment"
    private  var mBinding : FragmentChatBinding? = null
    private val chatAdapter = ChatAdapter()
    private val database = Firebase.database
    val chatList = mutableListOf<MessageInfo>()

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

        val messageToSend = mBinding?.inputMessageEdittext?.text
        val buttonToSend = mBinding?.messageSendButton as Button
        val args: ChatFragmentArgs by navArgs()
        val userId = args.userIdArgs


        // 리사이클러뷰
        mBinding?.chatRecyclerview?.adapter = chatAdapter
//        chatAdapter.messageList.addAll(
//            listOf<MessageInfo>(
//            )
//        )
//        chatAdapter.notifyDataSetChanged()

        val childEventListener = object : ChildEventListener {
            override fun onChildAdded(dataSnapshot: DataSnapshot, previousChildName: String?) {
                Log.d(TAG, "onChildAdded:" + dataSnapshot.key!!)

                // A new comment has been added, add it to the displayed list
                chatList.clear()
                for(snapshot in dataSnapshot.children){
                    val tempMessage = MessageInfo(userId,"")
                    if(snapshot.key=="message")
                    {
                        tempMessage.message=snapshot.value.toString()
                        chatList.add(tempMessage)
                    }
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


        val databaseReference = database.getReference("message")
        databaseReference.addChildEventListener(childEventListener)

        // 보내기 버튼을 누르면 firebase realtime db에 저장
        buttonToSend.setOnClickListener {
            val c = Calendar.getInstance()
            val dataFormat = SimpleDateFormat("yyyy-MM-dd hh:mm:ss")
            val dateTime = dataFormat.format(c.time)
            val myRef = database.getReference("message").child(dateTime)

            if (messageToSend != null) {
                if(messageToSend.isBlank())
                    Toast.makeText(activity, "input message!", Toast.LENGTH_SHORT).show()
                else {
                    val hashMap = HashMap<String, String>()

                    hashMap["id"] = userId
                    hashMap["message"] = messageToSend.toString()

                    myRef.setValue(hashMap)
                }
            }
        }
    }

    override fun onDestroyView() {
        mBinding = null
        super.onDestroyView()
    }
}