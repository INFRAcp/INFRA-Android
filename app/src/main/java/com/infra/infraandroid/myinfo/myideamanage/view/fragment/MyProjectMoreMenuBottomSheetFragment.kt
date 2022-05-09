package com.infra.infraandroid.myinfo.myideamanage.view.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.infra.infraandroid.R
import com.infra.infraandroid.databinding.MyProjectMoreMenuBinding
import com.infra.infraandroid.myinfo.myideamanage.view.WarningDeleteDialog
import com.google.android.material.bottomsheet.BottomSheetDialogFragment

class MyProjectMoreMenuBottomSheetFragment : BottomSheetDialogFragment() {
    private  var mBinding : MyProjectMoreMenuBinding? = null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding = MyProjectMoreMenuBinding.inflate(inflater, container, false)
        mBinding = binding

        return mBinding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val warningDeleteDialog = WarningDeleteDialog()

        mBinding?.editProjectConstraintLayout?.setOnClickListener {
            dismiss()
            findNavController().navigate(R.id.action_myInfoTeamIdeaFragment_to_myInfoProjectModifyFragment)
        }
        mBinding?.deleteProjectConstraintLayout?.setOnClickListener {
            dismiss()
            activity?.supportFragmentManager?.let { it1 -> warningDeleteDialog.show(it1, warningDeleteDialog.tag) }
        }
    }
}