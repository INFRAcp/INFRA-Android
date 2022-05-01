package com.example.infraandroid.id.view

import android.content.Context
import android.content.Intent
import android.content.IntentSender
import android.util.Log
import android.widget.Button
import android.widget.Toast
import androidx.navigation.findNavController
import androidx.navigation.fragment.findNavController
//import com.example.infraandroid.BuildConfig
import com.example.infraandroid.util.InfraApplication
import com.example.infraandroid.R
import com.example.infraandroid.databinding.FragmentLoginBinding
import com.example.infraandroid.id.model.RequestLoginData
import com.example.infraandroid.id.model.ResponseLoginData
import com.example.infraandroid.id.viewmodel.SignUpViewModel.Companion.TAG
import com.example.infraandroid.util.BaseFragment
import com.example.infraandroid.util.ServiceCreator
import com.google.android.gms.auth.api.identity.BeginSignInRequest
import com.google.android.gms.auth.api.identity.Identity
import com.google.android.gms.auth.api.identity.SignInClient
import com.google.android.gms.common.api.ApiException
import com.google.android.gms.common.api.CommonStatusCodes
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

// 로그인 페이지
// 작성자 : 신승민
// 최종 수정일 : 2022-02-03
// History
// 2022-02-01 : 회원 가입 버튼 누르면 회원 가입 페이지로 이동하도록 수정
// 2022-02-03 : 카카오 로그인 연결

class LoginFragment : BaseFragment<FragmentLoginBinding>(R.layout.fragment_login){
//    lateinit var mOAuthLoginInstance : OAuthLogin
    lateinit var mContext: Context
    private lateinit var oneTapClient: SignInClient
    private lateinit var signInRequest: BeginSignInRequest
    private val REQ_ONE_TAP = 100
    private var showOneTapUI = true

    override fun FragmentLoginBinding.onCreateView(){
//        oneTapClient = Identity.getSignInClient(requireActivity())
//        signInRequest = BeginSignInRequest.builder()
//            .setPasswordRequestOptions(BeginSignInRequest.PasswordRequestOptions.builder()
//                .setSupported(true)
//                .build())
//            .setGoogleIdTokenRequestOptions(
//                BeginSignInRequest.GoogleIdTokenRequestOptions.builder()
//                    .setSupported(true)
//                    // Your server's client ID, not your Android client ID.
//                    .setServerClientId(BuildConfig.WEB_CLIENT_ID)
//                    // Only show accounts previously used to sign in.
//                    .setFilterByAuthorizedAccounts(true)
//                    .build())
//            // Automatically sign in when exactly one credential is retrieved.
//            .setAutoSelectEnabled(true)
//            .build()

    }

