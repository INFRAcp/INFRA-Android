package com.infra.infraandroid.id.view

import android.os.Bundle
import android.telephony.PhoneNumberFormattingTextWatcher
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.widget.AppCompatButton
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.findNavController
import com.infra.infraandroid.R
import com.infra.infraandroid.databinding.FragmentSignUpSecondBinding
import com.infra.infraandroid.id.viewmodel.SignUpViewModel.Companion.TAG
import com.infra.infraandroid.id.model.RequestSMSData
import com.infra.infraandroid.id.model.ResponseSMSData
import com.infra.infraandroid.util.ServiceCreator
import com.infra.infraandroid.id.viewmodel.SignUpViewModel
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.util.regex.Pattern


// 회원가입 second depth 페이지 (휴대폰 본인인증)
// 작성자 : 신승민
// 작성일 : 2022-02-02
// Update
// 2022-02-06 휴대폰 SMS 본인인증 서버 연결 (작성자 : 신승민)

class SignUpSecondFragment : Fragment(){
    private var mBinding : FragmentSignUpSecondBinding? = null
    private val sharedViewModel : SignUpViewModel by activityViewModels()
    lateinit var certificationCode: String

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val binding = FragmentSignUpSecondBinding.inflate(inflater, container, false)

        mBinding = binding

        return mBinding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val getCertificationButton = mBinding?.receiveMessageButton as AppCompatButton
        val inputPhoneNumberEditText = mBinding?.inputPhoneEditText as EditText
        val inputCertificationNumberEditText = mBinding?.inputCertificationNumberEditText as EditText
        val nextButton = mBinding?.goToThirdSignUpButton as AppCompatButton
        val retryTextView = mBinding?.reInputCodeTextView as TextView
        val certifyImageView = mBinding?.certifyCodeImageView as ImageView

        inputPhoneNumberEditText.addTextChangedListener(PhoneNumberFormattingTextWatcher())

        // 전화번호 유효성 검사, 정규식 표현
        inputPhoneNumberEditText.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                getCertificationButton.isEnabled = true
            }

            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                if (Pattern.matches("^\\d{3}-\\d{3,4}-\\d{4}$", inputPhoneNumberEditText.text.toString())) {
                    getCertificationButton.isEnabled = true
                }
            }

            override fun afterTextChanged(p0: Editable?) {
                if (Pattern.matches("^\\d{3}-\\d{3,4}-\\d{4}$", inputPhoneNumberEditText.text.toString())) {
                    getCertificationButton.isEnabled = true
                }
            }
        })

        // 인증번호 유효성 검사(5글자 안채우면 다음 버튼 활성화 안됨)
        inputCertificationNumberEditText.addTextChangedListener(object : TextWatcher{
            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                nextButton.isEnabled = false
            }

            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                if(inputCertificationNumberEditText.text.toString() == certificationCode && inputCertificationNumberEditText.text.length==5) {
                    retryTextView.isVisible = false
                    inputCertificationNumberEditText.setBackgroundResource(R.drawable.can_use_this_id_background)
                    certifyImageView.isVisible = true
                    nextButton.isEnabled = true
                }
                else{
                    retryTextView.isVisible = true
                    inputCertificationNumberEditText.setBackgroundResource(R.drawable.double_check_id_background)
                    certifyImageView.isVisible = false
                }
            }

            override fun afterTextChanged(p0: Editable?) {
                if(inputCertificationNumberEditText.text.toString() == certificationCode && inputCertificationNumberEditText.text.length==5) {
                    retryTextView.isVisible = false
                    inputCertificationNumberEditText.setBackgroundResource(R.drawable.can_use_this_id_background)
                    certifyImageView.isVisible = true
                    nextButton.isEnabled = true
                }
                else{
                    retryTextView.isVisible = true
                    inputCertificationNumberEditText.setBackgroundResource(R.drawable.double_check_id_background)
                    certifyImageView.isVisible = false
                }
            }
        })

        // 다음 버튼 눌렀을 때 다음 페이지로 넘아감
        nextButton.setOnClickListener{
            sharedViewModel.updateInputPhone(inputPhoneNumberEditText.text.toString())
            it.findNavController().navigate(R.id.action_sign_up_second_fragment_to_sign_up_third_fragment)
        }

        getCertificationButton.setOnClickListener {
            val requestSMSData = RequestSMSData(
                recipientPhoneNumber = inputPhoneNumberEditText.text.toString(),
                title = "본인인증",
                content = "인증번호",
            )
            val call: Call<ResponseSMSData> = ServiceCreator.sendSMSService
                .postSendSMS(requestSMSData)

            call.enqueue(object: Callback<ResponseSMSData> {
                override fun onResponse(
                    call: Call<ResponseSMSData>,
                    response: Response<ResponseSMSData>
                ) {
                    if(response.isSuccessful){
                        Log.d(TAG, "onResponse: 서버 연결 성공" + response.body()?.code)
                        when(response.body()?.code){
                            1000 -> {
                                Toast.makeText(requireActivity(),"인증번호가 발송되었습니다. 잠시만 기다려주세요.", Toast.LENGTH_LONG).show()
                                certificationCode = response.body()?.result?.certifyValue.toString()
                                getCertificationButton.text = "재인증 받기"
                                getCertificationButton.setBackgroundResource(R.drawable.check_same_id_button_enable_false_background)
                                getCertificationButton.setTextColor(resources.getColor(R.color.infra_blue_c))
                            }
                            2601 -> {
                                Toast.makeText(requireActivity(),"전화번호 형식이 알맞지 않습니다.", Toast.LENGTH_SHORT).show()
                            }
                            3601 -> {
                                Toast.makeText(requireActivity(),"해당번호로 이미 가입하였습니다.", Toast.LENGTH_SHORT).show()
                            }
                        }
                    }
                    else{
                        Log.d(TAG, "onResponse: 실패" + response.body()?.code + response.body()?.message)
                    }
                }
                override fun onFailure(call: Call<ResponseSMSData>, t: Throwable) {
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