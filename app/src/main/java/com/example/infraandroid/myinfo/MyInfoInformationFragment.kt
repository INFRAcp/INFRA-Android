package com.example.infraandroid.myinfo

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.navigation.findNavController
import com.example.infraandroid.R
import com.example.infraandroid.ServiceCreator
import com.example.infraandroid.databinding.MyInfoInformationBinding
import com.example.infraandroid.id.api.RequestUserData
import com.example.infraandroid.id.api.ResponseUserData
import com.example.infraandroid.id.data.SharedIdViewModel
import com.example.infraandroid.myinfo.api.ResponseProfileViewData
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MyInfoInformationFragment: Fragment() {
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

        val bottomSheetDialogFragment = MyInfoModifyMenuBottomSheetFragment()
        mBinding!!.myInfoInfoModifyTv.setOnClickListener{
            activity?.supportFragmentManager?.let { it1 -> bottomSheetDialogFragment.show(it1, bottomSheetDialogFragment.tag) }
        }


        //소개 페이지 내용 조회 서버 연결
//        val call: Call<ResponseProfileViewData> = ServiceCreator.profileViewService
//            .getProfile("1")
//
//        call.enqueue(
//            object: Callback<ResponseProfileViewData> {
//            override fun onResponse(
//                call: Call<ResponseProfileViewData>,
//                response: Response<ResponseProfileViewData>
//            ) {
//                if(response.isSuccessful){
//                    when (response.body()?.code) {
//                        1000 -> {
//                            Toast.makeText(requireActivity(),"요청에 성공하셨습니다.", Toast.LENGTH_SHORT).show()
//                            //Log.d(SharedIdViewModel.TAG, "요청에 성공하셨습니다.")
//
//                            //inforNameTextView.text =
//                        }
//                        3101 -> {
//                            Toast.makeText(requireActivity(),"해당하는 아이디가 없습니다.", Toast.LENGTH_SHORT).show()
//                        }
//                        4000 -> {
//                            Toast.makeText(requireActivity(),"데이터베이스 연결에 실패하였습니다.", Toast.LENGTH_SHORT).show()
//                        }
//
//                    }
//                }
//                else{
//                    Log.d(SharedIdViewModel.TAG, "onResponse: 연결 실패..")
//                }
//            }
//
//
//                override fun onFailure(call: Call<ResponseProfileViewData>, t: Throwable) {
//                    TODO("Not yet implemented")
//                }
//
//            })
    }

    override fun onDestroyView() {
        mBinding = null
        super.onDestroyView()
    }
}