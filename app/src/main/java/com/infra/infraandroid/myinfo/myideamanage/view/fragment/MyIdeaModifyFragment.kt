package com.infra.infraandroid.myinfo.myideamanage.view.fragment

import android.os.Bundle
import androidx.core.view.isVisible
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.findNavController
import com.infra.infraandroid.R
import com.infra.infraandroid.category.model.ResponseViewIdeaData
import com.infra.infraandroid.databinding.FragmentTeamIdeaBinding
import com.infra.infraandroid.myinfo.myideamanage.model.MyProjectViewModel
import com.infra.infraandroid.util.BaseFragment
import com.infra.infraandroid.util.InfraApplication
import com.infra.infraandroid.util.ServiceCreator
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

//내 정보 > 내 아이디어 > 아이디어 탭 .kt
class MyIdeaModifyFragment : BaseFragment<FragmentTeamIdeaBinding>(R.layout.fragment_team_idea) {

    private lateinit var viewModel : MyProjectViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        activity?.run{
            viewModel = ViewModelProvider(this, ViewModelProvider.NewInstanceFactory())
                .get(MyProjectViewModel::class.java)
        }
    }

    override fun FragmentTeamIdeaBinding.onCreateView(){

    }

    override fun FragmentTeamIdeaBinding.onViewCreated(){

        val call: Call<ResponseViewIdeaData> = ServiceCreator.projectService
            .viewProject(
                InfraApplication.prefs.getString("jwt", "null"),
                InfraApplication.prefs.getString("refreshToken", "null").toInt(),
                viewModel.currentObservingProjectNum.value,
                InfraApplication.prefs.getString("userId", "null")
            )

        call.enqueue(object: Callback<ResponseViewIdeaData> {
            override fun onResponse(
                call: Call<ResponseViewIdeaData>,
                response: Response<ResponseViewIdeaData>
            ) {
                if(response.isSuccessful){
                    val data = response.body()?.result
                    when(response.body()?.code){
                        1000->{
                            binding.myIdea = response.body()?.result
                            viewModel.updateObservingProjectNum(data?.pj_num)
                            viewModel.updateMyProjectHeader(data?.pj_header)
                            viewModel.updateMyProjectCategory(data?.pj_categoryName)
                            viewModel.updateMyProjectSubCategory(data?.pj_subCategoryName)
                            viewModel.updateMyProjectContent(data?.pj_content)
                            viewModel.updateMyProjectProgress(data?.pj_progress)
                            viewModel.updateMyProjectStartTerm(data?.pj_startTerm)
                            viewModel.updateMyProjectEndTerm(data?.pj_endTerm)
                            viewModel.updateMyProjectDeadLine(data?.pj_deadline)
                            viewModel.updateMyProjectTotalPerson(data?.pj_totalPerson?.toInt())
                            viewModel.updateMyProjectImg(data?.pj_photo?.get(0))
                            viewModel.clearMyProjectHashTag()
                            if(data?.hashtag != null){
                                for(ht in data.hashtag){
                                    viewModel.updateMyProjectHashTag(ht)
                                }
                            }
                            if (data != null) {
                                if(data.pj_recruit != "마감"){
                                    binding.teamIdeaModifyBtnTv.isVisible = true
                                }
                            }
                        }
                    }
                }
            }

            override fun onFailure(call: Call<ResponseViewIdeaData>, t: Throwable) {
                TODO("Not yet implemented")
            }

        })

        binding?.teamIdeaModifyBtnTv?.setOnClickListener{
            it.findNavController().navigate(R.id.action_myInfoTeamIdeaFragment_to_myInfoProjectModifyFragment)
        }
    }
}