package com.example.infraandroid.myinfo.myideamanage.view

import android.app.Dialog
import android.content.Context
import android.content.res.Resources
import android.graphics.Color
import android.graphics.Rect
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.DialogFragment
import androidx.lifecycle.ViewModelProvider
import com.example.infraandroid.R
import com.example.infraandroid.databinding.MyInfoMyIdeaRejectWarningBinding
import com.example.infraandroid.myinfo.myideamanage.model.MyProjectViewModel
import com.example.infraandroid.myinfo.myideamanage.model.RequestAcceptData
import com.example.infraandroid.myinfo.myideamanage.model.ResponseAcceptData
import com.example.infraandroid.util.InfraApplication
import com.example.infraandroid.util.ServiceCreator
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

// 내 정보 > 내 아이디어 > 팀원 탭 > 내 팀원 관리 > 거절하기 클릭 시 나오는 dialog
class WarningRejectDialog(private val userId: String, private val userNickName: String) : DialogFragment() {
    private var mBinding : MyInfoMyIdeaRejectWarningBinding?=null
    private lateinit var viewModel : MyProjectViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        activity?.run{
            viewModel = ViewModelProvider(this, ViewModelProvider.NewInstanceFactory())
                .get(MyProjectViewModel::class.java)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding = MyInfoMyIdeaRejectWarningBinding.inflate(inflater, container, false)
        mBinding = binding

        return mBinding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        mBinding?.warningTeamMemNameTv?.text = userNickName

        mBinding?.warningOkayButton?.setOnClickListener {
            val requestAcceptData = RequestAcceptData(
                pj_inviteStatus = "거절",
                pj_num = viewModel.currentObservingProjectNum.value,
                user_id = userId
            )

            val call: Call<ResponseAcceptData> = ServiceCreator.myProjectService
                .acceptTeamMember(InfraApplication.prefs.getString("jwt", "null"), InfraApplication.prefs.getString("refreshToken", "null").toInt(), requestAcceptData)

            call.enqueue(object: Callback<ResponseAcceptData> {
                override fun onResponse(
                    call: Call<ResponseAcceptData>,
                    response: Response<ResponseAcceptData>
                ) {
                    if(response.isSuccessful){
                        when(response.body()?.code){
                            1000->{
                                Toast.makeText(requireActivity(), "거절했습니다", Toast.LENGTH_SHORT).show()
                                dismiss()
                            }
                        }
                    }
                }

                override fun onFailure(call: Call<ResponseAcceptData>, t: Throwable) {
                    TODO("Not yet implemented")
                }

            })
        }

        mBinding?.warningCancelButton?.setOnClickListener {
            dismiss()
        }
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        setWidthPercent(90)
    }

    fun DialogFragment.setWidthPercent(percentage: Int) {
        val percent = percentage.toFloat() / 100
        val dm = Resources.getSystem().displayMetrics
        val rect = dm.run { Rect(0, 0, widthPixels, heightPixels) }
        val percentWidth = rect.width() * percent
        dialog?.window?.setLayout(percentWidth.toInt(), ViewGroup.LayoutParams.WRAP_CONTENT)
        dialog?.window?.setBackgroundDrawableResource(R.drawable.warning_background)
    }
}