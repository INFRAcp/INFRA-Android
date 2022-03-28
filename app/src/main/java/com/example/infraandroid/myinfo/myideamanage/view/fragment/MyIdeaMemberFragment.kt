package com.example.infraandroid.myinfo.myideamanage.view.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.example.infraandroid.R
import com.example.infraandroid.databinding.FragmentTeamMemberBinding
import com.example.infraandroid.myinfo.myideamanage.model.MyIdeaMemberManageInfo
import com.example.infraandroid.myinfo.myideamanage.model.MyIdeaMemberApplyManageInfo
import com.example.infraandroid.myinfo.myideamanage.model.MyProjectViewModel
import com.example.infraandroid.myinfo.myideamanage.model.ResponseViewProjectApplyData
import com.example.infraandroid.myinfo.myideamanage.view.adapter.MyIdeaMemberApplyAdapter
import com.example.infraandroid.myinfo.teammembereval.view.adapter.TeamMemberAdapter
import com.example.infraandroid.util.BaseFragment
import com.example.infraandroid.util.InfraApplication
import com.example.infraandroid.util.ServiceCreator
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

// 내 정보 > 내 아이디어 탭 레이아웃의 팀원 탭
class MyIdeaMemberFragment : BaseFragment<FragmentTeamMemberBinding>(R.layout.fragment_team_member) {

    private lateinit var viewModel : MyProjectViewModel

    private val teamMemberAdapter = TeamMemberAdapter()
    private val teamMemberApplyAdapter = MyIdeaMemberApplyAdapter()
    private val teamMemberInfo = mutableListOf<MyIdeaMemberManageInfo>()
    private val teamMemberApplicationInfo = mutableListOf<MyIdeaMemberApplyManageInfo>()

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

//        //팀원 관리 어뎁터 연결
//        mBinding?.teamMemberManagementRecyclerview?.adapter = TeamMemberAdapter
//        val tempDataMember = MyIdeaMemberManageInfo(3,"김프라")
//        teamMemberInfo.add(tempDataMember)
//        TeamMemberAdapter.teamMemderlist.addAll(teamMemberInfo)
//        TeamMemberAdapter.notifyDataSetChanged()

        //신청 관리 어뎁터 연결
        binding.applicationManagementRecyclerview.adapter = teamMemberApplyAdapter
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
                                    teamMemberApplyAdapter.teamMemberApplyList = data
                                }
                                teamMemberApplyAdapter.notifyDataSetChanged()
                            }
                        }
                    }
                }
            }

            override fun onFailure(call: Call<ResponseViewProjectApplyData>, t: Throwable) {
                TODO("Not yet implemented")
            }

        })
    }
}