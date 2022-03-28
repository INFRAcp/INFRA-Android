package com.example.infraandroid.myinfo.myideamanage.view.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.infraandroid.databinding.ItemTeamMemberApplicationRecyclerviewBinding
import com.example.infraandroid.myinfo.myideamanage.view.WarningAcceptDialog
import com.example.infraandroid.myinfo.myideamanage.view.WarningRejectDialog
import com.example.infraandroid.myinfo.myideamanage.model.MyIdeaMemberApplyManageInfo
import com.example.infraandroid.myinfo.myideamanage.model.ResponseViewProjectApplyData
import com.example.infraandroid.myinfo.myinfomodify.view.MyInfoPhotoMoreMenuBottomSheetFragment

//fragmentManager: FragmentManager
class MyIdeaMemberApplyAdapter(): RecyclerView.Adapter<RecyclerView.ViewHolder>() {
    var teamMemberApplyList = mutableListOf<ResponseViewProjectApplyData.Result>()
    private lateinit var context: Context
    //private var mFragmentManager: FragmentManager

//    init {
//        mFragmentManager = fragmentManager
//    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val binding = ItemTeamMemberApplicationRecyclerviewBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
        context = parent.context
        return TeamMemberApplyViewHolder(binding)
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        (holder as TeamMemberApplyViewHolder).onBind(teamMemberApplyList[position])
    }

    override fun getItemCount(): Int = teamMemberApplyList.size

    inner class TeamMemberApplyViewHolder(
        private val binding: ItemTeamMemberApplicationRecyclerviewBinding
    ): RecyclerView.ViewHolder(binding.root){
        fun onBind(myIdeaMemberApplyManageInfo: ResponseViewProjectApplyData.Result){

//            //bottom sheet layout 설정
//            val bottomSheetDialogFragment = MyInfoPhotoMoreMenuBottomSheetFragment()
//            binding.teamMemberProfileConstraintLayout.setOnClickListener {
//                //bottomSheetDialogFragment.show(getFragmentManager() , "TAG")
//                //bottomSheetDialogFragment.show(mFragmentManager, bottomSheetDialogFragment.tag)
//            }

            binding.applyPerson = myIdeaMemberApplyManageInfo

            //수락하기 버튼 클릭시
            binding.teamMemberApplicationAcceptBoxIv.setOnClickListener {
                val warningAcceptDialog = WarningAcceptDialog(context)
                warningAcceptDialog.show()
            }
            //거절하기 버튼 클릭시
            binding.teamMemberApplicationRejectBoxIv.setOnClickListener {
                val warningRejectDialog = WarningRejectDialog(context)
                warningRejectDialog.show()
            }
        }
    }
}