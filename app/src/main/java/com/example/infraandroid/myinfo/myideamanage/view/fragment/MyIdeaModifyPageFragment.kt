package com.example.infraandroid.myinfo.myideamanage.view.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.example.infraandroid.R
import com.example.infraandroid.databinding.FragmentMyInfoProjectModifyBinding
import com.example.infraandroid.myinfo.myideamanage.model.MyProjectViewModel
import com.example.infraandroid.util.BaseFragment

// 2022-02-08 내 정보 들어가서 프로젝트 수정하는 .kt파일 (작성자 : 신승민)

class MyIdeaModifyPageFragment : BaseFragment<FragmentMyInfoProjectModifyBinding>(R.layout.fragment_my_info_project_modify) {
    private lateinit var viewModel : MyProjectViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        activity?.run{
            viewModel = ViewModelProvider(this, ViewModelProvider.NewInstanceFactory())
                .get(MyProjectViewModel::class.java)
        }
    }

    override fun FragmentMyInfoProjectModifyBinding.onCreateView(){
        var startTerm : String? = viewModel.currentMyProjectStartTerm.value
        var endTerm : String? = viewModel.currentMyProjectEndTerm.value
        var deadline : String? = viewModel.currentMyProjectDeadLine.value
        var startYear = startTerm?.substring(0 until 4)
        var startMonth = startTerm?.substring(5 until 7)
        var startDay = startTerm?.substring(8 until 10)
        var endYear = endTerm?.substring(0 until 4)
        var endMonth = endTerm?.substring(5 until 7)
        var endDay = endTerm?.substring(8 until 10)
        var deadlineYear = deadline?.substring(0 until 4)
        var deadlineMonth = deadline?.substring(5 until 7)
        var deadlineDay = deadline?.substring(8 until 10)

        binding.titleEditText.setText(viewModel.currentMyProjectHeader.value)
        binding.numberOfTeamEditText.setText(viewModel.currentMyProjectTotalPerson.value.toString())
        binding.setProjectStartDateTextView.text = "{$startYear}년 {$startMonth}월 {$startDay}일"
        binding.projectEndMakingDateEditTextView.text = "{$endYear}년 {$endMonth}월 {$endDay}일"
        binding.projectEndDateEditTextView.text = "{$deadlineYear}년 {$deadlineMonth}월 {$deadlineDay}일"
        binding.projectContentEditText.setText(viewModel.currentMyProjectContent.value)
        binding.descriptionEditText.setText(viewModel.currentMyProjectProgress.value)
        
    }

    override fun FragmentMyInfoProjectModifyBinding.onViewCreated(){

    }
}