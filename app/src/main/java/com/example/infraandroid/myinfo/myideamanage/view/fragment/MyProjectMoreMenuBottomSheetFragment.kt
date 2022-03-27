package com.example.infraandroid.myinfo.myideamanage.view.fragment

import android.annotation.SuppressLint
import android.app.Dialog
import android.content.DialogInterface
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.findNavController
import androidx.navigation.fragment.NavHostFragment.findNavController
import androidx.navigation.fragment.findNavController
import com.example.infraandroid.R
import com.example.infraandroid.databinding.MyProjectMoreMenuBinding
import com.example.infraandroid.id.viewmodel.SignUpViewModel.Companion.TAG
import com.example.infraandroid.myinfo.myideamanage.view.WarningDeleteDialog
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