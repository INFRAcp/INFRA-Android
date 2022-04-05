package com.example.infraandroid.myinfo.myinfomodify.view

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.widget.AppCompatButton
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.navigation.findNavController
import com.example.infraandroid.R
import com.example.infraandroid.databinding.FragmentMyInfoModifyBinding
import com.example.infraandroid.id.viewmodel.SignUpViewModel
import com.example.infraandroid.myinfo.myinfomodify.model.RequestModifyMyInfoData
import com.example.infraandroid.myinfo.myinfomodify.model.RequestNicknameCheckData
import com.example.infraandroid.myinfo.myinfomodify.model.ResponseNicknameCheckData
import com.example.infraandroid.myinfo.myinfomodify.model.ResponseViewMyInfoData
import com.example.infraandroid.util.BaseFragment
import com.example.infraandroid.util.InfraApplication
import com.example.infraandroid.util.ServiceCreator
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

//내 정보 > 내 정보 .kt
class MyInfoModifyFragment : BaseFragment<FragmentMyInfoModifyBinding>(R.layout.fragment_my_info_modify) {
    /*private var mBinding : FragmentMyInfoModifyBinding? = null*/
    private var isChecked = false
    private var userNickname : String ?= null
    private var userPrPhoto : String ?= null

    override fun FragmentMyInfoModifyBinding.onCreateView() {
    }

    override fun FragmentMyInfoModifyBinding.onViewCreated() {

        val inputNicknameEditText = binding.inputNicknameEditText as EditText
        val myInfoModifyBackButton = binding.myInfoModifyBackButton as ImageView
        val myInfoModifyUserProfileLayout = binding.myInfoModifyUserProfileLayout as ConstraintLayout
        val overlapCheckButton = binding.overlapCheckButton as AppCompatButton
        val modifyCompletedButton = binding.modifyCompletedButton as TextView
        val doNotUseThisNicknameTextView = binding.doNotUseThisNicknameTextView as TextView
        val canUserIcon = binding.canUseIconImageView

        //내 정보 보기 서버 연결
        val viewcall: Call<ResponseViewMyInfoData> = ServiceCreator.myinfoService
            .viewMyInfo(InfraApplication.prefs.getString("jwt","null"),
                InfraApplication.prefs.getString("userId","null"))

        viewcall.enqueue(object : Callback<ResponseViewMyInfoData> {
            override fun onResponse(
                call: Call<ResponseViewMyInfoData>,
                response: Response<ResponseViewMyInfoData>
            ) {
                if(response.isSuccessful){
                    when(response.body()?.code){
                        1000 -> {
                            binding.myInfoModify = response.body()?.result
                            //userNickname = response.body()?.result?.user_nickname

                            //userPrPhoto = response.body()?.result?.user_prPhoto

                        }
                    }
                }

            }

            override fun onFailure(call: Call<ResponseViewMyInfoData>, t: Throwable) {
                Log.d("TAG","Failed : $t")
            }

        } )

        //닉네임 중복 서버 연결
        overlapCheckButton.setOnClickListener {
            val requestNicknameCheckData = RequestNicknameCheckData(
                userNickname = inputNicknameEditText.text.toString(),
            )

            val checkcall: Call<ResponseNicknameCheckData> = ServiceCreator.nicknameDoubleCheckService
                .doublecheck(requestNicknameCheckData)
            checkcall.enqueue(object : Callback<ResponseNicknameCheckData>{
                override fun onResponse(
                    call: Call<ResponseNicknameCheckData>,
                    response: Response<ResponseNicknameCheckData>
                ) {
                    if(response.isSuccessful){
                        val data = response.body()?.code
                        if(data==1000) {
                            isChecked = true
                            doNotUseThisNicknameTextView.isVisible = false
                            canUserIcon.isVisible = true
                            inputNicknameEditText.setBackgroundResource(R.drawable.can_use_this_id_background)
                        }
                        if(data==3104){
                            doNotUseThisNicknameTextView.isVisible = true
                            canUserIcon.isVisible = false
                            inputNicknameEditText.setBackgroundResource(R.drawable.double_check_id_background)
                        }
                    }
                }

                override fun onFailure(call: Call<ResponseNicknameCheckData>, t: Throwable) {

                }

            })

        }

        //내 정보 수정 서버 연결
        val modifycall: Call<RequestModifyMyInfoData> = ServiceCreator.myinfoService
            .ModifyMyInfo("jwt","userId")
        modifycall.enqueue(object : Callback<RequestModifyMyInfoData> {
            override fun onResponse(
                call: Call<RequestModifyMyInfoData>,
                response: Response<RequestModifyMyInfoData>
            ) {
                if(response.isSuccessful){
                    when(response.body()?.code){

                    }
                }
            }

            override fun onFailure(call: Call<RequestModifyMyInfoData>, t: Throwable) {
                Log.d("TAG","Failed : $t")
            }

        })

        //프로필 사진 추가 바텀싵
        val bottomSheetDialogFragment = MyInfoPhotoMoreMenuBottomSheetFragment()
        myInfoModifyUserProfileLayout.setOnClickListener {
            activity?.supportFragmentManager?.let { it1 -> bottomSheetDialogFragment.show(it1, bottomSheetDialogFragment.tag) }
        }

        // 뒤로가기 버튼 눌렀을때
        myInfoModifyBackButton.setOnClickListener {
            it.findNavController().navigate(R.id.action_my_info_modify_fragment_to_my_info_fragment)
        }

        // 수정완료 버튼 눌렀을때
        modifyCompletedButton.setOnClickListener {
            //값 저장해서 넘겨주는 내용의 코드
            it.findNavController().navigate(R.id.action_my_info_modify_fragment_to_my_info_fragment)
        }

        //닉네임 중복 버튼 활성화 설정
        inputNicknameEditText.addTextChangedListener(object: TextWatcher{
            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                overlapCheckButton.isEnabled = false
            }

            override fun afterTextChanged(p0: Editable?) {
                /*inputNicknameEditText.length() < 12 && inputNicknameEditText.length()>0*/
                if (inputNicknameEditText.length() < 12) {
                    overlapCheckButton.isEnabled = true
                } else {
                    Toast.makeText(requireActivity(), "12자 이하로 입력해주세요.", Toast.LENGTH_SHORT).show()
                }
            }

            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                //얘네는 나중에 수정 필요
                if (inputNicknameEditText.length() < 12) {
                    overlapCheckButton.isEnabled = true
                } else {
                    Toast.makeText(requireActivity(), "12자 이하로 입력해주세요.", Toast.LENGTH_SHORT).show()
                }
            }
        })

        //수정 완료 버튼 활성화 설정
        inputNicknameEditText.addTextChangedListener(object: TextWatcher{
            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                modifyCompletedButton.isEnabled = false
            }

            override fun afterTextChanged(p0: Editable?) {
                /*inputNicknameEditText.length() < 12 && inputNicknameEditText.length()>0*/
                if (inputNicknameEditText.length() < 12 && isChecked) {
                    modifyCompletedButton.isEnabled = true
                } else {
                    Toast.makeText(requireActivity(), "12자 이하로 입력해주세요.", Toast.LENGTH_SHORT).show()
                }
            }

            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                //얘네는 나중에 수정 필요
                if (inputNicknameEditText.length() < 12 && isChecked) {
                    modifyCompletedButton.isEnabled = true
                } else {
                    Toast.makeText(requireActivity(), "12자 이하로 입력해주세요.", Toast.LENGTH_SHORT).show()
                }
            }
        })

    }

}