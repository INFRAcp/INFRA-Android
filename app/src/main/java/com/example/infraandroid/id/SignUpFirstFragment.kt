package com.example.infraandroid.id

import android.os.Bundle
import android.text.Editable
import android.text.InputType
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
import androidx.lifecycle.Observer
import androidx.navigation.findNavController
import com.example.infraandroid.R
import com.example.infraandroid.databinding.FragmentSignUpFirstBinding
import com.example.infraandroid.id.SharedIdViewModel.Companion.TAG
import com.example.infraandroid.id.api.ResponseCheckUserIdData
import com.example.infraandroid.id.api.ServiceCreator
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.util.regex.Pattern
import androidx.lifecycle.ViewModelProvider


// 회원가입 first depth 페이지
// 작성자 : 신승민
// 작성일 : 2022-02-01
// Update
// 2022-02-05 아이디 중복확인 서버 연결, 아이디와 비밀번호 Shared Live Data 이용해서 회원가입으로 보냄(작성자 : 신승민)


class SignUpFirstFragment : Fragment() {
    private  var mBinding : FragmentSignUpFirstBinding? = null
    private val sharedViewModel : SharedIdViewModel by activityViewModels()
    private var isChecked = false

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding = FragmentSignUpFirstBinding.inflate(inflater, container, false)
        mBinding = binding

