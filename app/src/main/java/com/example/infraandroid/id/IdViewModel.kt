package com.example.infraandroid.id

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

// 로그인/회원가입과 관련한 LiveData를 다루는 ViewModel 페이지
// 작성자 : 신승민
// 작성일 : 2022-02-01

class IdViewModel : ViewModel() {

    companion object{
        const val TAG: String = "로그"
    }

    private val _currentInputId = MutableLiveData<String>()
    private val _currentInputPw = MutableLiveData<String>()
    private val _currentInputCheckPw = MutableLiveData<String>()

    val currentInputId: LiveData<String>
        get() = _currentInputId

    val currentInputPw: LiveData<String>
        get() = _currentInputPw

    val currentInputCheckPw: LiveData<String>
        get() = _currentInputCheckPw

    // 초기값 설정
    init{
        _currentInputId.value = ""
        _currentInputPw.value = ""
        _currentInputCheckPw.value = ""
    }

    fun updateInputId(input: String){
        _currentInputId.value = input
    }

    fun updateInputPw(input: String){
        _currentInputPw.value = input
    }

    fun updateInputCheckPw(input: String){
        _currentInputCheckPw.value = input
    }
}