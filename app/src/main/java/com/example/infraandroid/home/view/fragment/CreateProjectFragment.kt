package com.example.infraandroid.home.view.fragment

import android.Manifest
import android.annotation.SuppressLint
import android.app.Activity.RESULT_OK
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
import android.widget.TextView
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.core.view.isGone
import androidx.core.view.isVisible
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.NavArgs
import androidx.navigation.findNavController
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.example.infraandroid.R
import com.example.infraandroid.category.view.fragment.CategoryViewIdeaFragmentArgs
import com.example.infraandroid.databinding.FragmentCreateProjectBinding
import com.example.infraandroid.home.model.ResponseCreateProjectData
import com.example.infraandroid.home.viewmodel.CreateProjectViewModel
import com.example.infraandroid.id.viewmodel.SignUpViewModel.Companion.TAG
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
import java.time.LocalDate
import java.time.Month
import java.util.*


@RequiresApi(Build.VERSION_CODES.O)
class CreateProjectFragment : Fragment() {
    private var mBinding : FragmentCreateProjectBinding? = null
    private var viewModel : CreateProjectViewModel? = null
    private val today : LocalDate = LocalDate.now()
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
        val year = today.year
        val month = today.monthValue
        val day = today.dayOfMonth
        todayString = "${year}-${month}-${day}"
        startTermString = todayString
        endTermString = todayString
        deadlineString = todayString

        viewModel?.updateStartMaking(todayString!!)
        viewModel?.updateEndRecruit(todayString!!)
        viewModel?.updateEndMaking(todayString!!)

        // 초기 날짜를 오늘 날짜로 세팅
        mBinding?.projectStartDateEditTextView?.text = getString(R.string.today_date).format(year, month, day)
        mBinding?.projectEndDateEditTextView?.text = getString(R.string.today_date).format(year, month, day)
        mBinding?.setProjectStartDateTextView?.text = getString(R.string.today_date).format(year, month, day)
        mBinding?.projectEndMakingDateEditTextView?.text = getString(R.string.today_date).format(year, month, day)

        return mBinding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val cal = Calendar.getInstance()
        val projectRecruitEnd = mBinding?.projectEndDateEditTextView
        val projectMakingStart = mBinding?.setProjectStartDateTextView
        val projectMakingEnd = mBinding?.projectEndMakingDateEditTextView
        val addFileButton = mBinding?.addFileTextView
        val args: CreateProjectFragmentArgs by navArgs()

        viewModel?.hashTag?.observe(viewLifecycleOwner, Observer{
            when(it.size){
                0 -> {
                    mBinding?.hashTagOneConstraintLayout?.isVisible = false
                    mBinding?.hashTagTwoConstraintLayout?.isVisible = false
                    mBinding?.hashTagThreeConstraintLayout?.isVisible = false
                    mBinding?.hashTagFourConstraintLayout?.isVisible = false
                    hashTagString = ""
                }
                1 -> {
                    mBinding?.hashTagOneTextView?.text = it[0]
                    mBinding?.hashTagOneConstraintLayout?.isVisible = true
                    mBinding?.hashTagTwoConstraintLayout?.isVisible = false
                    mBinding?.hashTagThreeConstraintLayout?.isVisible = false
                    mBinding?.hashTagFourConstraintLayout?.isVisible = false
                    hashTagString = "\"${it[0]}\""
                }
                2 -> {
                    mBinding?.hashTagTwoTextView?.text = it[1]
                    mBinding?.hashTagOneConstraintLayout?.isVisible = true
                    mBinding?.hashTagTwoConstraintLayout?.isVisible = true
                    mBinding?.hashTagThreeConstraintLayout?.isVisible = false
                    mBinding?.hashTagFourConstraintLayout?.isVisible = false
                    hashTagString = "\"${it[0]}\",\"${it[1]}\""
                }
                3 -> {
                    mBinding?.hashTagThreeTextView?.text = it[2]
                    mBinding?.hashTagOneConstraintLayout?.isVisible = true
                    mBinding?.hashTagTwoConstraintLayout?.isVisible = true
                    mBinding?.hashTagThreeConstraintLayout?.isVisible = true
                    mBinding?.hashTagFourConstraintLayout?.isVisible = false
                    hashTagString = "\"${it[0]}\",\"${it[1]}\",\"${it[2]}\""
                }
                4 -> {
                    mBinding?.hashTagFourTextView?.text = it[3]
                    mBinding?.hashTagOneConstraintLayout?.isVisible = true
                    mBinding?.hashTagTwoConstraintLayout?.isVisible = true
                    mBinding?.hashTagThreeConstraintLayout?.isVisible = true
                    mBinding?.hashTagFourConstraintLayout?.isVisible = true
                    hashTagString = "\"${it[0]}\",\"${it[1]}\",\"${it[2]}\",\"${it[3]}\""
                }
            }
        })

        mBinding?.hashTagOneConstraintLayout?.setOnClickListener {
            viewModel?.deleteHashTag(0)
        }

        mBinding?.hashTagTwoConstraintLayout?.setOnClickListener {
            viewModel?.deleteHashTag(1)
        }

        mBinding?.hashTagThreeConstraintLayout?.setOnClickListener {
            viewModel?.deleteHashTag(2)
        }

        mBinding?.hashTagFourConstraintLayout?.setOnClickListener {
            viewModel?.deleteHashTag(3)
        }

        // 파일 첨부 버튼 눌렀을 때
        addFileButton?.setOnClickListener {
            requestPermission()
        }

