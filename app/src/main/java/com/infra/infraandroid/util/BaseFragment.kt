package com.infra.infraandroid.util

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.LayoutRes
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.fragment.app.Fragment

// 데이터바인딩 사용할 때 상속해서 사용하면 바로 사용할 수 있음

open class BaseFragment<T: ViewDataBinding>(@LayoutRes val layoutRes: Int) : Fragment(){

    lateinit var binding: T

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(inflater, layoutRes, container, false)
        binding.onCreateView()
        binding.onViewCreated()
        return binding.root
    }

    open fun T.onCreateView() = Unit
    open fun T.onViewCreated() = Unit
}