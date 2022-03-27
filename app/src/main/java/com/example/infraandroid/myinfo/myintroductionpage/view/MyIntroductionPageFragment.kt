package com.example.infraandroid.myinfo.myintroductionpage.view

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.fragment.app.Fragment
import com.example.infraandroid.databinding.MyInfoInformationBinding
import com.example.infraandroid.id.model.RequestLoginData
import com.example.infraandroid.id.viewmodel.SignUpViewModel
import com.example.infraandroid.myinfo.myintroductionpage.model.ResponseProfileViewData
import com.example.infraandroid.util.ServiceCreator
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

//내 정보 > 소개페이지 .kt
class MyIntroductionPageFragment: Fragment() {
    private var mBinding: MyInfoInformationBinding? = null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding = MyInfoInformationBinding.inflate(inflater,container,false)
        mBinding = binding
        return mBinding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val inforNameTextView = mBinding?.myInfoInfoNameTextview as TextView
        val inforAbilityTextView = mBinding?.myInfoInfoDepartTextview as TextView
        val inforTextTextView = mBinding?.myInfoInfoTextTextview as TextView
        val infoGrade = mBinding?.myInfoInfoStarPointTextview as TextView

        val finishedPrImageView = mBinding?.categoryFinishedPrImageview as ImageView
        val finishedPrName = mBinding?.categoryFinishedPrNameTextview as TextView

        val categoryLinkGithubTv = mBinding?.categoryLinkGithubTv as TextView
        val categoryLinkInstagramTv = mBinding?.categoryLinkInstagramTv as TextView
        val categoryLinkBehanceTv = mBinding?.categoryLinkBehanceTv as TextView
        val categoryLinkGoogleDriveTv = mBinding?.categoryLinkGoogleDriveTv as TextView
        val categoryLinkYoutubeTv = mBinding?.categoryLinkYoutubeTv as TextView
        val categoryLinkNotionTv = mBinding?.categoryLinkNotionTv as TextView
        val categoryLinkLinkTv = mBinding?.categoryLinkLinkTv as TextView

        val categoryGithubLinearlayout = mBinding?.categoryGithubLinearlayout as LinearLayout
        val categoryInstagramLinearlayout = mBinding?.categoryInstagramLinearlayout as LinearLayout
        val categoryBehanceLinearlayout = mBinding?.categoryBehanceLinearlayout as LinearLayout
        val categoryGoogleDriveLinearlayout = mBinding?.categoryGoogleDriveLinearlayout as LinearLayout
        val categoryYoutubeLinearlayout = mBinding?.categoryYoutubeLinearlayout as LinearLayout
        val categoryNotionLinearlayout = mBinding?.categoryNotionLinearlayout as LinearLayout
        val categoryLinkLinearlayout = mBinding?.categoryLinkLinearlayout as LinearLayout

        val myInfoInfoHashTagOne = mBinding?.myInfoInfoHashTagOne as TextView
        val myInfoInfoHashTagTwo = mBinding?.myInfoInfoHashTagTwo as TextView
        val myInfoInfoHashTagThree = mBinding?.myInfoInfoHashTagThree as TextView
        val myInfoInfoHashTagFour = mBinding?.myInfoInfoHashTagFour as TextView

        val bottomSheetDialogFragment = MyIntroPageModifyBottomSheetFragment()
        mBinding!!.myInfoInfoModifyTv.setOnClickListener{
            activity?.supportFragmentManager?.let { it1 -> bottomSheetDialogFragment.show(it1, bottomSheetDialogFragment.tag) }
        }

//        val requestLoginData = RequestLoginData(
//            userId = inputId
//        )

        //소개 페이지 내용 조회 서버 연결
        val call: Call<ResponseProfileViewData> = ServiceCreator.profileViewService
            .getProfile("android123")

        call.enqueue(
            object: Callback<ResponseProfileViewData> {
                override fun onResponse(
                    call: Call<ResponseProfileViewData>,
                    response: Response<ResponseProfileViewData>
                ) {
                    if(response.isSuccessful){
                        when (response.body()?.code) {
                            1000 -> {
                                Toast.makeText(requireActivity(),"요청에 성공하셨습니다.", Toast.LENGTH_SHORT).show()

                            }
                            3101 -> {
                                Toast.makeText(requireActivity(),"해당하는 아이디가 없습니다.", Toast.LENGTH_SHORT).show()
                            }
                            4000 -> {
                                Toast.makeText(requireActivity(),"데이터베이스 연결에 실패하였습니다.", Toast.LENGTH_SHORT).show()
                            }

                        }
                    }
                    else{
                        Log.d(SignUpViewModel.TAG, "onResponse: 연결 실패..")
                    }
                }


                override fun onFailure(call: Call<ResponseProfileViewData>, t: Throwable) {
                    Log.d(SignUpViewModel.TAG, "onResponse: 연결 실패..")
                }

            })
    }

    override fun onDestroyView() {
        mBinding = null
        super.onDestroyView()
    }
}