    override fun FragmentLoginBinding.onViewCreated(){
        // 로그인 버튼을 눌렀을 때
        binding.loginButton.setOnClickListener {
            val inputId = binding.idEdittext.text.toString()
            val inputPw = binding.pwEdittext.text.toString()

            //작성자 : 이은진
            //작성일 : 2022.02.06
            //Login api 연결
            val requestLoginData = RequestLoginData(
                userId = inputId,
                userPw = inputPw,
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
                                InfraApplication.prefs.setString("jwt", response.body()?.result?.jwt.toString())
                                InfraApplication.prefs.setString("refreshToken", response.body()?.result?.refreshToken.toString())
                                InfraApplication.prefs.setString("userId", response.body()?.result?.userId.toString())
                                InfraApplication.prefs.setString("userNickName", response.body()?.result?.userNickName.toString())
                                InfraApplication.prefs.setString("userProfileImg", response.body()?.result?.userProfileImg.toString())
                                InfraApplication.prefs.setUserId(response.body()?.result?.userId.toString())
                                InfraApplication.prefs.setUserPW(inputPw)
                                Toast.makeText(requireActivity(),"요청에 성공하셨습니다.", Toast.LENGTH_SHORT).show()
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
        binding.loginRegisterTv.setOnClickListener {
            it.findNavController().navigate(R.id.action_login_fragment_to_sign_up_first_fragment)
        }

        //비밀번호 찾기 눌렀을 때
        binding.loginFindIdPwTv.setOnClickListener{
            it.findNavController().navigate(R.id.action_login_fragment_to_findPWFragment)
        }

//        binding.loginGoogleIv.setOnClickListener {
//            oneTapClient.beginSignIn(signInRequest)
//                .addOnSuccessListener(requireActivity()) { result ->
//                    try {
//                        startIntentSenderForResult(
//                            result.pendingIntent.intentSender, REQ_ONE_TAP,
//                            null, 0, 0, 0, null)
//                    } catch (e: IntentSender.SendIntentException) {
//                        Log.e(TAG, "Couldn't start One Tap UI: ${e.localizedMessage}")
//                    }
//                }
//                .addOnFailureListener(requireActivity()) { e ->
//                    // No saved credentials found. Launch the One Tap sign-up flow, or
//                    // do nothing and continue presenting the signed-out UI.
//                    Log.d(TAG, e.localizedMessage)
//                }
//        }

        //  네이버 아이디로 로그인
//        val naver_client_id = "WWSmSMIYeWU77c_0uql8"
//        val naver_client_secret = "QNsQFfwiiQ"
//        val naver_client_name = "infra"
//
//        mOAuthLoginInstance = OAuthLogin.getInstance()
//        mOAuthLoginInstance.init(mContext, naver_client_id, naver_client_secret, naver_client_name)
//
//        buttonOAuthLoginImg.setOAuthLoginHandler(mOAuthLoginHandler)


//        binding!!.loginKakaotalkIv.setOnClickListener{
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

    //네이버 로그인
//    val  mOAuthLoginHandler: OAuthLoginHandler = object : OAuthLoginHandler() {
//        override fun run(success: Boolean) {
//            if (success) {
//            } else {
//                val errorCode: String = mOAuthLoginInstance.getLastErrorCode(mContext).code
//                val errorDesc = mOAuthLoginInstance.getLastErrorDesc(mContext)
//
//                Toast.makeText(
//                    baseContext, "errorCode:" + errorCode
//                            + ", errorDesc:" + errorDesc, Toast.LENGTH_SHORT
//                ).show()
//            }
//        }
//    }

//    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
//        super.onActivityResult(requestCode, resultCode, data)
//
//        when (requestCode) {
//            REQ_ONE_TAP -> {
//                try {
//                    val credential = oneTapClient.getSignInCredentialFromIntent(data)
//                    val idToken = credential.googleIdToken
//                    val username = credential.id
//                    val password = credential.password
//                    when {
//                        idToken != null -> {
//                            // Got an ID token from Google. Use it to authenticate
//                            // with your backend.
//                            Log.d(TAG, "Got ID token.")
//                        }
//                        password != null -> {
//                            // Got a saved username and password. Use them to authenticate
//                            // with your backend.
//                            Log.d(TAG, "Got password.")
//                        }
//                        else -> {
//                            // Shouldn't happen.
//                            Log.d(TAG, "No ID token or password!")
//                        }
//                    }
//                } catch (e: ApiException) {
//                    when (e.statusCode) {
//                        CommonStatusCodes.CANCELED -> {
//                            Log.d(TAG, "One-tap dialog was closed.")
//                            // Don't re-prompt the user.
//                            showOneTapUI = false
//                        }
//                        CommonStatusCodes.NETWORK_ERROR -> {
//                            Log.d(TAG, "One-tap encountered a network error.")
//                            // Try again or just ignore.
//                        }
//                        else -> {
//                            Log.d(TAG, "Couldn't get credential from result." +
//                                    " (${e.localizedMessage})")
//                        }
//                    }
//                }
//            }
//        }
//    }
}

