package com.infra.infraandroid.chat.view

import android.content.res.Resources
import android.graphics.Rect
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.DialogFragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase
import com.infra.infraandroid.R
import com.infra.infraandroid.databinding.LeaveChatRoomDialogBinding
import com.infra.infraandroid.databinding.MyInfoMyProjectDeleteWarningBinding
import com.infra.infraandroid.myinfo.myideamanage.model.MyProjectViewModel


class LeaveChatRoomDialog(val chatRoomIdx : String) : DialogFragment() {
    private lateinit var viewModel : MyProjectViewModel
    private var mBinding : LeaveChatRoomDialogBinding? = null
    private val database = Firebase.database
    private val mRef = database.getReference("chatting")

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        activity?.run{
            viewModel = ViewModelProvider(this, ViewModelProvider.NewInstanceFactory())
                .get(MyProjectViewModel::class.java)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding = LeaveChatRoomDialogBinding.inflate(inflater, container, false)
        mBinding = binding

        return mBinding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        mBinding?.warningOkayButton?.setOnClickListener {
            mRef.child(chatRoomIdx).removeValue()
            dismiss()
            findNavController().navigate(R.id.action_chat_fragment_to_chatting_room_list_fragment)
        }

        mBinding?.warningCancelButton?.setOnClickListener {
            dismiss()
        }
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        setWidthPercent(90)
    }

    fun DialogFragment.setWidthPercent(percentage: Int) {
        val percent = percentage.toFloat() / 100
        val dm = Resources.getSystem().displayMetrics
        val rect = dm.run { Rect(0, 0, widthPixels, heightPixels) }
        val percentWidth = rect.width() * percent
        dialog?.window?.setLayout(percentWidth.toInt(), ViewGroup.LayoutParams.WRAP_CONTENT)
        dialog?.window?.setBackgroundDrawableResource(R.drawable.warning_background)
    }
}