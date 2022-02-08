package com.example.infraandroid.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.findNavController
import com.example.infraandroid.R
import com.example.infraandroid.databinding.FragmentCreateProjectBinding

class CreateProjectFragment : Fragment() {
    private var mBinding : FragmentCreateProjectBinding? = null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding = FragmentCreateProjectBinding.inflate(inflater, container, false)
        mBinding = binding
        return mBinding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        mBinding?.cancelTextView?.setOnClickListener {
            it.findNavController().navigate(R.id.action_createProjectFragment_to_home_fragment)
        }

        mBinding?.completeTextView?.setOnClickListener {
            it.findNavController().navigate(R.id.action_createProjectFragment_to_home_fragment)
        }
    }

    override fun onDestroyView() {
        mBinding = null
        super.onDestroyView()
    }
}