package com.example.infraandroid.myinfo.myinfomodify.view

import android.content.ContentValues.TAG
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import android.widget.EditText
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.widget.AppCompatButton
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.view.isVisible
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.findNavController
import com.bumptech.glide.Glide
import com.example.infraandroid.R
import com.example.infraandroid.databinding.FragmentMyInfoModifyBinding
import com.example.infraandroid.myinfo.myideamanage.model.MyProjectViewModel
import com.example.infraandroid.myinfo.myinfomodify.model.RequestNicknameCheckData
import com.example.infraandroid.myinfo.myinfomodify.model.ResponseModifyMyInfoData
import com.example.infraandroid.myinfo.myinfomodify.model.ResponseNicknameCheckData
import com.example.infraandroid.myinfo.myinfomodify.model.ResponseViewMyInfoData
import com.example.infraandroid.myinfo.myinfomodify.viewmodel.MyInfoViewModel
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

//내 정보 > 내 정보 .kt
class MyInfoModifyFragment : BaseFragment<FragmentMyInfoModifyBinding>(R.layout.fragment_my_info_modify) {
    /*private var mBinding : FragmentMyInfoModifyBinding? = null*/
    private lateinit var viewModel : MyInfoViewModel
    private var isChecked = false
    private var profileImgStatus : String = "등록"
    private var userNickname : String ?= null
    private var userPrPhoto : String ?= null
    private var fileToUpload : MultipartBody.Part? = null

    override fun FragmentMyInfoModifyBinding.onCreateView() {
        binding.inputNicknameEditText.text = InfraApplication.prefs.getString("userNickName", "null")
        activity?.run{
            viewModel = ViewModelProvider(this, ViewModelProvider.NewInstanceFactory())
                .get(MyInfoViewModel::class.java)
        }

        if(viewModel.currentImgBitmap.value==null){
            Glide.with(this@MyInfoModifyFragment)
                .load(InfraApplication.prefs.getString("userProfileImg", "null"))
                .circleCrop()
                .error(R.drawable.user_photo)
                .into(binding.myInfoModifyUserProfileIv)
        }
        else{
            Glide.with(this@MyInfoModifyFragment)
                .load(viewModel.currentImgBitmap.value)
                .circleCrop()
                .error(R.drawable.user_photo)
                .into(binding.myInfoModifyUserProfileIv)
        }
    }

