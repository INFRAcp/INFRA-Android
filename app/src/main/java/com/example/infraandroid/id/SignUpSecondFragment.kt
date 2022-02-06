package com.example.infraandroid.id

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import androidx.appcompat.widget.AppCompatButton
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.findNavController
import com.example.infraandroid.R
import com.example.infraandroid.databinding.FragmentSignUpSecondBinding
import com.example.infraandroid.id.SharedIdViewModel.Companion.TAG
import java.util.regex.Pattern


// 회원가입 second depth 페이지 (휴대폰 본인인증)
// 작성자 : 신승민
// 작성일 : 2022-02-02
// Update
// 2022-02-06 휴대폰 본인인증 서버 연결 (작성자 : 신승민)

class SignUpSecondFragment : Fragment(){
    private var mBinding : FragmentSignUpSecondBinding? = null
    private val sharedViewModel : SharedIdViewModel by activityViewModels()

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

        val inputNameEditText = mBinding?.inputNameEditText as EditText
        val getCertificationButton = mBinding?.receiveMessageButton as AppCompatButton
        val inputPhoneNumberEditText = mBinding?.inputPhoneEditText as EditText
        val inputCertificationNumberEditText = mBinding?.inputCertificationNumberEditText as EditText
        val nextButton = mBinding?.goToThirdSignUpButton as AppCompatButton


        // 전화번호 유효성 검사, 정규식 표현
        inputPhoneNumberEditText.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                getCertificationButton.isEnabled = false
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
                nextButton.isEnabled = inputCertificationNumberEditText.text.toString().length==5
            }

            override fun afterTextChanged(p0: Editable?) {
                nextButton.isEnabled = inputCertificationNumberEditText.text.toString().length==5
            }
        })

        // 다음 버튼 눌렀을 때 다음 페이지로 넘아감
        nextButton.setOnClickListener{
            sharedViewModel.updateInputName(inputNameEditText.text.toString())
            sharedViewModel.updateInputPhone(inputPhoneNumberEditText.text.toString())
            it.findNavController().navigate(R.id.action_sign_up_second_fragment_to_sign_up_third_fragment)
        }

    }

    override fun onDestroyView() {
        mBinding = null
        super.onDestroyView()
    }
}