package com.example.infraandroid.myinfo.myideamanage.model

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class MyProjectViewModel : ViewModel() {
    private val _currentObservingProjectNum = MutableLiveData<Int>()
    val currentObservingProjectNum: LiveData<Int> = _currentObservingProjectNum

    private val _currentApplyUserId = MutableLiveData<String>()
    val currentApplyUserId: LiveData<String> = _currentApplyUserId

    private val _currentMyProjectHeader = MutableLiveData<String>()
    val currentMyProjectHeader: LiveData<String> = _currentMyProjectHeader

    private val _currentMyProjectContent = MutableLiveData<String>()
    val currentMyProjectContent: LiveData<String> = _currentMyProjectContent

    private val _currentMyProjectCategory = MutableLiveData<String>()
    val currentMyProjectCategory: LiveData<String> = _currentMyProjectCategory

    private val _currentMyProjectSubCategory = MutableLiveData<String>()
    val currentMyProjectSubCategory: LiveData<String> = _currentMyProjectSubCategory

    private val _currentMyProjectProgress = MutableLiveData<String>()
    val currentMyProjectProgress: LiveData<String> = _currentMyProjectProgress

    private val _currentMyProjectStartTerm = MutableLiveData<String>()
    val currentMyProjectStartTerm: LiveData<String> = _currentMyProjectStartTerm

    private val _currentMyProjectEndTerm = MutableLiveData<String>()
    val currentMyProjectEndTerm: LiveData<String> = _currentMyProjectEndTerm

    private val _currentMyProjectDeadLine = MutableLiveData<String>()
    val currentMyProjectDeadLine: LiveData<String> = _currentMyProjectDeadLine

    private val _currentMyProjectTotalPerson = MutableLiveData<Int>()
    val currentMyProjectTotalPerson: LiveData<Int> = _currentMyProjectTotalPerson

    private val _currentMyProjectHashTag = MutableLiveData<ArrayList<String>>()
    val currentMyProjectHashTag: LiveData<ArrayList<String>> = _currentMyProjectHashTag

    private val _currentMyProjectImg = MutableLiveData<String?>()
    val currentMyProjectImg: LiveData<String?> = _currentMyProjectImg

    private val _currentDay = MutableLiveData<String?>()
    val currentDay: LiveData<String?> = _currentDay

    fun updateObservingProjectNum(projectNum: Int?){
        _currentObservingProjectNum.value = projectNum
    }

    fun updateCurrentApplyUserId(userId: String){
        _currentApplyUserId.value = userId
    }

    fun updateMyProjectHeader(header: String?){
        _currentMyProjectHeader.value = header
    }

    fun updateMyProjectCategory(category: String?){
        _currentMyProjectCategory.value = category
    }

    fun updateMyProjectSubCategory(subCategory: String?){
        _currentMyProjectSubCategory.value = subCategory
    }

    fun updateMyProjectProgress(progress: String?){
        _currentMyProjectProgress.value = progress
    }

    fun updateMyProjectStartTerm(term: String?){
        _currentMyProjectStartTerm.value = term
    }

    fun updateMyProjectEndTerm(term: String?){
        _currentMyProjectEndTerm.value = term
    }

    fun updateMyProjectDeadLine(deadline: String?){
        _currentMyProjectDeadLine.value = deadline
    }

    fun updateMyProjectContent(content: String?){
        _currentMyProjectContent.value = content
    }

    fun updateMyProjectTotalPerson(person: Int?){
        _currentMyProjectTotalPerson.value = person
    }

    fun updateMyProjectHashTag(hashtag: ArrayList<String>?){
        _currentMyProjectHashTag.value = hashtag
    }

    fun updateMyProjectImg(img: String?){
        _currentMyProjectImg.value = img
    }

    fun updateCurrentDay(day: String?){
        _currentDay.value = day
    }
}