        // 프로젝트 모집 끝 눌렀을 때 날짜 선택
        projectRecruitEnd?.setOnClickListener{
            showDatePicker(cal, projectRecruitEnd, 1)
            viewModel?.updateEndRecruit("${cal.get(Calendar.YEAR)}-${cal.get(Calendar.MONTH)+1}-${cal.get(Calendar.DAY_OF_MONTH)}")
        }

        // 예상 제작 시작 기간 눌렀을 때 날짜 선택
        projectMakingStart?.setOnClickListener{
            showDatePicker(cal, projectMakingStart, 2)
            viewModel?.updateStartMaking("${cal.get(Calendar.YEAR)}-${cal.get(Calendar.MONTH)+1}-${cal.get(Calendar.DAY_OF_MONTH)}")
        }

        // 예상 제작 종료 기간 눌렀을 때 날짜 선택
        projectMakingEnd?.setOnClickListener{
            showDatePicker(cal, projectMakingEnd, 3)
            viewModel?.updateEndMaking("${cal.get(Calendar.YEAR)}-${cal.get(Calendar.MONTH)+1}-${cal.get(Calendar.DAY_OF_MONTH)}")
        }

        // 취소 버튼 눌렀을 때 홈 프레그먼트로 이동
        mBinding?.cancelTextView?.setOnClickListener {
            it.findNavController().navigate(R.id.action_createProjectFragment_to_home_fragment)
        }

        // 사진 지우기
        mBinding?.photoImageView?.setOnClickListener {
            mBinding?.addFileTextView?.isVisible = true
            mBinding?.photoImageView?.isGone = true
            mBinding?.photoBackgroundView?.isGone = true
            mBinding?.deletePhotoImageView?.isGone = true
            mediaPath = null
        }

        // 완료 버튼 눌렀을 때 프로젝트 생성 완료
        mBinding?.completeTextView?.setOnClickListener {

            viewModel?.updateProjectTitle(mBinding?.titleEditText?.text.toString())
            viewModel?.updateNumberOfTeam(mBinding?.numberOfTeamEditText?.text.toString())
            viewModel?.updateProjectContent(mBinding?.projectContentEditText?.text.toString())
            viewModel?.updateProgress(mBinding?.descriptionEditText?.text.toString())

            fileToUpload = if (mediaPath!=null){
                val file = File(mediaPath)
                val requestBody = file.asRequestBody("image/jpeg".toMediaTypeOrNull())
                MultipartBody.Part.createFormData("images", file.name, requestBody)
            } else{
                null
            }

            val jsonString = "{\"user_id\" : \""+ InfraApplication.prefs.getString("userId", "null")+"\"," +
                    "\"pj_header\" : \"" + mBinding?.titleEditText?.text.toString() + "\"," +
                    "\"pj_categoryName\" : \""+args.category+"\"," +
                    "\"pj_content\" : \""+ mBinding?.projectContentEditText?.text.toString() +"\"," +
                    "\"pj_subCategoryName\" : \""+args.categoryDetail+"\"," +
                    "\"pj_progress\" : \""+ mBinding?.descriptionEditText?.text.toString() +"\"," +
                    "\"pj_startTerm\" : "+ startTermString +"," +
                    "\"pj_endTerm\" : "+ endTermString +"," +
                    "\"pj_deadline\" : "+ deadlineString +"," +
                    "\"pj_totalPerson\" : "+ mBinding?.numberOfTeamEditText?.text.toString() +"," +
                    "\"hashtag\" : ["+ hashTagString +"]}"

            val jsonList = jsonString.toRequestBody("text/plain".toMediaTypeOrNull())
            val call : Call<ResponseCreateProjectData> = ServiceCreator.createProjectService
                .postCreateProject(InfraApplication.prefs.getString("jwt", "null"), jsonList , fileToUpload)

            call.enqueue(object : Callback<ResponseCreateProjectData> {
                override fun onResponse(
                    call: Call<ResponseCreateProjectData>,
                    response: Response<ResponseCreateProjectData>
                ) {
                    if(response.isSuccessful){
                        when(response.body()?.code){
                            1000 -> Log.d(TAG, "onResponse: 프로젝트 등록 성공")
                            else -> Log.d(TAG, "onResponse: " + response.body()?.code)
                        }
                    } else{
                        Log.d(TAG, "onResponse: 뭐가 문제냐..")
                    }
                }
                override fun onFailure(call: Call<ResponseCreateProjectData>, t: Throwable) {
                    Log.d(TAG, "onFailure: $t")
                }
            })
            findNavController().navigate(R.id.action_createProjectFragment_to_home_fragment)
        }


        mBinding?.editCategoryImageButton?.setOnClickListener {
            it.findNavController().navigate(R.id.action_createProjectFragment_to_createProjectSelectCategory)
        }
    }

    @SuppressLint("Range")
    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        if(requestCode == OPEN_GALLERY && resultCode == RESULT_OK && data!=null){
            val selectedImage = data.data
            val photoUri = data.data
            try {
                photoUri?.let {
                    var bitmap : Bitmap ?= null
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
                    mBinding?.photoImageView?.isVisible = true
                    mBinding?.addFileTextView?.isGone = true
                    mBinding?.photoBackgroundView?.isVisible = true
                    mBinding?.deletePhotoImageView?.isVisible = true
                    mBinding?.photoImageView?.setImageBitmap(bitmap)
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

    override fun onDestroyView() {
        mBinding = null
        super.onDestroyView()
    }


    // 해시태그 추가 버튼 눌렀을 때
    fun onClickAddHashTagButton(){
        if(mBinding?.hashTagEditText?.text.toString() != "")
            viewModel?.updateHashTag(mBinding?.hashTagEditText?.text.toString())
        mBinding?.hashTagEditText?.setText("")
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