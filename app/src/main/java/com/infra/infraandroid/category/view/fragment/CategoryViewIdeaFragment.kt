package com.infra.infraandroid.category.view.fragment

import android.util.Log
import android.widget.Toast
import androidx.core.view.isVisible
import androidx.navigation.findNavController
import androidx.navigation.fragment.navArgs
import com.infra.infraandroid.R
import com.infra.infraandroid.category.model.RequestApplyProjectData
import com.infra.infraandroid.category.model.ResponseApplyProjectData
import com.infra.infraandroid.category.model.ResponseViewIdeaData
import com.infra.infraandroid.databinding.FragmentViewIdeaBinding
import com.infra.infraandroid.id.viewmodel.SignUpViewModel.Companion.TAG
import com.infra.infraandroid.util.BaseFragment
import com.infra.infraandroid.util.InfraApplication
import com.infra.infraandroid.util.ServiceCreator
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

// 다른 사람의 아이디어를 눌렀을 때 볼 수 있는 자세히 보기 뷰

class CategoryViewIdeaFragment : BaseFragment<FragmentViewIdeaBinding>(R.layout.fragment_view_idea){
    private var writerId : String ?= null
    private var opponentProfileImg : String ?= null

    override fun FragmentViewIdeaBinding.onCreateView(){

    }

    override fun FragmentViewIdeaBinding.onViewCreated() {
        val args: CategoryViewIdeaFragmentArgs by navArgs()
        val projectNum = args.projectNum
        Log.d(TAG, "onViewCreated: $projectNum")

        val call: Call<ResponseViewIdeaData> = ServiceCreator.projectService
            .viewProject(InfraApplication.prefs.getString("jwt", "null"),
                InfraApplication.prefs.getString("refreshToken", "null").toInt(),
                projectNum,
                InfraApplication.prefs.getString("userId", "null")
            )

        call.enqueue(object:Callback<ResponseViewIdeaData>{
            override fun onResponse(
                call: Call<ResponseViewIdeaData>,
                response: Response<ResponseViewIdeaData>
            ) {
                if(response.isSuccessful){
                    when(response.body()?.code){
                        1000 -> {
                            binding.viewIdea = response.body()?.result
                            writerId = response.body()?.result?.user_nickname
                            opponentProfileImg = response.body()?.result?.user_prPhoto
                            if(writerId == InfraApplication.prefs.getString("userNickName", "null")){
                                binding.startChattingImageButton.isVisible = false
                                binding.teamIdeaApplyButton.isVisible = false
                            }
                        }
                    }
                }
            }

            override fun onFailure(call: Call<ResponseViewIdeaData>, t: Throwable) {
                Log.d(TAG, "onFailure: $t")
            }
        })

        binding.startChattingImageButton.setOnClickListener {
            val action = CategoryViewIdeaFragmentDirections.actionCategoryViewIdeaFragmentToChatFragment(writerId = writerId, opponentProfileImg)
            it.findNavController().navigate(action)
        }


        val applyButton = binding.teamIdeaApplyButton
        applyButton.setOnClickListener {
            val requestApplyProjectData = RequestApplyProjectData(
                projectNum = projectNum,
                userId = InfraApplication.prefs.getString("userId", "null")
            )

            val call: Call<ResponseApplyProjectData> = ServiceCreator.projectService
                .postApplyProject(InfraApplication.prefs.getString("jwt","null"), InfraApplication.prefs.getString("refreshToken", "null").toInt(), requestApplyProjectData)
            call.enqueue(object:Callback<ResponseApplyProjectData>{
                override fun onResponse(
                    call: Call<ResponseApplyProjectData>,
                    response: Response<ResponseApplyProjectData>
                ) {
                    if(response.isSuccessful){
                        when(response.body()?.code){
                            1000 -> Toast.makeText(requireActivity(), "신청이 완료되었습니다.", Toast.LENGTH_SHORT).show()
                            2320 -> Toast.makeText(requireActivity(), "이미 지원한 프로젝트입니다.", Toast.LENGTH_SHORT).show()
                            2325 -> Toast.makeText(requireActivity(), "거절된 프로젝트입니다.", Toast.LENGTH_SHORT).show()
                        }
                    }
                }

                override fun onFailure(call: Call<ResponseApplyProjectData>, t: Throwable) {
                    Log.d(TAG, "onFailure: $t")
                }
            })
        }
    }
}