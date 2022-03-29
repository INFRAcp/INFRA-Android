package com.example.infraandroid.myinfo.myideamanage.view

import android.content.res.Resources
import android.graphics.Rect
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.DialogFragment
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.findNavController
import androidx.navigation.fragment.findNavController
import com.example.infraandroid.R
import com.example.infraandroid.databinding.MyInfoMyProjectDeleteWarningBinding
import com.example.infraandroid.myinfo.myideamanage.model.MyProjectViewModel
import com.example.infraandroid.myinfo.myideamanage.model.RequestDeleteProjectData
import com.example.infraandroid.myinfo.myideamanage.model.ResponseDeleteProjectData
import com.example.infraandroid.util.InfraApplication
import com.example.infraandroid.util.ServiceCreator
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class WarningDeleteDialog: DialogFragment() {
    private lateinit var viewModel : MyProjectViewModel
    private var mBinding : MyInfoMyProjectDeleteWarningBinding? = null

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
        val binding = MyInfoMyProjectDeleteWarningBinding.inflate(inflater, container, false)
        mBinding = binding

        return mBinding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        mBinding?.warningOkayButton?.setOnClickListener {
            val requestDeleteProjectData = RequestDeleteProjectData(
                user_id = InfraApplication.prefs.getUserId(),
                pj_num = viewModel.currentObservingProjectNum.value
            )
            val call : Call<ResponseDeleteProjectData> = ServiceCreator.myProjectService
                .deleteProject(InfraApplication.prefs.getString("jwt", "null"),
                InfraApplication.prefs.getString("refreshToken", "null").toInt(),
                requestDeleteProjectData)

            call.enqueue(object: Callback<ResponseDeleteProjectData> {
                override fun onResponse(
                    call: Call<ResponseDeleteProjectData>,
                    response: Response<ResponseDeleteProjectData>
                ) {
                    if(response.isSuccessful){
                        when(response.body()?.code){
                            1000->{
                                dismiss()
                                findNavController().navigate(R.id.action_myInfoTeamIdeaFragment_to_myInfoMyIdeaFragment)
                            }
                        }
                    }
                }

                override fun onFailure(call: Call<ResponseDeleteProjectData>, t: Throwable) {
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