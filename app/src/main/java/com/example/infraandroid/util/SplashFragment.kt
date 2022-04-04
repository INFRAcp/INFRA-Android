package com.example.infraandroid.util

import android.util.Log
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.infraandroid.R
import com.example.infraandroid.databinding.FragmentSplashBinding
import com.example.infraandroid.id.model.RequestLoginData
import com.example.infraandroid.id.model.ResponseLoginData
import com.example.infraandroid.id.viewmodel.SignUpViewModel.Companion.TAG
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
                                Toast.makeText(requireActivity(),"요청에 성공하셨습니다.", Toast.LENGTH_SHORT).show()
                                // 로그인 버튼을 누르면 home_fragment로 이동
                                findNavController().navigate(R.id.action_splashFragment_to_home_fragment)
                            }
                            2001 -> {
                                Toast.makeText(requireActivity(),"id가 비어있습니다.", Toast.LENGTH_SHORT).show()}
                            3014 -> {
                                Toast.makeText(requireActivity(),"없는 아이디거나 비밀번호가 틀렸습니다.", Toast.LENGTH_SHORT).show()}
                            4000 -> {
                                Toast.makeText(requireActivity(),"데이터베이스 연결에 실패하였습니다.", Toast.LENGTH_SHORT).show()}
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