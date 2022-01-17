package com.example.infraandroid.chat

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.infraandroid.databinding.FragmentChattingRoomListBinding

class ChattingRoomListFragment : Fragment() {
    private var mBinding : FragmentChattingRoomListBinding? = null
    private val chattingRoomAdapter = ChattingRoomAdapter()

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

        chattingRoomAdapter.chattingList.addAll(
            listOf<ChattingRoomInfo>(
                ChattingRoomInfo(
                    "test",
                    "test",
                    "test",
                    "test"
                ),
                ChattingRoomInfo(
                    "test",
                    "test",
                    "test",
                    "test"
                ),
                ChattingRoomInfo(
                    "test",
                    "test",
                    "test",
                    "test"
                )
            )
        )

        chattingRoomAdapter.notifyDataSetChanged()
    }

    override fun onDestroyView() {
        mBinding = null
        super.onDestroyView()
    }
}