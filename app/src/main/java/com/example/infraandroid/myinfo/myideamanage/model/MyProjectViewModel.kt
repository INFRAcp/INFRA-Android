package com.example.infraandroid.myinfo.myideamanage.model

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class MyProjectViewModel : ViewModel() {
    private val _currentObservingProjectNum = MutableLiveData<Int>()
    val currentObservingProjectNum: LiveData<Int> = _currentObservingProjectNum

    private val _currentApplyUserId = MutableLiveData<String>()
    val currentApplyUserId: LiveData<String> = _currentApplyUserId

    fun updateObservingProjectNum(projectNum: Int){
        _currentObservingProjectNum.value = projectNum
    }

    fun updateCurrentApplyUserId(userId: String){
        _currentApplyUserId.value = userId
    }
}