    override fun FragmentMyInfoModifyBinding.onViewCreated() {

        val myInfoModifyBackButton = binding.myInfoModifyBackButton
        val myInfoModifyUserProfileLayout = binding.myInfoModifyUserProfileLayout
//        val overlapCheckButton = binding.overlapCheckButton as AppCompatButton
        val modifyCompletedButton = binding.modifyCompletedButton as TextView
//        val doNotUseThisNicknameTextView = binding.doNotUseThisNicknameTextView as TextView
//        val canUserIcon = binding.canUseIconImageView

        //내 정보 보기 서버 연결
//        val viewcall: Call<ResponseViewMyInfoData> = ServiceCreator.myinfoService
//            .viewMyInfo(InfraApplication.prefs.getString("jwt","null"),
//                InfraApplication.prefs.getString("userId","null"))
//
//        viewcall.enqueue(object : Callback<ResponseViewMyInfoData> {
//            override fun onResponse(
//                call: Call<ResponseViewMyInfoData>,
//                response: Response<ResponseViewMyInfoData>
//            ) {
//                if(response.isSuccessful){
//                    when(response.body()?.code){
//                        1000 -> {
//                            binding.inputNicknameEditText.setText(response.body()?.result?.user_nickname)
//
//                            Glide.with(this@MyInfoModifyFragment)
//                                .load(response.body()?.result?.user_prPhoto)
//                                .circleCrop()
//                                .error(R.drawable.user_photo)
//                                .into(binding.myInfoModifyUserProfileIv)
//                        }
//                    }
//                }
//
//            }
//
//            override fun onFailure(call: Call<ResponseViewMyInfoData>, t: Throwable) {
//                Log.d("TAG","Failed : $t")
//            }
//
//        } )

        //닉네임 중복 서버 연결
//        overlapCheckButton.setOnClickListener {
//            val requestNicknameCheckData = RequestNicknameCheckData(
//                userNickname = inputNicknameEditText.text.toString(),
//            )
//
//            val checkcall: Call<ResponseNicknameCheckData> = ServiceCreator.nicknameDoubleCheckService
//                .doublecheck(requestNicknameCheckData)
//            checkcall.enqueue(object : Callback<ResponseNicknameCheckData>{
//                override fun onResponse(
//                    call: Call<ResponseNicknameCheckData>,
//                    response: Response<ResponseNicknameCheckData>
//                ) {
//                    if(response.isSuccessful){
//                        val data = response.body()?.code
//                        if(data==1000) {
//                            isChecked = true
//                            doNotUseThisNicknameTextView.isVisible = false
//                            canUserIcon.isVisible = true
//                            inputNicknameEditText.setBackgroundResource(R.drawable.can_use_this_id_background)
//                        }
//                        if(data==3104){
//                            doNotUseThisNicknameTextView.isVisible = true
//                            canUserIcon.isVisible = false
//                            inputNicknameEditText.setBackgroundResource(R.drawable.double_check_id_background)
//                        }
//                    }
//                }
//
//                override fun onFailure(call: Call<ResponseNicknameCheckData>, t: Throwable) {
//
//                }
//
//            })
//
//        }


        binding.modifyCompletedButton.setOnClickListener {
            fileToUpload = if (viewModel.currentImg.value != null){
                val file = File(viewModel.currentImg.value)
                val requestBody = file.asRequestBody("image/jpeg".toMediaTypeOrNull())
                MultipartBody.Part.createFormData("images", file.name, requestBody)
            } else{
                null
            }

            //내 정보 수정 서버 연결
            val modifycall: Call<ResponseModifyMyInfoData> = ServiceCreator.myinfoService
                .modifyMyInfo(InfraApplication.prefs.getString("jwt", "null"), InfraApplication.prefs.getString("userId", "null"),
                    InfraApplication.prefs.getString("userNickName", "null").toRequestBody("text/plain".toMediaTypeOrNull()), viewModel.currentProfileImgStatus.value.toString().toRequestBody("text/plain".toMediaTypeOrNull()),
                    fileToUpload)
            modifycall.enqueue(object : Callback<ResponseModifyMyInfoData> {
                override fun onResponse(
                    call: Call<ResponseModifyMyInfoData>,
                    response: Response<ResponseModifyMyInfoData>
                ) {
                    if(response.isSuccessful){
                        when(response.body()?.code){
                            1000->{
                                Log.d(TAG, "onResponse: 성공")
                            }
                            else->{
                                Log.d(TAG, "onResponse: 실패${response.body()?.code}")
                            }
                        }
                    }
                }

                override fun onFailure(call: Call<ResponseModifyMyInfoData>, t: Throwable) {
                    Log.d("TAG","Failed : $t")
                }

            })

            it.findNavController().navigate(R.id.action_my_info_modify_fragment_to_my_info_fragment)
        }

        //프로필 사진 추가 바텀싵
        val bottomSheetDialogFragment = MyInfoPhotoMoreMenuBottomSheetFragment()
        myInfoModifyUserProfileLayout.setOnClickListener {
            activity?.supportFragmentManager?.let { it1 -> bottomSheetDialogFragment.show(it1, bottomSheetDialogFragment.tag) }
        }

        // 뒤로가기 버튼 눌렀을때
        myInfoModifyBackButton.setOnClickListener {
            it.findNavController().navigate(R.id.action_my_info_modify_fragment_to_my_info_fragment)
        }

//        //닉네임 중복 버튼 활성화 설정
//        inputNicknameEditText.addTextChangedListener(object: TextWatcher{
//            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
//                overlapCheckButton.isEnabled = false
//            }
//
//            override fun afterTextChanged(p0: Editable?) {
//                /*inputNicknameEditText.length() < 12 && inputNicknameEditText.length()>0*/
//                if (inputNicknameEditText.length() < 12) {
//                    overlapCheckButton.isEnabled = true
//                } else {
//                    //Toast.makeText(requireActivity(), "12자 이하로 입력해주세요.", Toast.LENGTH_SHORT).show()
//                }
//            }
//
//            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
//                //얘네는 나중에 수정 필요
//                if (inputNicknameEditText.length() < 12) {
//                    overlapCheckButton.isEnabled = true
//                } else {
//                    Toast.makeText(requireActivity(), "12자 이하로 입력해주세요.", Toast.LENGTH_SHORT).show()
//                }
//            }
//        })

        //수정 완료 버튼 활성화 설정
//        inputNicknameEditText.addTextChangedListener(object: TextWatcher{
//            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
//                modifyCompletedButton.isEnabled = false
//            }
//
//            override fun afterTextChanged(p0: Editable?) {
//                /*inputNicknameEditText.length() < 12 && inputNicknameEditText.length()>0*/
//                if (inputNicknameEditText.length() < 12 && isChecked) {
//                    modifyCompletedButton.isEnabled = true
//                } else {
//                    Toast.makeText(requireActivity(), "12자 이하로 입력해주세요.", Toast.LENGTH_SHORT).show()
//                }
//            }
//
//            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
//                //얘네는 나중에 수정 필요
//                if (inputNicknameEditText.length() < 12 && isChecked) {
//                    modifyCompletedButton.isEnabled = true
//                } else {
//                    Toast.makeText(requireActivity(), "12자 이하로 입력해주세요.", Toast.LENGTH_SHORT).show()
//                }
//            }
//        })

    }

}