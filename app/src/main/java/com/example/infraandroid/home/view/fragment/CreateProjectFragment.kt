package com.example.infraandroid.home.view.fragment

import android.os.Build
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.RequiresApi
import androidx.core.view.isVisible
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.findNavController
import androidx.navigation.fragment.findNavController
import com.example.infraandroid.R
import com.example.infraandroid.databinding.FragmentCreateProjectBinding
import com.example.infraandroid.home.viewmodel.CreateProjectViewModel
import java.time.LocalDate

class CreateProjectFragment : Fragment() {
    private var mBinding : FragmentCreateProjectBinding? = null
    private var viewModel : CreateProjectViewModel? = null
    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding : FragmentCreateProjectBinding = DataBindingUtil.inflate(
            inflater, R.layout.fragment_create_project, container, false)

        viewModel = ViewModelProvider(this).get(CreateProjectViewModel::class.java)

        binding.createViewModel = viewModel
        binding.lifecycleOwner = this
        binding.fragment = this

        mBinding = binding

        val today : LocalDate = LocalDate.now()
        val year = today.year
        val month = today.monthValue
        val day = today.dayOfMonth

        // 초기 날짜를 오늘 날짜로 세팅
        mBinding?.projectStartDateEditTextView?.text = getString(R.string.today_date).format(year, month, day)
        mBinding?.projectEndDateEditTextView?.text = getString(R.string.today_date).format(year, month, day)
        mBinding?.setProjectStartDateTextView?.text = getString(R.string.today_date).format(year, month, day)
        mBinding?.projectEndMakingDateEditTextView?.text = getString(R.string.today_date).format(year, month, day)
        viewModel!!.updateStartRecruit(today)
        viewModel!!.updateEndRecruit(today)
        viewModel!!.updateStartMaking(today)
        viewModel!!.updateEndMaking(today)

        return mBinding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


        mBinding?.cancelTextView?.setOnClickListener {
            it.findNavController().navigate(R.id.action_createProjectFragment_to_home_fragment)
        }


        mBinding?.editCategoryImageButton?.setOnClickListener {
            it.findNavController().navigate(R.id.action_createProjectFragment_to_createProjectSelectCategory)
        }
    }

    override fun onDestroyView() {
        mBinding = null
        super.onDestroyView()
    }

    // 완료 버튼 눌렀을 때
    fun onClickCompeleteButton(){
        viewModel?.updateProjectTitle(mBinding?.titleEditText?.text.toString())
        viewModel?.updateNumberOfTeam(mBinding?.numberOfTeamEditText?.text.toString().toInt())
        viewModel?.updateProjectContent(mBinding?.projectContentEditText?.text.toString())
        viewModel?.updateProgress(mBinding?.descriptionEditText?.text.toString())
        findNavController().navigate(R.id.action_createProjectFragment_to_home_fragment)
    }

    // 해시태그 추가 버튼 눌렀을 때
    fun onClickAddHashTagButton(){
        if(mBinding?.hashTagEditText?.text.toString() != "")
            viewModel?.updateHashTag(mBinding?.hashTagEditText?.text.toString())
        mBinding?.hashTagEditText?.setText("")
        viewModel?.hashTag?.observe(viewLifecycleOwner, Observer{
            when(it.size){
                1 -> {
                    mBinding?.hashTagOneTextView?.text = it[0]
                    mBinding?.hashTagOneTextView?.isVisible = true
                }
                2 -> {
                    mBinding?.hashTagTwoTextView?.text = it[1]
                    mBinding?.hashTagTwoTextView?.isVisible = true
                }
                3 -> {
                    mBinding?.hashTagThreeTextView?.text = it[2]
                    mBinding?.hashTagThreeTextView?.isVisible = true
                }
                4 -> {
                    mBinding?.hashTagFourTextView?.text = it[3]
                    mBinding?.hashTagFourTextView?.isVisible = true
                }
            }
        })
    }

}