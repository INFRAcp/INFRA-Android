package com.example.infraandroid.category.view.fragment

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.appcompat.widget.AppCompatButton
import androidx.fragment.app.Fragment
import com.example.infraandroid.category.model.RequestApplyProjectData
import com.example.infraandroid.category.model.ResponseApplyProjectData
import com.example.infraandroid.databinding.FragmentViewIdeaBinding
import com.example.infraandroid.id.viewmodel.SignUpViewModel.Companion.TAG
import com.example.infraandroid.util.InfraApplication
import com.example.infraandroid.util.ServiceCreator
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

// 다른 사람의 아이디어를 눌렀을 때 볼 수 있는 자세히 보기 뷰

class CategoryViewIdeaFragment : Fragment(){
    private var mBinding : FragmentViewIdeaBinding? = null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding = FragmentViewIdeaBinding.inflate(inflater, container, false)
        mBinding = binding
        return mBinding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val applyButton = mBinding?.teamIdeaApplyButton as AppCompatButton
        applyButton.setOnClickListener {
            val requestApplyProjectData = RequestApplyProjectData(
                projectNum = 1,
                userId = InfraApplication.prefs.getString("userId", "null")
            )

            val call: Call<ResponseApplyProjectData> = ServiceCreator.applyProjectService
                .postApplyProject(requestApplyProjectData)
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

    override fun onDestroyView() {
        mBinding = null
        super.onDestroyView()
    }
}