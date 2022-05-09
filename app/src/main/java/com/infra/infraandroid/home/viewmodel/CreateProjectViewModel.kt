package com.infra.infraandroid.home.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class CreateProjectViewModel : ViewModel() {
    private val hashTagArrayList = ArrayList<String>()

    private val _currentProjectTitle = MutableLiveData<String>("제목")
    val projectTitle: LiveData<String> = _currentProjectTitle

    fun updateProjectTitle(input: String){
        _currentProjectTitle.value = input
    }

    private val _currentProjectName = MutableLiveData<String>("이름")
    val projectName: LiveData<String> = _currentProjectName

    fun updateProjectName(input: String){
        _currentProjectName.value = input
    }

    private val _currentProjectCategory = MutableLiveData<String>("스터디")
    val projectCategory: LiveData<String> = _currentProjectCategory

    fun updateProjectCategory(input: String){
        _currentProjectCategory.value = input
    }

    private val _currentProjectDetailCategory = MutableLiveData<String>("토익")
    val projectDetailCategory: LiveData<String> = _currentProjectDetailCategory

    fun updateProjectDetailCategory(input: String){
        _currentProjectDetailCategory.value = input
    }

    private val _currentNumberOfTeam = MutableLiveData<String>("0")
    val numberOfTeam: LiveData<String> = _currentNumberOfTeam

    fun updateNumberOfTeam(input: String){
        _currentNumberOfTeam.value = input
    }

    private val _currentStartRecruit = MutableLiveData<String>()
    val startRecruit: LiveData<String> = _currentStartRecruit

    fun updateStartRecruit(input: String){
        _currentStartRecruit.value = input
    }

    private val _currentEndRecruit = MutableLiveData<String>()
    val endRecruit: LiveData<String> = _currentEndRecruit

    fun updateEndRecruit(input: String){
        _currentEndRecruit.value = input
    }

    private val _currentProjectContent = MutableLiveData<String>("내용")
    val projectContent: LiveData<String> = _currentProjectContent

    fun updateProjectContent(input: String){
        _currentProjectContent.value = input
    }

    private val _currentStartMaking = MutableLiveData<String>()
    val startMaking: LiveData<String> = _currentStartMaking

    fun updateStartMaking(input: String){
        _currentStartMaking.value = input
    }

    private val _currentEndMaking = MutableLiveData<String>()
    val endMaking: LiveData<String> = _currentEndMaking

    fun updateEndMaking(input: String){
        _currentEndMaking.value = input
    }

    private val _currentProgress = MutableLiveData<String>("진행사항")
    val progress: LiveData<String> = _currentProgress

    fun updateProgress(input: String){
        _currentProgress.value = input
    }

    private val _currentHashTag = MutableLiveData<ArrayList<String>>()
    val hashTag : LiveData<ArrayList<String>> = _currentHashTag

    fun updateHashTag(input: String){
        if(hashTagArrayList.size < 4){
            hashTagArrayList.add(input)
            _currentHashTag.value = hashTagArrayList
        }
    }

    fun deleteHashTag(index: Int){
        if(hashTagArrayList.size>0) {
            hashTagArrayList.removeAt(index)
            _currentHashTag.value = hashTagArrayList
        }
    }
}