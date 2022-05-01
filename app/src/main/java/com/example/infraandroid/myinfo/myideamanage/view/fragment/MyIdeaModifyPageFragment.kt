package com.example.infraandroid.myinfo.myideamanage.view.fragment

import android.Manifest
import android.annotation.SuppressLint
import android.app.Activity
import android.app.DatePickerDialog
import android.content.Intent
import android.content.pm.PackageManager
import android.database.Cursor
import android.graphics.Bitmap
import android.graphics.ImageDecoder
import android.net.Uri
import android.os.Build
import android.os.Bundle
import android.provider.MediaStore
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import android.widget.TextView
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.core.view.isGone
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.findNavController
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.bumptech.glide.Glide
import com.example.infraandroid.R
import com.example.infraandroid.databinding.FragmentMyInfoProjectModifyBinding
import com.example.infraandroid.home.model.ResponseCreateProjectData
import com.example.infraandroid.home.view.fragment.CreateProjectFragmentArgs
import com.example.infraandroid.id.viewmodel.SignUpViewModel
import com.example.infraandroid.id.viewmodel.SignUpViewModel.Companion.TAG
import com.example.infraandroid.myinfo.myideamanage.model.MyProjectViewModel
import com.example.infraandroid.myinfo.myideamanage.model.ResponseModifyProjectData
import com.example.infraandroid.util.BaseFragment
import com.example.infraandroid.util.InfraApplication
import com.example.infraandroid.util.ServiceCreator
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.MultipartBody
import okhttp3.RequestBody.Companion.asRequestBody
import okhttp3.RequestBody.Companion.toRequestBody
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.io.File
import java.io.IOException
import java.net.URL
import java.time.LocalDate
import java.util.*

// 2022-02-08 내 정보 들어가서 프로젝트 수정하는 .kt파일 (작성자 : 신승민)

