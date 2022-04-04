package com.example.infraandroid.myinfo.myideamanage.view.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.findNavController
import com.example.infraandroid.R
import com.example.infraandroid.databinding.FragmentMyInfoMyIdeaBinding
import com.example.infraandroid.myinfo.myideamanage.model.MyIdeaListInfo
import com.example.infraandroid.myinfo.myideamanage.model.ResponseMyProjectListData
import com.example.infraandroid.myinfo.myideamanage.view.adapter.MyIdeaListAdapter
import com.example.infraandroid.util.InfraApplication
import com.example.infraandroid.util.ServiceCreator
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

//내 정보 > 내 아이디어 관리 파일.kt
class MyIdeaManageFragment : Fragment() {
    private var mBinding: FragmentMyInfoMyIdeaBinding? = null
    private val myIdeaListAdapter = MyIdeaListAdapter()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding = FragmentMyInfoMyIdeaBinding.inflate(inflater, container, false)
        mBinding = binding
        return mBinding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        mBinding?.myIdeaListRecyclerView?.adapter = myIdeaListAdapter

        mBinding?.myIdeaListBackButton?.setOnClickListener {
            it.findNavController().navigate(R.id.action_myInfoMyIdeaFragment_to_my_info_fragment)
        }

        val call : Call<ResponseMyProjectListData> = ServiceCreator.myProjectService
            .viewMyProject(InfraApplication.prefs.getString("jwt", "null"), InfraApplication.prefs.getString("refreshToken", "null").toInt(), InfraApplication.prefs.getString("userId","null"))

        call.enqueue(object: Callback<ResponseMyProjectListData>{
            override fun onResponse(
                call: Call<ResponseMyProjectListData>,
                response: Response<ResponseMyProjectListData>
            ) {
                if(response.isSuccessful){
                    val body = response.body()
                    if(body!=null){
                        when(body.code){
                            1000->{
                                val data = body.result
                                if(data!=null){
                                    myIdeaListAdapter.myIdeaList = data
                                }
                                myIdeaListAdapter.notifyDataSetChanged()
                            }
                        }
                    }
                }
            }

            override fun onFailure(call: Call<ResponseMyProjectListData>, t: Throwable) {
                TODO("Not yet implemented")
            }
        })

    }

    override fun onDestroyView() {
        mBinding = null
        super.onDestroyView()
    }
}