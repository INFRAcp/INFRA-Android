package com.example.infraandroid.category.view.fragment

import android.util.Log
import androidx.navigation.findNavController
import androidx.navigation.fragment.navArgs
import com.example.infraandroid.R
import com.example.infraandroid.category.model.ResponseLookUpAllProjectData
import com.example.infraandroid.category.view.adapter.IdeaListAdapter
import com.example.infraandroid.databinding.FragmentIdeaListBinding
import com.example.infraandroid.id.viewmodel.SignUpViewModel.Companion.TAG
import com.example.infraandroid.util.BaseFragment
import com.example.infraandroid.util.InfraApplication
import com.example.infraandroid.util.ServiceCreator
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

// 아이디어의 목록을 볼 수 있는 뷰
// Update
// 2022-02-07 뒤로가기버튼 누르면 이전 페이지로 이동 (작성자 : 신승민)

class IdeaListFragment : BaseFragment<FragmentIdeaListBinding>(R.layout.fragment_idea_list) {
    private  val ideaListAdapter = IdeaListAdapter()

    override fun FragmentIdeaListBinding.onCreateView(){

    }

    override fun FragmentIdeaListBinding.onViewCreated(){
        binding.ideaListRecyclerView.adapter = ideaListAdapter
        val args: IdeaListFragmentArgs by navArgs()
        val searchKeyword = args.searchKeyword
        Log.d(TAG, "onViewCreated: $searchKeyword")
        if(searchKeyword==null){
            val call: Call<ResponseLookUpAllProjectData> = ServiceCreator.projectService
                .getLookUpAllProject(InfraApplication.prefs.getString("jwt","null"), InfraApplication.prefs.getString("userId", "null"))

            call.enqueue(object : Callback<ResponseLookUpAllProjectData>{
                override fun onResponse(
                    call: Call<ResponseLookUpAllProjectData>,
                    response: Response<ResponseLookUpAllProjectData>
                ) {
                    if(response.isSuccessful){
                        val body = response.body()
                        if (body != null) {
                            when(body.code){
                                1000 -> {
                                    val data = body.result
                                    if (data != null) {
                                        ideaListAdapter.ideaList = data
                                    }
                                    ideaListAdapter.notifyDataSetChanged()
                                }
                            }
                        }
                    }
                }
                override fun onFailure(call: Call<ResponseLookUpAllProjectData>, t: Throwable) {
                    Log.d(TAG, "onFailure: $t")
                }
            })
        }
        else{
            val call: Call<ResponseLookUpAllProjectData> = ServiceCreator.createProjectService
                .searchProject(InfraApplication.prefs.getString("jwt","null"),searchKeyword,InfraApplication.prefs.getString("userId", "null"))
            call.enqueue(object:Callback<ResponseLookUpAllProjectData>{
                override fun onResponse(
                    call: Call<ResponseLookUpAllProjectData>,
                    response: Response<ResponseLookUpAllProjectData>
                ) {
                    if(response.isSuccessful){
                        val body = response.body()
                        if(body!=null){
                            when(body.code){
                                1000->{
                                    val data = body.result
                                    if(data!=null){
                                        ideaListAdapter.ideaList = data
                                    }
                                }
                            }
                        }
                    }
                }

                override fun onFailure(call: Call<ResponseLookUpAllProjectData>, t: Throwable) {
                    TODO("Not yet implemented")
                }
            })
        }

       binding.ideaListBackButton.setOnClickListener {
           it.findNavController().navigate(R.id.action_idea_list_fragment_to_category_fragment)
       }
    }
}