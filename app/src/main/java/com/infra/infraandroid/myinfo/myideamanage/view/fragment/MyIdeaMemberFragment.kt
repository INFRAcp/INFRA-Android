package com.infra.infraandroid.myinfo.myideamanage.view.fragment

import android.os.Bundle
import androidx.lifecycle.ViewModelProvider
import com.infra.infraandroid.R
import com.infra.infraandroid.databinding.FragmentTeamMemberBinding
import com.infra.infraandroid.myinfo.myideamanage.model.*
import com.infra.infraandroid.myinfo.myideamanage.view.adapter.MyIdeaMemberApplyAdapter
import com.infra.infraandroid.myinfo.teammembereval.view.adapter.TeamMemberAdapter
import com.infra.infraandroid.util.BaseFragment
import com.infra.infraandroid.util.InfraApplication
import com.infra.infraandroid.util.ServiceCreator
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

// 내 정보 > 내 아이디어 탭 레이아웃의 팀원 탭
class MyIdeaMemberFragment : BaseFragment<FragmentTeamMemberBinding>(R.layout.fragment_team_member) {

    private lateinit var viewModel : MyProjectViewModel

    private val teamMemberAdapter = TeamMemberAdapter()
    private val teamMemberList = mutableListOf<ResponseViewProjectApplyData.Result>()
    private val applyList = mutableListOf<ResponseViewProjectApplyData.Result>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        activity?.run{
            viewModel = ViewModelProvider(this, ViewModelProvider.NewInstanceFactory())
                .get(MyProjectViewModel::class.java)
        }
    }

    override fun FragmentTeamMemberBinding.onCreateView(){

    }

    override fun FragmentTeamMemberBinding.onViewCreated() {
        val teamMemberApplyAdapter = MyIdeaMemberApplyAdapter(activity)
//        //팀원 관리 어뎁터 연결
//        mBinding?.teamMemberManagementRecyclerview?.adapter = TeamMemberAdapter
//        val tempDataMember = MyIdeaMemberManageInfo(3,"김프라")
//        teamMemberInfo.add(tempDataMember)
//        TeamMemberAdapter.teamMemderlist.addAll(teamMemberInfo)
//        TeamMemberAdapter.notifyDataSetChanged()

        //신청 관리 어뎁터 연결
        binding.applicationManagementRecyclerview.adapter = teamMemberApplyAdapter
        binding.teamMemberManagementRecyclerview.adapter = teamMemberAdapter
        val call : Call<ResponseViewProjectApplyData> = ServiceCreator.myProjectService
            .viewProjectApply(InfraApplication.prefs.getString("jwt", "null"), InfraApplication.prefs.getString("refreshToken", "null").toInt(),
            InfraApplication.prefs.getString("userId", "null"), viewModel.currentObservingProjectNum.value)

        call.enqueue(object: Callback<ResponseViewProjectApplyData> {
            override fun onResponse(
                call: Call<ResponseViewProjectApplyData>,
                response: Response<ResponseViewProjectApplyData>
            ) {
                if(response.isSuccessful){
                    val body = response.body()
                    if(body!=null){
                        when(body.code){
                            1000->{
                                val data = body.result
                                if(data!=null){
                                    data.forEach{
                                        if(it.pj_inviteStatus == "승인완료"){
                                            teamMemberList.add(it)
                                        }
                                        if(it.pj_inviteStatus == "신청"){
                                            applyList.add(it)
                                        }
                                    }
                                    teamMemberApplyAdapter.teamMemberApplyList = applyList
                                    teamMemberAdapter.teamMemberList = teamMemberList
                                }
                                teamMemberApplyAdapter.notifyDataSetChanged()
                                teamMemberAdapter.notifyDataSetChanged()
                            }
                        }
                    }
                }
            }
            override fun onFailure(call: Call<ResponseViewProjectApplyData>, t: Throwable) {
                TODO("Not yet implemented")
            }

        })

//        val requestTeamMemberData = RequestTeamMemberData(pj_num = viewModel.currentObservingProjectNum.value)
//        val projectMemberCall : Call<ResponseTeamMemberData> = ServiceCreator.myProjectService
//            .viewProjectMember(InfraApplication.prefs.getString("jwt", "null"), InfraApplication.prefs.getString("refreshToken", "null").toInt(),
//                InfraApplication.prefs.getString("userId", "null"), requestTeamMemberData)
//
//        projectMemberCall.enqueue(object: Callback<ResponseTeamMemberData>{
//            override fun onResponse(
//                call: Call<ResponseTeamMemberData>,
//                response: Response<ResponseTeamMemberData>
//            ) {
//                if(response.isSuccessful){
//                    val body = response.body()
//                    if(body!=null){
//                        when(body.code){
//                            1000->{
//                                val data = body.result
//                                if(data!=null){
//                                    teamMemberAdapter.teamMemberList = data
//                                }
//                                teamMemberAdapter.notifyDataSetChanged()
//                            }
//                        }
//                    }
//                }
//            }
//
//            override fun onFailure(call: Call<ResponseTeamMemberData>, t: Throwable) {
//                TODO("Not yet implemented")
//            }
//
//        })
    }
}