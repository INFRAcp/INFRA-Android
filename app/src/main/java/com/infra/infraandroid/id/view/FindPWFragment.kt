package com.infra.infraandroid.id.view

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.findNavController
import com.infra.infraandroid.R
import com.infra.infraandroid.databinding.FragmentFindPwBinding
import com.infra.infraandroid.id.model.RequestPWData
import com.infra.infraandroid.id.model.ResponsePWData
import com.infra.infraandroid.myinfo.myideamanage.model.MyProjectViewModel
import com.infra.infraandroid.util.BaseFragment
import com.infra.infraandroid.util.ServiceCreator
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.util.regex.Pattern

class FindPWFragment : BaseFragment<FragmentFindPwBinding>(R.layout.fragment_find_pw){
    private lateinit var viewModel : MyProjectViewModel
    private val TAG = "로그"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        activity?.run{
            viewModel = ViewModelProvider(this, ViewModelProvider.NewInstanceFactory())
                .get(MyProjectViewModel::class.java)
        }
    }

    override fun FragmentFindPwBinding.onCreateView() {
    }

    override fun FragmentFindPwBinding.onViewCreated() {
        val inputPhoneNumEditText = binding.inputNumEditText
        val nextButton = binding.goToSecondPwButton
        val backButton = binding.findPwBackButton

        //다음 버튼 활성화 설정
        inputPhoneNumEditText.addTextChangedListener(object : TextWatcher{
            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                nextButton.isEnabled = false
            }

            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                if (Pattern.matches("^\\d{3}-\\d{3,4}-\\d{4}$", inputPhoneNumEditText.text.toString())) {
                    nextButton.isEnabled = true
                }
            }

            override fun afterTextChanged(p0: Editable?) {
                if (Pattern.matches("^\\d{3}-\\d{3,4}-\\d{4}$", inputPhoneNumEditText.text.toString())) {
                    nextButton.isEnabled = true
                }
            }
        })

        //뒤로 가기 눌렀을 때 로그인 화면
        backButton.setOnClickListener {
            it.findNavController().navigate(R.id.action_findPWFragment_to_login_fragment)
        }

        //비밀번호 조회 서버 연결
        nextButton.setOnClickListener {
            val requestPWData = RequestPWData(
                userPhone = inputPhoneNumEditText.text.toString()
            )
            it.findNavController().navigate(R.id.action_findPWFragment_to_findPWSecondFragment)

            val call: Call<ResponsePWData> = ServiceCreator.pwService
                .findPWInfo(requestPWData)

            call.enqueue(object: Callback<ResponsePWData>{
                override fun onResponse(
                    call: Call<ResponsePWData>,
                    response: Response<ResponsePWData>
                ) {
                    if(response.isSuccessful){
                        Log.d(TAG, "onResponse: 서버 연결 성공" + response.body()?.code)
                    }
                    else{
                        Log.d(TAG, "onResponse: 실패" + response.body()?.code + response.body()?.message)
                    }
                }

                override fun onFailure(call: Call<ResponsePWData>, t: Throwable) {
                    Log.d(TAG, "onFailure: $t")
                }
            })
        }


    }
}