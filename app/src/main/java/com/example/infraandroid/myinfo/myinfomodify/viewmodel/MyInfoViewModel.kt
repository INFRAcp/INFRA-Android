package com.example.infraandroid.myinfo.myinfomodify.viewmodel

import android.graphics.Bitmap
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class MyInfoViewModel : ViewModel() {
    private val _currentImg = MutableLiveData<String?>()
    val currentImg: LiveData<String?> = _currentImg

    fun updateImg(mediaPath: String?){
        _currentImg.value = mediaPath
        _currentProfileImgStatus.value = "등록"
    }

    private val _currentProfileImgStatus = MutableLiveData("등록")
    val currentProfileImgStatus: LiveData<String> = _currentProfileImgStatus

    fun deleteImg(){
        _currentImg.value = null
        _currentProfileImgStatus.value = "삭제"
    }

    private val _currentImgBitmap = MutableLiveData<Bitmap?>(null)
    val currentImgBitmap: LiveData<Bitmap?> = _currentImgBitmap

    fun updateImgBitmap(bitmap: Bitmap?){
        _currentImgBitmap.value = bitmap
    }
}