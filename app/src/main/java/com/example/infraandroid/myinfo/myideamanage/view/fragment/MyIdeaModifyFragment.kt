package com.example.infraandroid.myinfo.myideamanage.view.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.findNavController
import androidx.navigation.fragment.navArgs
import com.example.infraandroid.R
import com.example.infraandroid.category.model.ResponseViewIdeaData
import com.example.infraandroid.databinding.FragmentTeamIdeaBinding
import com.example.infraandroid.myinfo.myideamanage.model.MyProjectViewModel
import com.example.infraandroid.util.BaseFragment
import com.example.infraandroid.util.InfraApplication
import com.example.infraandroid.util.ServiceCreator
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
                    when(response.body()?.code){
                        1000->{
                            binding.myIdea = response.body()?.result
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