package com.example.infraandroid.myinfo.team

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.fragment.app.FragmentManager
import androidx.recyclerview.widget.RecyclerView
import com.example.infraandroid.databinding.ItemTeamMemberApplicationRecyclerviewBinding
import com.example.infraandroid.myinfo.MyInfoPhotoMoreMenuBottomSheetFragment
import com.example.infraandroid.myinfo.WarningAcceptDialog
import com.example.infraandroid.myinfo.WarningRejectDialog
//fragmentManager: FragmentManager
class TeamMemberAppliAdapter(): RecyclerView.Adapter<RecyclerView.ViewHolder>() {
    val teamMemberAppliList = mutableListOf<TeamMemberApplicationInfo>()
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
        fun onBind(teamMemberApplicationInfo: TeamMemberApplicationInfo){
            binding.teamMemberNameTv.text = teamMemberApplicationInfo.name
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