        return mBinding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)



        val checkButton = mBinding?.checkButton as AppCompatButton
        val inputMakeIdEdittext = mBinding?.inputMakeIdEditText as EditText
        val inputPwVisibilityImageView = mBinding?.inputPwVisibilityImageView as ImageView
        val checkPwVisibilityImageView = mBinding?.checkPwVisibilityImageView as ImageView
        val inputMakePwEditText = mBinding?.inputMakePwEditText as EditText
        val inputCheckPwEditText = mBinding?.inputCheckPwEditText as EditText
        val goToSecondSignUpButton = mBinding?.goToSecondSignUpButton as AppCompatButton
        val doNotUseThisIdTextView = mBinding?.doNotUseThisIdTextView as TextView

        // 아이디 생성 유효성 검사 및 정규식
        inputMakeIdEdittext.addTextChangedListener(object: TextWatcher{
            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                checkButton.isEnabled = false
            }

            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                if (Pattern.matches("^(?=.*\\d)(?=.*[a-zA-Z])(?=.*[0-9]).{6,20}$", inputMakeIdEdittext.text.toString())) {
                    checkButton.isEnabled = true
                }
            }

            override fun afterTextChanged(p0: Editable?) {
                if (Pattern.matches("^(?=.*\\d)(?=.*[a-zA-Z])(?=.*[0-9]).{6,20}$", inputMakeIdEdittext.text.toString())) {
                    checkButton.isEnabled = true
                }
            }
        })


        // 비밀번호 입력 유효성 검사
        inputMakePwEditText.addTextChangedListener(object: TextWatcher{
            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                goToSecondSignUpButton.isEnabled = false
            }

            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                if (Pattern.matches("^(?=.*\\d)(?=.*[~`!@#$%\\^&*()-])(?=.*[a-zA-Z]).{8,20}$", inputMakePwEditText.text.toString())) {
                    if(inputCheckPwEditText.text.toString() == inputMakePwEditText.text.toString())
                        goToSecondSignUpButton.isEnabled = true
                }
            }

            override fun afterTextChanged(p0: Editable?) {
                if (Pattern.matches("^(?=.*\\d)(?=.*[~`!@#$%\\^&*()-])(?=.*[a-zA-Z]).{8,20}$", inputMakePwEditText.text.toString())) {
                    if(inputCheckPwEditText.text.toString() == inputMakePwEditText.text.toString())
                        goToSecondSignUpButton.isEnabled = true
                }
            }
        })


        // 비밀번호 확인과 일치 여부 판단
        inputCheckPwEditText.addTextChangedListener(object: TextWatcher{
            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                goToSecondSignUpButton.isEnabled = false
            }

            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                if (Pattern.matches("^(?=.*\\d)(?=.*[~`!@#$%\\^&*()-])(?=.*[a-zA-Z]).{8,20}$", inputMakePwEditText.text.toString())) {
                    if(inputCheckPwEditText.text.toString() == inputMakePwEditText.text.toString() && isChecked)
                        goToSecondSignUpButton.isEnabled = true
                }
            }

            override fun afterTextChanged(p0: Editable?) {
                if (Pattern.matches("^(?=.*\\d)(?=.*[~`!@#$%\\^&*()-])(?=.*[a-zA-Z]).{8,20}$", inputMakePwEditText.text.toString())) {
                    if(inputCheckPwEditText.text.toString() == inputMakePwEditText.text.toString() && isChecked)
                        goToSecondSignUpButton.isEnabled = true
                }
            }
        })


        // 비밀번호 보이게할지 안보이게할지 이미지 버튼
        inputPwVisibilityImageView.setOnClickListener {
            if (inputPwVisibilityImageView.tag.equals("invisible")) {
                inputPwVisibilityImageView.setImageResource(R.drawable.ic_visible_pw)
                inputMakePwEditText.inputType = InputType.TYPE_TEXT_VARIATION_VISIBLE_PASSWORD
                inputPwVisibilityImageView.tag = "visible"
            }
            else if (inputPwVisibilityImageView.tag.equals("visible")) {
                inputPwVisibilityImageView.setImageResource(R.drawable.ic_hide_pw)
                inputMakePwEditText.inputType =
                    InputType.TYPE_CLASS_TEXT or InputType.TYPE_TEXT_VARIATION_PASSWORD
                inputPwVisibilityImageView.tag = "invisible"
            }
            inputMakePwEditText.setSelection(inputMakePwEditText.text.length)
        }

        checkPwVisibilityImageView.setOnClickListener {
            if (checkPwVisibilityImageView.tag.equals("invisible")) {
                checkPwVisibilityImageView.setImageResource(R.drawable.ic_visible_pw)
                inputCheckPwEditText.inputType = InputType.TYPE_TEXT_VARIATION_VISIBLE_PASSWORD
                checkPwVisibilityImageView.tag = "visible"
            }
            else if (checkPwVisibilityImageView.tag.equals("visible")) {
                checkPwVisibilityImageView.setImageResource(R.drawable.ic_hide_pw)
                inputCheckPwEditText.inputType =
                    InputType.TYPE_CLASS_TEXT or InputType.TYPE_TEXT_VARIATION_PASSWORD
                checkPwVisibilityImageView.tag = "invisible"
            }
            inputCheckPwEditText.setSelection(inputCheckPwEditText.text.length)
        }


        // 다음 버튼 누르면 다음 페이지로 이동
        goToSecondSignUpButton.setOnClickListener {
            val newId = inputMakeIdEdittext.text.toString()
            val newPw = inputMakePwEditText.text.toString()
            sharedViewModel.updateInputId(newId)
            sharedViewModel.updateInputPw(newPw)
            it.findNavController()
                .navigate(R.id.action_sign_up_first_fragment_to_sign_up_second_fragment)
        }

        // 아이디 중복확인 서버 연결
        checkButton.setOnClickListener {
            val call: Call<ResponseCheckUserIdData> = ServiceCreator.userDoubleCheckService
                .doubleCheck(inputMakeIdEdittext.text.toString())

            call.enqueue(object: Callback<ResponseCheckUserIdData>{
                override fun onResponse(call: Call<ResponseCheckUserIdData>, response: Response<ResponseCheckUserIdData>) {
                    if(response.isSuccessful){
                        val data = response.body()?.code
                        if(data==1000){
                            isChecked = true
                            doNotUseThisIdTextView.isVisible = false
                            inputMakeIdEdittext.setBackgroundResource(R.drawable.can_use_this_id_background)
                            mBinding?.canUseIconImageView?.isVisible = true
                        }
                        if(data==2020){
                            doNotUseThisIdTextView.isVisible = true
                            inputMakeIdEdittext.setBackgroundResource(R.drawable.double_check_id_background)
                            mBinding?.canUseIconImageView?.isVisible = false
                        }
                    }
                    else{
                        Toast.makeText(requireActivity(),"네트워크 오류",Toast.LENGTH_SHORT).show()
                    }
                }
                override fun onFailure(call: Call<ResponseCheckUserIdData>, t: Throwable) {
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