package com.example.infraandroid.id

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

// 로그인/회원가입과 관련한 LiveData를 다루는 ViewModel 페이지
// 작성자 : 신승민
// 작성일 : 2022-02-01
// Update
// 2022-02-06 SharedLiveData를 통한 프레그먼트간 데이터 전달 (작성자 : 신승민)

class SharedIdViewModel : ViewModel() {

    companion object{
        const val TAG: String = "로그"
    }

    private val _currentInputId = MutableLiveData<String>("")
    val currentInputId: LiveData<String> = _currentInputId

    private val _currentInputPw = MutableLiveData<String>("")
    val currentInputPw: LiveData<String> = _currentInputPw

    private val _currentInputName = MutableLiveData<String>("")
    val currentInputName: LiveData<String> = _currentInputName

    private val _currentInputPhone = MutableLiveData<String>("")
    val currentInputPhone: LiveData<String> = _currentInputPhone

    private val _currentInputCertificationNumber = MutableLiveData<String>("")
    val currentInputCertificationNumber: LiveData<String> = _currentInputCertificationNumber

    private val _currentInputNickName = MutableLiveData<String>("")
    val currentInputNickName: LiveData<String> = _currentInputNickName

    private val _currentInputEmail = MutableLiveData<String>("")
    val currentInputEmail: LiveData<String> = _currentInputEmail

    fun updateInputId(input: String){
        _currentInputId.value = input
    }

    fun updateInputPw(input: String){
        _currentInputPw.value = input
    }

    fun updateInputName(input: String){
        _currentInputName.value = input
    }

    fun updateInputPhone(input: String){
        _currentInputPhone.value = input
    }

    fun updateInputCertificationNumber(input: String){
        _currentInputCertificationNumber.value = input
    }

    fun updateInputNickName(input: String){
        _currentInputNickName.value = input
    }

    fun updateInputEmail(input: String){
        _currentInputEmail.value = input
    }
}