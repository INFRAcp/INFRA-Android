package com.infra.infraandroid.id.view

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import android.util.Patterns
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.widget.AppCompatButton
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.findNavController
import com.infra.infraandroid.R
import com.infra.infraandroid.databinding.FragmentSignUpThirdBinding
import com.infra.infraandroid.id.viewmodel.SignUpViewModel.Companion.TAG
import com.infra.infraandroid.id.model.RequestUserData
import com.infra.infraandroid.id.model.ResponseUserData
import com.infra.infraandroid.util.ServiceCreator
import com.infra.infraandroid.id.viewmodel.SignUpViewModel
import com.infra.infraandroid.myinfo.myinfomodify.model.RequestNicknameCheckData
import com.infra.infraandroid.myinfo.myinfomodify.model.ResponseNicknameCheckData
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.util.regex.Pattern


// 회원가입 third depth 페이지 (닉네임 설정)
// 작성자 : 신승민
// 작성일 : 2022-02-02
// Update
// 2022-02-06 회원가입 첫 페이지와 두번째 페이지에서 Shared Live Data로 정보 받아서 서버에 넘겨주는 작업 (작성자 : 신승민)

class SignUpThirdFragment : Fragment(){
    private  var mBinding : FragmentSignUpThirdBinding? = null
    private val sharedViewModel : SignUpViewModel by activityViewModels()
    private var isChecked : Boolean = false

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding = FragmentSignUpThirdBinding.inflate(inflater, container, false)

        mBinding = binding
        return mBinding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val email = mBinding?.inputEmailEditText as EditText
        val nextButton = mBinding?.goToLastSignUpButton as AppCompatButton


        // 이메일 입력 유효성 검사 및 정규식 검사, 닉네임 입력 안하면 안넘어가도록 설정
        email.addTextChangedListener(object:TextWatcher{
            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                nextButton.isEnabled = false
            }

            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                if (Patterns.EMAIL_ADDRESS.matcher(email.text.toString()).matches()) {
                    nextButton.isEnabled = isChecked
                }
            }

            override fun afterTextChanged(p0: Editable?) {
                if (Patterns.EMAIL_ADDRESS.matcher(email.text.toString()).matches()) {
                    nextButton.isEnabled = isChecked
                }
            }
        })

        // 다음 버튼 누르면 다음 페이지로 넘어감
        nextButton.setOnClickListener{
            sharedViewModel.updateInputEmail(email.text.toString())
            val requestUserData = RequestUserData(
                userId = "",
                userPw = "",
                userPhone = "",
                userEmail = "",
                userNickName = ""
            )
            sharedViewModel.currentInputId.observe(viewLifecycleOwner) { currentInputId ->
                requestUserData.userId = currentInputId
            }
            sharedViewModel.currentInputPw.observe(viewLifecycleOwner) { currentInputPw ->
                requestUserData.userPw = currentInputPw
            }
            sharedViewModel.currentInputPhone.observe(viewLifecycleOwner) { currentInputPhone ->
                requestUserData.userPhone = currentInputPhone
            }
            sharedViewModel.currentInputEmail.observe(viewLifecycleOwner) { currentInputEmail ->
                requestUserData.userEmail = currentInputEmail
            }

            requestUserData.userNickName = mBinding?.inputNicknameEditText?.text.toString()

            val call: Call<ResponseUserData> = ServiceCreator.signUpService
                .postSignUp(requestUserData)

            call.enqueue(object:Callback<ResponseUserData>{
                override fun onResponse(
                    call: Call<ResponseUserData>,
                    response: Response<ResponseUserData>
                ) {
                    if(response.isSuccessful){
                        when (response.body()?.code) {
                            1000 -> {
                                Log.d(TAG, "onResponse: 회원가입 성공!")
                                it.findNavController().navigate(R.id.action_sign_up_third_fragment_to_sign_up_fourth_fragment)
                            }
                            3103 -> {
                                Toast.makeText(requireActivity(),"중복된 핸드폰 번호입니다.", Toast.LENGTH_SHORT).show()
                            }
                            2102 -> {
                                Toast.makeText(requireActivity(),"이메일 형식을 확인해주세요.", Toast.LENGTH_SHORT).show()
                            }
                            3105 -> {
                                Toast.makeText(requireActivity(),"중복된 이메일입니다.", Toast.LENGTH_SHORT).show()
                            }
                            2107 -> {
                                Toast.makeText(requireActivity(),"필수 정보가 비어있습니다.", Toast.LENGTH_SHORT).show()
                            }
                            3116 -> {
                                Toast.makeText(requireActivity(),"탈퇴한 후 일주일 후에 가입 가능합니다.", Toast.LENGTH_SHORT).show()
                            }
                            3117 -> {
                                Toast.makeText(requireActivity(),"강제 탈퇴된 사용자로 3개월 후에 가입 가능합니다.", Toast.LENGTH_SHORT).show()
                            }
                            3118 -> {
                                Toast.makeText(requireActivity(),"이미 인프라의 회원입니다.", Toast.LENGTH_SHORT).show()
                            }
                            4000 -> {
                                Toast.makeText(requireActivity(),"데이터베이스 연결에 실패하였습니다.", Toast.LENGTH_SHORT).show()
                            }
                        }
                    }
                    else{
                        Log.d(TAG, "onResponse: 연결 실패..")
                    }
                }

                override fun onFailure(call: Call<ResponseUserData>, t: Throwable) {
                    Log.d(TAG, "onFailure: $t")
                }

            })
        }

        mBinding?.inputNicknameEditText?.addTextChangedListener(object: TextWatcher{
            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                mBinding?.checkButton?.isEnabled = false
            }

            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                mBinding?.checkButton?.isEnabled = mBinding?.inputNicknameEditText?.text?.length!! in 2..6
            }

            override fun afterTextChanged(p0: Editable?) {
                mBinding?.checkButton?.isEnabled = mBinding?.inputNicknameEditText?.text?.length!! in 2..6
            }
        })


        mBinding?.checkButton?.setOnClickListener{
            //닉네임 중복 서버 연결

            val requestNicknameCheckData = RequestNicknameCheckData(
                userNickname = mBinding?.inputNicknameEditText?.text.toString(),
            )

            val call: Call<ResponseNicknameCheckData> = ServiceCreator.nicknameDoubleCheckService
                .doublecheck(requestNicknameCheckData)
            call.enqueue(object : Callback<ResponseNicknameCheckData>{
                override fun onResponse(
                    call: Call<ResponseNicknameCheckData>,
                    response: Response<ResponseNicknameCheckData>
                ) {
                    if(response.isSuccessful){
                        val data = response.body()?.code
                        if(data==1000) {
                            isChecked = true
                            mBinding?.doNotUseThisNickNameTextView?.isVisible = false
                            mBinding?.canUseIconImageView?.isVisible = true
                            mBinding?.inputNicknameEditText?.setBackgroundResource(R.drawable.can_use_this_id_background)
                            nextButton.isEnabled = true
                        }
                        if(data==3104){
                            mBinding?.doNotUseThisNickNameTextView?.isVisible = true
                            mBinding?.canUseIconImageView?.isVisible = false
                            mBinding?.inputNicknameEditText?.setBackgroundResource(R.drawable.double_check_id_background)
                            nextButton.isEnabled = false
                        }
                    }
                }

                override fun onFailure(call: Call<ResponseNicknameCheckData>, t: Throwable) {

                }

            })
        }


    }

    override fun onDestroyView() {
        mBinding = null
        super.onDestroyView()
    }
}