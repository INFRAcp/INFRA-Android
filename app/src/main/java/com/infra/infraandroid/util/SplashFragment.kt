package com.infra.infraandroid.util

import android.util.Log
import android.widget.Toast
import androidx.navigation.fragment.findNavController
import com.infra.infraandroid.R
import com.infra.infraandroid.databinding.FragmentSplashBinding
import com.infra.infraandroid.id.model.RequestLoginData
import com.infra.infraandroid.id.model.ResponseLoginData
import com.infra.infraandroid.id.viewmodel.SignUpViewModel.Companion.TAG
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class SplashFragment : BaseFragment<FragmentSplashBinding>(R.layout.fragment_splash){
    override fun FragmentSplashBinding.onCreateView() {
        if(InfraApplication.prefs.getUserId().isNotEmpty()
            and InfraApplication.prefs.getUserPW().isNotEmpty()){

            val requestLoginData = RequestLoginData(
                userId = InfraApplication.prefs.getUserId(),
                userPw = InfraApplication.prefs.getUserPW(),
            )

            val call: Call<ResponseLoginData> = ServiceCreator.loginService
                .postLogin(requestLoginData)

            call.enqueue(object : Callback<ResponseLoginData> {
                override fun onResponse(
                    call: Call<ResponseLoginData>,
                    response: Response<ResponseLoginData>
                ) {
                    if(response.isSuccessful){
                        when(response.body()?.code){
                            1000 -> {
                                InfraApplication.prefs.setString("jwt", response.body()?.result?.jwt.toString())
                                InfraApplication.prefs.setString("refreshToken", response.body()?.result?.refreshToken.toString())
                                InfraApplication.prefs.setString("userId", response.body()?.result?.userId.toString())
                                InfraApplication.prefs.setString("userNickName", response.body()?.result?.userNickName.toString())
                                InfraApplication.prefs.setString("userProfileImg", response.body()?.result?.userProfileImg.toString())
                                Toast.makeText(requireActivity(),"인프라에 오신걸 환영합니다 :)", Toast.LENGTH_SHORT).show()
                                // 로그인 버튼을 누르면 home_fragment로 이동
                                findNavController().navigate(R.id.action_splashFragment_to_home_fragment)
                            }
                            else -> {
                                findNavController().navigate(R.id.action_splashFragment_to_login_fragment)
                            }
                        }
                    }
                    else{
                        Log.d(TAG, "onResponse: ${InfraApplication.prefs.getUserId()}")
                    }
                }

                override fun onFailure(call: Call<ResponseLoginData>, t: Throwable) {
                    Log.e("login_server_test", "fail")
                }
            })
        }

        else{
            findNavController().navigate(R.id.action_splashFragment_to_login_fragment)
        }
    }

    override fun FragmentSplashBinding.onViewCreated(){

    }
}