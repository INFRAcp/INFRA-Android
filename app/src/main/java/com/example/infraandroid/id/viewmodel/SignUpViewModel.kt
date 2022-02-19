package com.example.infraandroid.id.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

// 회원가입과 관련한 LiveData를 다루는 ViewModel 페이지
// 작성자 : 신승민
// 작성일 : 2022-02-01
// Update
// 2022-02-06 SharedLiveData를 통한 프레그먼트간 데이터 전달 (작성자 : 신승민)

class SignUpViewModel : ViewModel() {

    companion object{
        const val TAG: String = "로그"
    }

    private val _currentInputId = MutableLiveData<String>("")
    val currentInputId: LiveData<String> = _currentInputId

    private val _currentInputPw = MutableLiveData<String>("")
    val currentInputPw: LiveData<String> = _currentInputPw

    private val _currentInputPhone = MutableLiveData<String>("")
    val currentInputPhone: LiveData<String> = _currentInputPhone

    private val _currentInputEmail = MutableLiveData<String>("")
    val currentInputEmail: LiveData<String> = _currentInputEmail

    fun updateInputId(input: String){
        _currentInputId.value = input
    }

    fun updateInputPw(input: String){
        _currentInputPw.value = input
    }

    fun updateInputPhone(input: String){
        _currentInputPhone.value = input
    }

    fun updateInputEmail(input: String){
        _currentInputEmail.value = input
    }
}