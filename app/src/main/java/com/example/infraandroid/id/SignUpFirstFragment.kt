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
import androidx.appcompat.widget.AppCompatButton
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.infraandroid.R
import com.example.infraandroid.databinding.FragmentSignUpFirstBinding
import com.example.infraandroid.id.IdViewModel.Companion.TAG


// 회원가입 first depth 페이지
// 작성자 : 신승민
// 작성일 : 2022-02-01


class SignUpFirstFragment : Fragment() {
    private  var mBinding : FragmentSignUpFirstBinding? = null
    lateinit var idViewModel: IdViewModel

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

        inputMakeIdEdittext.addTextChangedListener(object: TextWatcher{
            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                checkButton.isEnabled = false
            }

            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                checkButton.isEnabled = inputMakeIdEdittext.text.toString().length>=6
            }

            override fun afterTextChanged(p0: Editable?) {
                checkButton.isEnabled = inputMakeIdEdittext.text.toString().length>=6
            }
        })

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

    }

    override fun onDestroyView() {
        mBinding = null
        super.onDestroyView()
    }
}