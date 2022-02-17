package com.example.infraandroid.myinfo.myideamanage.view.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.infraandroid.databinding.ItemTeamMemberApplicationRecyclerviewBinding
import com.example.infraandroid.myinfo.myideamanage.view.WarningAcceptDialog
import com.example.infraandroid.myinfo.myideamanage.view.WarningRejectDialog
import com.example.infraandroid.myinfo.myideamanage.model.MyIdeaMemberApplyManageInfo
import com.example.infraandroid.myinfo.myinfomodify.view.MyInfoPhotoMoreMenuBottomSheetFragment

//fragmentManager: FragmentManager
class MyIdeaMemberApplyAdapter(): RecyclerView.Adapter<RecyclerView.ViewHolder>() {
    val teamMemberAppliList = mutableListOf<MyIdeaMemberApplyManageInfo>()
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
        return TeamMemberAppliViewHolder(binding)
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        (holder as TeamMemberAppliViewHolder).onBind(teamMemberAppliList[position])
    }

    override fun getItemCount(): Int = teamMemberAppliList.size

    inner class TeamMemberAppliViewHolder(
        private val binding: ItemTeamMemberApplicationRecyclerviewBinding
    ): RecyclerView.ViewHolder(binding.root){
        fun onBind(myIdeaMemberApplyManageInfo: MyIdeaMemberApplyManageInfo){
            binding.teamMemberNameTv.text = myIdeaMemberApplyManageInfo.name
            //binding.teamMemberProfileIv.setImageResource(teamMemberApplicationInfo.profileImg)

            //팀원 프로필 클릭시 팀원 소개 페이지 바텀싵
            //bottom sheet layout 설정
            val bottomSheetDialogFragment = MyInfoPhotoMoreMenuBottomSheetFragment()
            binding.teamMemberProfileConstraintLayout.setOnClickListener {
                //bottomSheetDialogFragment.show(getFragmentManager() , "TAG")
                //bottomSheetDialogFragment.show(mFragmentManager, bottomSheetDialogFragment.tag)
            }

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