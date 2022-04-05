package com.example.infraandroid.myinfo

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.findNavController
import androidx.navigation.fragment.findNavController
import com.example.infraandroid.util.InfraApplication
import com.example.infraandroid.R
import com.example.infraandroid.databinding.FragmentMyInfoBinding
//import com.google.android.gms.oss.licenses.OssLicensesActivity
//import com.google.android.gms.oss.licenses.OssLicensesMenuActivity

//작성자 : 이은진
//작성일 : 2022.02.03
// Update
// 내 정보에 이름 출력 (작성자 : 신승민)
// 2022-02-24 열람기록, 스크랩, 관심해시태그 설정, 프로젝트 신청 현황 네비게이션 연결 (작성자 : 신승민)
class MyInfoFragment : Fragment() {
    private  var mBinding : FragmentMyInfoBinding? = null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val binding = FragmentMyInfoBinding.inflate(inflater, container, false)

        mBinding = binding
        mBinding?.myInfoUserNameTv?.text = getString(R.string.my_info_name).format(InfraApplication.prefs.getString("userNickName","null"))
        return mBinding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        mBinding!!.myInfoMyInfoLinearlayout.setOnClickListener {
            it.findNavController().navigate(R.id.action_my_info_fragment_to_my_info_modify_fragment)
        }
       /* mBinding!!.myInfoIntroPageLayout.setOnClickListener {
            it.findNavController().navigate(R.id.action_my_info_fragment_to_myInfoInformationFragment)
        }*/
        mBinding!!.myInfoMyIdeaLinearlayout.setOnClickListener {
            it.findNavController().navigate(R.id.action_my_info_fragment_to_myInfoMyIdeaFragment)
        }
       /* mBinding!!.myInfoEvaluationLinearlayout.setOnClickListener {
            it.findNavController().navigate(R.id.action_my_info_fragment_to_myInfoTeamMemberEvaluationFragment)
        }
        mBinding!!.myInfoInterestLinearlayout.setOnClickListener {
            it.findNavController().navigate(R.id.action_my_info_fragment_to_myInfoInterestFragment)
        }
        mBinding!!.myInfoOpenLogLinearlayout.setOnClickListener {
            it.findNavController().navigate(R.id.action_my_info_fragment_to_historyFragment)
        }
        mBinding!!.myInfoBookmarkLayout.setOnClickListener {
            it.findNavController().navigate(R.id.action_my_info_fragment_to_scrapFragment)
        }
        mBinding!!.myInfoApplicationStatusLinearlayout.setOnClickListener {
            it.findNavController().navigate(R.id.action_my_info_fragment_to_applicationStatusFragment)
        }
        mBinding!!.myInfoHashtagAlarmLinearlayout.setOnClickListener {
            it.findNavController().navigate(R.id.action_my_info_fragment_to_hashTagAlarmFragment)
        }*/
        mBinding!!.myInfoUserGuideLinearlayout.setOnClickListener {
            it.findNavController().navigate(R.id.action_my_info_fragment_to_userGuideFragment)
        }

        mBinding!!.ossLinearLayout.setOnClickListener{
            //val intent = Intent(this.context, OssLicensesMenuActivity::class.java)
            //startActivity(intent)
        }
    }

    override fun onDestroyView() {
        mBinding = null
        super.onDestroyView()
    }
}