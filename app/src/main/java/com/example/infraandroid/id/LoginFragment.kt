package com.example.infraandroid.id

import android.content.ContentValues.TAG
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.navigation.findNavController
import com.example.infraandroid.InfraApplication
import com.example.infraandroid.R
import com.example.infraandroid.databinding.FragmentLoginBinding
import com.example.infraandroid.id.api.RequestLoginData
import com.example.infraandroid.id.api.RequestUserData
import com.example.infraandroid.id.api.ResponseLoginData
import com.example.infraandroid.id.api.ServiceCreator
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.kakao.sdk.auth.model.OAuthToken
import com.kakao.sdk.common.model.ClientError
import com.kakao.sdk.common.model.ClientErrorCause
import com.kakao.sdk.user.UserApiClient
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

// 로그인 페이지
// 작성자 : 신승민
// 최종 수정일 : 2022-02-03
// History
// 2022-02-01 : 회원 가입 버튼 누르면 회원 가입 페이지로 이동하도록 수정
// 2022-02-03 : 카카오 로그인 연결

class LoginFragment : Fragment() {
    private var mBinding : FragmentLoginBinding? = null
    private lateinit var auth: FirebaseAuth
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val binding = FragmentLoginBinding.inflate(inflater, container, false)
        auth = FirebaseAuth.getInstance()
        mBinding = binding

        return mBinding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // 로그인 버튼을 눌렀을 때
        mBinding!!.loginButton.setOnClickListener {
            // 로그인할 때 유저 id를 전역변수에 저장
            val inputId = mBinding!!.idEdittext.text.toString()
            val inputPw = mBinding!!.pwEdittext.text.toString()

            //삭제 예정
            if(mBinding!!.idEdittext.text.isEmpty()|| mBinding!!.pwEdittext.text.isEmpty()) {
                Toast.makeText(activity, "아이디와 비밀번호를 입력해주세요.", Toast.LENGTH_SHORT).show()
                mBinding!!.idEdittext.setText("")
                mBinding!!.pwEdittext.setText("")
            }
            else{
                auth.signInWithEmailAndPassword(inputId, inputPw)
                    .addOnCompleteListener { task ->
                        if (task.isSuccessful) {
                            val user = auth.currentUser
                            updateUI(user)
                            InfraApplication.setUserId(inputId)
                            // 로그인 버튼을 누르면 home_fragment로 이동
                            it.findNavController().navigate(R.id.action_login_fragment_to_home_fragment)
                        } else {
                            // If sign in fails, display a message to the user.
                            Toast.makeText(activity, "정확한 아이디와 비밀번호를 입력해주세요.", Toast.LENGTH_SHORT).show()
                        }
                    }
            }

            //작성자 : 이은진
            //작성일 : 2022.02.06
            //Login api 연결
            val requestLoginData = RequestLoginData(
                userId = "",
                userPw = "",
            )
            val call: Call<ResponseLoginData> = ServiceCreator.loginService
                .postLogin(requestLoginData)

            call.enqueue(object : Callback<ResponseLoginData>{
                override fun onResponse(
                    call: Call<ResponseLoginData>,
                    response: Response<ResponseLoginData>
                ) {
                    if(response.isSuccessful){
                        val code = response.body()?.code
                        when(code){
                            1000 -> {
                                Toast.makeText(requireActivity(),"요청에 성공하셨습니다.", Toast.LENGTH_SHORT).show()
                                val user = auth.currentUser
                                updateUI(user)
                                InfraApplication.setUserId(inputId)
                                // 로그인 버튼을 누르면 home_fragment로 이동
                                it.findNavController().navigate(R.id.action_login_fragment_to_home_fragment)
                            }
                            2001 -> {Toast.makeText(requireActivity(),"id가 비어있습니다.", Toast.LENGTH_SHORT).show()}
                            3014 -> {Toast.makeText(requireActivity(),"없는 아이디거나 비밀번호가 틀렸습니다.", Toast.LENGTH_SHORT).show()}
                            4000 -> {Toast.makeText(requireActivity(),"데이터베이스 연결에 실패하였습니다.", Toast.LENGTH_SHORT).show()}
                        }
                    }
                }

                override fun onFailure(call: Call<ResponseLoginData>, t: Throwable) {
                    Log.e("login_server_test", "fail")
                }
            })
        }

        // 회원가입 버튼을 눌렀을 때
        mBinding!!.loginRegisterTv.setOnClickListener {
            it.findNavController().navigate(R.id.action_login_fragment_to_sign_up_first_fragment)
        }



//        mBinding!!.loginKakaotalkIv.setOnClickListener{
//            val callback: (OAuthToken?, Throwable?) -> Unit = { token, error ->
//                if (error != null) {
//                    Log.e(TAG, "카카오계정으로 로그인 실패", error)
//                } else if (token != null) {
//                    Log.i(TAG, "카카오계정으로 로그인 성공 ${token.accessToken}")
//                    it.findNavController().navigate(R.id.action_login_fragment_to_home_fragment)
//                }
//            }
//
//            // 카카오톡이 설치되어 있으면 카카오톡으로 로그인, 아니면 카카오계정으로 로그인
//            if (UserApiClient.instance.isKakaoTalkLoginAvailable(requireActivity())) {
//                UserApiClient.instance.loginWithKakaoTalk(requireActivity()) { token, error ->
//                    if (error != null) {
//                        Log.e(TAG, "카카오톡으로 로그인 실패", error)
//
//                        // 사용자가 카카오톡 설치 후 디바이스 권한 요청 화면에서 로그인을 취소한 경우,
//                        // 의도적인 로그인 취소로 보고 카카오계정으로 로그인 시도 없이 로그인 취소로 처리 (예: 뒤로 가기)
//                        if (error is ClientError && error.reason == ClientErrorCause.Cancelled) {
//                            return@loginWithKakaoTalk
//                        }
//
//                        // 카카오톡에 연결된 카카오계정이 없는 경우, 카카오계정으로 로그인 시도
//                        UserApiClient.instance.loginWithKakaoAccount(requireActivity(), callback = callback)
//                    } else if (token != null) {
//                        Log.i(TAG, "카카오톡으로 로그인 성공 ${token.accessToken}")
//                    }
//                }
//            } else {
//                UserApiClient.instance.loginWithKakaoAccount(requireActivity(), callback = callback)
//            }
//        }
    }

    private fun createUser(email: String, password: String) {
        auth.createUserWithEmailAndPassword(email, password)
            .addOnCompleteListener { task ->
                if (task.isSuccessful) {
                    Toast.makeText(activity, "회원가입 성공", Toast.LENGTH_SHORT).show()
                    val user = auth.currentUser
                    updateUI(user)
                } else {
                    Toast.makeText(activity, "회원가입 실패", Toast.LENGTH_SHORT).show()
                    updateUI(null)
                }
            }
            .addOnFailureListener {
                Log.d(TAG, "createUser: ")
                Toast.makeText(activity, "회원가입 실패", Toast.LENGTH_SHORT).show()
            }
    }

    private fun updateUI(user: FirebaseUser?) {
        user?.let {
            Log.d(TAG, "Email: ${user.email}\nUid: ${user.uid}")
        }
    }


    override fun onDestroyView() {
        mBinding = null
        super.onDestroyView()
    }
}