class MyIdeaModifyPageFragment : BaseFragment<FragmentMyInfoProjectModifyBinding>(R.layout.fragment_my_info_project_modify) {
    private lateinit var viewModel : MyProjectViewModel
    private val OPEN_GALLERY = 100
    val PERMISSIONS_REQUEST_CODE = 101
    var REQUIRED_PERMISSIONS = arrayOf<String>( Manifest.permission.READ_EXTERNAL_STORAGE)
    private var mediaPath : String ?= null
    private var fileToUpload : MultipartBody.Part? = null
    private var hashTagString : String? = ""
    private var todayString : String? = null
    private var startTermString : String? = null
    private var endTermString : String? = null
    private var deadlineString : String? = null
    private var category : String? = null
    private var categoryDetail : String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        activity?.run{
            viewModel = ViewModelProvider(this, ViewModelProvider.NewInstanceFactory())
                .get(MyProjectViewModel::class.java)
        }
    }

    @RequiresApi(Build.VERSION_CODES.O)
    override fun FragmentMyInfoProjectModifyBinding.onCreateView(){
        val today : LocalDate = LocalDate.now()
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

        startTermString = "\"${startYear}-${startMonth}-${startDay}\""
        endTermString = "\"${endYear}-${endMonth}-${endDay}\""
        deadlineString = "\"${deadlineYear}-${deadlineMonth}-${deadlineDay}\""

        if(startMonth?.toInt()!! < 10){
            startMonth = startMonth.substring(1,2)
        }

        if(startDay?.toInt()!! < 10){
            startDay = startDay.substring(1,2)
        }

        if(endMonth?.toInt()!! < 10){
            endMonth = endMonth.substring(1,2)
        }

        if(endDay?.toInt()!! < 10){
            endDay = endDay.substring(1,2)
        }

        if(deadlineMonth?.toInt()!! < 10){
            deadlineMonth = deadlineMonth.substring(1,2)
        }

        if(deadlineDay?.toInt()!! < 10){
            deadlineDay = deadlineDay.substring(1,2)
        }


        // view model에 저장한 정보 가져오기
        binding.projectStartDateEditTextView.text = "${today.year}년 ${today.monthValue}월 ${today.dayOfMonth}일"
        binding.titleEditText.setText(viewModel.currentMyProjectHeader.value)
        binding.numberOfTeamEditText.setText(viewModel.currentMyProjectTotalPerson.value.toString())
        binding.setProjectStartDateTextView.text = "${startYear}년 ${startMonth}월 ${startDay}일"
        binding.projectEndMakingDateEditTextView.text = "${endYear}년 ${endMonth}월 ${endDay}일"
        binding.projectEndDateEditTextView.text = "${deadlineYear}년 ${deadlineMonth}월 ${deadlineDay}일"
        binding.projectContentEditText.setText(viewModel.currentMyProjectContent.value)
        binding.descriptionEditText.setText(viewModel.currentMyProjectProgress.value)
        category = viewModel.currentMyProjectCategory.value
        categoryDetail = viewModel.currentMyProjectSubCategory.value

        val hashtag = viewModel.currentMyProjectHashTag.value
        when(hashtag?.size){
            0 -> {
                binding.hashTagOneConstraintLayout.isVisible = false
                binding.hashTagTwoConstraintLayout.isVisible = false
                binding.hashTagThreeConstraintLayout.isVisible = false
                binding.hashTagFourConstraintLayout.isVisible = false
                hashTagString = ""
            }
            1 -> {
                binding.hashTagOneTextView.text = hashtag[0]
                binding.hashTagOneConstraintLayout.isVisible = true
                binding.hashTagTwoConstraintLayout.isVisible = false
                binding.hashTagThreeConstraintLayout.isVisible = false
                binding.hashTagFourConstraintLayout.isVisible = false
                hashTagString = "\"${hashtag[0]}\""
            }
            2 -> {
                binding.hashTagTwoTextView.text = hashtag[1]
                binding.hashTagOneConstraintLayout.isVisible = true
                binding.hashTagTwoConstraintLayout.isVisible = true
                binding.hashTagThreeConstraintLayout.isVisible = false
                binding.hashTagFourConstraintLayout.isVisible = false
                hashTagString = "\"${hashtag[0]}\",\"${hashtag[1]}\""
            }
            3 -> {
                binding.hashTagThreeTextView.text = hashtag[2]
                binding.hashTagOneConstraintLayout.isVisible = true
                binding.hashTagTwoConstraintLayout.isVisible = true
                binding.hashTagThreeConstraintLayout.isVisible = true
                binding.hashTagFourConstraintLayout.isVisible = false
                hashTagString = "\"${hashtag[0]}\",\"${hashtag[1]}\",\"${hashtag[2]}\""
            }
            4 -> {
                binding.hashTagFourTextView.text = hashtag[3]
                binding.hashTagOneConstraintLayout.isVisible = true
                binding.hashTagTwoConstraintLayout.isVisible = true
                binding.hashTagThreeConstraintLayout.isVisible = true
                binding.hashTagFourConstraintLayout.isVisible = true
                hashTagString = "\"${hashtag[0]}\",\"${hashtag[1]}\",\"${hashtag[2]}\",\"${hashtag[3]}\""
            }
        }

        if(viewModel.currentMyProjectImg.value != null){
            binding.photoImageView.isVisible = true
            binding.addFileTextView.isGone = true
            binding.photoBackgroundView.isVisible = true
            binding.deletePhotoImageView.isVisible = true
            Glide.with(requireActivity())
                .load(viewModel.currentMyProjectImg.value)
                .into(binding.photoImageView)

            val file = File(viewModel.currentMyProjectImg.value)
            val requestBody = file.asRequestBody("image/jpeg".toMediaTypeOrNull())
            fileToUpload = MultipartBody.Part.createFormData("images", file.name, requestBody)
        }
    }

    override fun FragmentMyInfoProjectModifyBinding.onViewCreated(){

        val cal = Calendar.getInstance()
        val projectRecruitEnd = binding.projectEndDateEditTextView
        val projectMakingStart = binding.setProjectStartDateTextView
        val projectMakingEnd = binding.projectEndMakingDateEditTextView
        val addFileButton = binding.addFileTextView

        viewModel.currentMyProjectHashTag.observe(viewLifecycleOwner, Observer{
            when(it.size){
                0 -> {
                    binding.hashTagOneConstraintLayout.isVisible = false
                    binding.hashTagTwoConstraintLayout.isVisible = false
                    binding.hashTagThreeConstraintLayout.isVisible = false
                    binding.hashTagFourConstraintLayout.isVisible = false
                    hashTagString = ""
                }
                1 -> {
                    binding.hashTagOneTextView.text = it[0]
                    binding.hashTagOneConstraintLayout.isVisible = true
                    binding.hashTagTwoConstraintLayout.isVisible = false
                    binding.hashTagThreeConstraintLayout.isVisible = false
                    binding.hashTagFourConstraintLayout.isVisible = false
                    hashTagString = "\"${it[0]}\""
                }
                2 -> {
                    binding.hashTagTwoTextView.text = it[1]
                    binding.hashTagOneConstraintLayout.isVisible = true
                    binding.hashTagTwoConstraintLayout.isVisible = true
                    binding.hashTagThreeConstraintLayout.isVisible = false
                    binding.hashTagFourConstraintLayout.isVisible = false
                    hashTagString = "\"${it[0]}\",\"${it[1]}\""
                }
                3 -> {
                    binding.hashTagThreeTextView.text = it[2]
                    binding.hashTagOneConstraintLayout.isVisible = true
                    binding.hashTagTwoConstraintLayout.isVisible = true
                    binding.hashTagThreeConstraintLayout.isVisible = true
                    binding.hashTagFourConstraintLayout.isVisible = false
                    hashTagString = "\"${it[0]}\",\"${it[1]}\",\"${it[2]}\""
                }
                4 -> {
                    binding.hashTagFourTextView.text = it[3]
                    binding.hashTagOneConstraintLayout.isVisible = true
                    binding.hashTagTwoConstraintLayout.isVisible = true
                    binding.hashTagThreeConstraintLayout.isVisible = true
                    binding.hashTagFourConstraintLayout.isVisible = true
                    hashTagString = "\"${it[0]}\",\"${it[1]}\",\"${it[2]}\",\"${it[3]}\""
                }
            }
        })

        binding.hashTagOneConstraintLayout.setOnClickListener {
            viewModel.deleteMyProjectHashTag(0)
        }

        binding.hashTagTwoConstraintLayout.setOnClickListener {
            viewModel.deleteMyProjectHashTag(1)
        }

        binding.hashTagThreeConstraintLayout.setOnClickListener {
            viewModel.deleteMyProjectHashTag(2)
        }

        binding.hashTagFourConstraintLayout.setOnClickListener {
            viewModel.deleteMyProjectHashTag(3)
        }

        // 파일 첨부 버튼 눌렀을 때
        addFileButton.setOnClickListener {
            requestPermission()
        }

        // 프로젝트 모집 끝 눌렀을 때 날짜 선택
        projectRecruitEnd.setOnClickListener{
            showDatePicker(cal, projectRecruitEnd, 1)
            viewModel.updateMyProjectDeadLine("${cal.get(Calendar.YEAR)}-${cal.get(Calendar.MONTH)+1}-${cal.get(Calendar.DAY_OF_MONTH)}")
        }

        // 예상 제작 시작 기간 눌렀을 때 날짜 선택
        projectMakingStart.setOnClickListener{
            showDatePicker(cal, projectMakingStart, 2)
            viewModel.updateMyProjectStartTerm("${cal.get(Calendar.YEAR)}-${cal.get(Calendar.MONTH)+1}-${cal.get(Calendar.DAY_OF_MONTH)}")
        }

        // 예상 제작 종료 기간 눌렀을 때 날짜 선택
        projectMakingEnd.setOnClickListener{
            showDatePicker(cal, projectMakingEnd, 3)
            viewModel.updateMyProjectEndTerm("${cal.get(Calendar.YEAR)}-${cal.get(Calendar.MONTH)+1}-${cal.get(Calendar.DAY_OF_MONTH)}")
        }

        // 취소 버튼 눌렀을 때 홈 프레그먼트로 이동
        binding.cancelTextView.setOnClickListener {
            it.findNavController().navigate(R.id.action_myInfoProjectModifyFragment_to_myInfoMyIdeaFragment)
        }

        // 사진 지우기
        binding.photoImageView.setOnClickListener {
            binding.addFileTextView.isVisible = true
            binding.photoImageView.isGone = true
            binding.photoBackgroundView.isGone = true
            binding.deletePhotoImageView.isGone = true
            mediaPath = null
            fileToUpload = null
            viewModel.deleteProjectPhoto()
        }

        // 완료 버튼 눌렀을 때 프로젝트 수정 완료
        binding.completeTextView.setOnClickListener {
            if(mediaPath!=null){
                val file = File(mediaPath)
                val requestBody = file.asRequestBody("image/jpeg".toMediaTypeOrNull())
                fileToUpload = MultipartBody.Part.createFormData("images", file.name, requestBody)
            }
            else{
                fileToUpload = null
            }

            Log.d(TAG, "onViewCreated: 수정완료버튼${viewModel.currentMyProjectSubCategory.value}" )

            val jsonString = "{" + "\"pj_num\" : " + viewModel.currentObservingProjectNum.value + ","+
                    "\"user_id\" : \""+ InfraApplication.prefs.getString("userId", "null")+"\"," +
                    "\"pj_header\" : \"" + binding.titleEditText.text.toString() + "\"," +
                    "\"pj_categoryName\" : \""+viewModel.currentMyProjectCategory.value+"\"," +
                    "\"pj_content\" : \""+ binding.projectContentEditText.text.toString() +"\"," +
                    "\"pj_subCategoryNum\" : \""+viewModel.currentMyProjectSubCategory.value+"\"," +
                    "\"pj_progress\" : \""+ binding.descriptionEditText.text.toString() +"\"," +
                    "\"pj_startTerm\" : "+ startTermString +"," +
                    "\"pj_endTerm\" : "+ endTermString +"," +
                    "\"pj_deadline\" : "+ deadlineString +"," +
                    "\"pj_totalPerson\" : "+ binding.numberOfTeamEditText.text.toString() +"," +
                    "\"hashtag\" : ["+ hashTagString +"]," +
                    "\"pjPhoto\" : \"" + viewModel.currentProjectPhotoStatus.value.toString() + "\"}"

            val jsonList = jsonString.toRequestBody("text/plain".toMediaTypeOrNull())
            val call : Call<ResponseModifyProjectData> = ServiceCreator.myProjectService
                .modifyProject(InfraApplication.prefs.getString("jwt", "null"), InfraApplication.prefs.getString("refreshToken", "null").toInt(),
                    jsonList, fileToUpload)

            call.enqueue(object : Callback<ResponseModifyProjectData> {
                override fun onResponse(
                    call: Call<ResponseModifyProjectData>,
                    response: Response<ResponseModifyProjectData>
                ) {
                    if(response.isSuccessful){
                        when(response.body()?.code){
                            1000 -> {
                                Log.d(TAG, "onResponse: 프로젝트 수정 성공")
                                findNavController().navigate(R.id.action_myInfoProjectModifyFragment_to_myInfoMyIdeaFragment)
                            }
                            else -> Log.d(TAG, "onResponse: " + response.body()?.code)
                        }
                    } else{
                        Log.d(TAG, "onResponse: 뭐가 문제냐..")
                    }
                }
                override fun onFailure(call: Call<ResponseModifyProjectData>, t: Throwable) {
                    Log.d(TAG, "onFailure: $t")
                }
            })
        }


        binding.editCategoryImageButton.setOnClickListener {
            it.findNavController().navigate(R.id.action_myInfoProjectModifyFragment_to_myIdeaCategoryModifyFragment)
        }
    }

    @SuppressLint("Range")
    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        if(requestCode == OPEN_GALLERY && resultCode == Activity.RESULT_OK && data!=null){
            val selectedImage = data.data
            Log.d(TAG, "onActivityResult: ${selectedImage.toString()}")
            val photoUri = data.data
            try {
                photoUri?.let {
                    var bitmap : Bitmap?= null
                    bitmap = if(Build.VERSION.SDK_INT < 28) {
                        MediaStore.Images.Media.getBitmap(
                            requireActivity().contentResolver,
                            photoUri
                        )
                        //imageView.setImageBitmap(bitmap)
                    } else {
                        val source = ImageDecoder.createSource(requireActivity().contentResolver, photoUri)
                        ImageDecoder.decodeBitmap(source)
                        //imageView.setImageBitmap(bitmap)
                    }
                    binding.photoImageView.isVisible = true
                    binding.addFileTextView.isGone = true
                    binding.photoBackgroundView.isVisible = true
                    binding.deletePhotoImageView.isVisible = true
                    binding.photoImageView.setImageBitmap(bitmap)
                }
            }
            catch (e: IOException) {
                e.printStackTrace()
            }

            val cursor: Cursor = requireActivity().contentResolver.query(
                Uri.parse(selectedImage.toString()),
                null,
                null,
                null,
                null
            )!!
            cursor.moveToFirst()
            mediaPath = cursor.getString(cursor.getColumnIndex(MediaStore.MediaColumns.DATA))
            viewModel.updateProjectPhoto()
        }
        else{
            Toast.makeText(requireActivity(), "이미지 업로드 실패", Toast.LENGTH_SHORT).show()
        }
    }

    override fun onRequestPermissionsResult(
        requestCode: Int, permissions: Array<out String>, grantResults: IntArray
    ) {
        when(requestCode){
            PERMISSIONS_REQUEST_CODE -> {
                if(grantResults.isNotEmpty() && grantResults[0] == PackageManager.PERMISSION_GRANTED){
                    openGallery()
                }else{
                    //권한 거부됨
                }
                return
            }
        }
    }


    // 해시태그 추가 버튼 눌렀을 때
    fun onClickAddHashTagButton(){
        if(binding.hashTagEditText.text.toString() != "")
            viewModel.updateMyProjectHashTag(binding.hashTagEditText.text.toString())
        binding.hashTagEditText.setText("")
    }

    // 날짜 선택하는 datepicker
    fun showDatePicker(cal : Calendar, view : TextView, code : Int){
        lateinit var changeMonth: String
        lateinit var changeDay: String
        val datePickerDialog = DatePickerDialog.OnDateSetListener { _, year, month, day->
            cal.set(Calendar.YEAR, year)
            cal.set(Calendar.MONTH, month)
            cal.set(Calendar.DAY_OF_MONTH, day)
            view.text = "${cal.get(Calendar.YEAR)}년 ${cal.get(Calendar.MONTH)+1}월 ${cal.get(Calendar.DAY_OF_MONTH)}일"
            changeMonth = if(cal.get(Calendar.MONTH)+1 < 10){
                "0${cal.get(Calendar.MONTH)+1}"
            }else{
                "${cal.get(Calendar.MONTH)+1}"
            }
            changeDay = if(cal.get(Calendar.DAY_OF_MONTH) < 10){
                "0${cal.get(Calendar.DAY_OF_MONTH)}"
            }else{
                "${cal.get(Calendar.DAY_OF_MONTH)}"
            }
            when(code){
                1->  deadlineString = "\"${cal.get(Calendar.YEAR)}-${changeMonth}-${changeDay}\""
                2->  startTermString = "\"${cal.get(Calendar.YEAR)}-${changeMonth}-${changeDay}\""
                3->  endTermString = "\"${cal.get(Calendar.YEAR)}-${changeMonth}-${changeDay}\""
            }
        }
        DatePickerDialog(requireContext(), datePickerDialog, cal.get(Calendar.YEAR),cal.get(Calendar.MONTH),cal.get(Calendar.DAY_OF_MONTH)).show()
    }

    // 갤러리를 여는 함수
    private fun openGallery() {
        val intent = Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI)
        startActivityForResult(intent, OPEN_GALLERY)
    }

    // 권한 요청하는 함수
    private fun requestPermission(){
        var permissionCheck = ContextCompat.checkSelfPermission(requireActivity(), Manifest.permission.READ_EXTERNAL_STORAGE)
        if(permissionCheck != PackageManager.PERMISSION_GRANTED){
            //설명이 필요한지
            if(ActivityCompat.shouldShowRequestPermissionRationale(requireActivity(), Manifest.permission.READ_EXTERNAL_STORAGE)){
                //설명 필요 (사용자가 요청을 거부한 적이 있음)
                ActivityCompat.requestPermissions(requireActivity(), REQUIRED_PERMISSIONS, PERMISSIONS_REQUEST_CODE )
            }else{
                //설명 필요하지 않음
                ActivityCompat.requestPermissions(requireActivity(), REQUIRED_PERMISSIONS, PERMISSIONS_REQUEST_CODE )
            }
        }else{
            //권한 허용하여 갤러리로 이동
            openGallery()
        }
    }

}