package com.example.infraandroid.home.data

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import java.time.LocalDate

class CreateProjectViewModel : ViewModel() {
    private val hashTagArrayList = ArrayList<String>()

    private val _currentProjectTitle = MutableLiveData<String>()
    val projectTitle: LiveData<String> = _currentProjectTitle

    fun updateProjectTitle(input: String){
        _currentProjectTitle.value = input
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

    private val _currentNumberOfTeam = MutableLiveData<Int>(0)
    val numberOfTeam: LiveData<Int> = _currentNumberOfTeam

    fun updateNumberOfTeam(input: Int){
        _currentNumberOfTeam.value = input
    }

    private val _currentStartRecruit = MutableLiveData<LocalDate>()
    val startRecruit: LiveData<LocalDate> = _currentStartRecruit

    fun updateStartRecruit(input: LocalDate){
        _currentStartRecruit.value = input
    }

    private val _currentEndRecruit = MutableLiveData<LocalDate>()
    val endRecruit: LiveData<LocalDate> = _currentEndRecruit

    fun updateEndRecruit(input: LocalDate){
        _currentEndRecruit.value = input
    }

    private val _currentProjectContent = MutableLiveData<String>()
    val projectContent: LiveData<String> = _currentProjectContent

    fun updateProjectContent(input: String){
        _currentProjectContent.value = input
    }

    private val _currentStartMaking = MutableLiveData<LocalDate>()
    val startMaking: LiveData<LocalDate> = _currentStartMaking

    fun updateStartMaking(input: LocalDate){
        _currentStartMaking.value = input
    }

    private val _currentEndMaking = MutableLiveData<LocalDate>()
    val endMaking: LiveData<LocalDate> = _currentEndMaking

    fun updateEndMaking(input: LocalDate){
        _currentEndMaking.value = input
    }

    private val _currentProgress = MutableLiveData<String>()
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
}