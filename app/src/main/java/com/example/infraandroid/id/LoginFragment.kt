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
import com.example.infraandroid.R
import com.example.infraandroid.UserId
import com.example.infraandroid.databinding.FragmentLoginBinding
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser

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
                            UserId.setUserId(inputId)
                            // 로그인 버튼을 누르면 home_fragment로 이동
                            it.findNavController().navigate(R.id.action_login_fragment_to_home_fragment)
                        } else {
                            // If sign in fails, display a message to the user.
                            Toast.makeText(activity, "정확한 아이디와 비밀번호를 입력해주세요.", Toast.LENGTH_SHORT).show()
                        }
                    }
            }
        }

        // 회원가입 버튼을 눌렀을 때
        mBinding!!.loginRegisterTv.setOnClickListener {
            val inputId = mBinding!!.idEdittext.text.toString()
            val inputPw = mBinding!!.pwEdittext.text.toString()

            createUser(inputId, inputPw)
        }
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

