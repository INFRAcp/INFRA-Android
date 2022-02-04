package com.example.infraandroid.id

import android.os.Bundle
import android.provider.ContactsContract
import android.text.Editable
import android.text.TextWatcher
import android.util.Patterns
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.widget.AppCompatButton
import androidx.fragment.app.Fragment
import androidx.navigation.findNavController
import com.example.infraandroid.R
import com.example.infraandroid.databinding.FragmentSignUpThirdBinding


// 회원가입 third depth 페이지 (닉네임 설정)
// 작성자 : 신승민
// 작성일 : 2022-02-02

class SignUpThirdFragment : Fragment(){
    private  var mBinding : FragmentSignUpThirdBinding? = null

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

        val nickName = mBinding?.inputNickNameEditText as EditText
        val email = mBinding?.inputEmailEditText as EditText
        val nextButton = mBinding?.goToLastSignUpButton as AppCompatButton

        // 이메일 입력 유효성 검사 및 정규식 검사, 닉네임 입력 안하면 안넘어가도록 설정
        email.addTextChangedListener(object:TextWatcher{
            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                nextButton.isEnabled = false
            }

            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                if (Patterns.EMAIL_ADDRESS.matcher(email.text.toString()).matches()
                    && nickName.text.toString().isNotEmpty()
                ) {
                    nextButton.isEnabled = true
                }
            }

            override fun afterTextChanged(p0: Editable?) {
                if (Patterns.EMAIL_ADDRESS.matcher(email.text.toString()).matches()
                    && nickName.text.toString().isNotEmpty()
                ) {
                    nextButton.isEnabled = true
                }
            }
        })

        // 다음 버튼 누르면 다음 페이지로 넘어감
        nextButton.setOnClickListener{
            it.findNavController().navigate(R.id.action_sign_up_third_fragment_to_sign_up_fourth_fragment)
        }
    }

    override fun onDestroyView() {
        mBinding = null
        super.onDestroyView()
    }
}