package com.example.infraandroid

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.NavigationUI
import com.example.infraandroid.databinding.ActivityMainBinding

import android.view.View
import android.widget.Toast
import androidx.fragment.app.FragmentManager
import com.google.android.material.snackbar.Snackbar
import com.kakao.sdk.common.util.Utility

class MainActivity : AppCompatActivity() {

    private lateinit var mBinding : ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        mBinding = ActivityMainBinding.inflate(layoutInflater)

        setContentView(mBinding.root)

        // 네비게이션들을 담는 호스트
        val navHostFragment = supportFragmentManager.findFragmentById(R.id.my_nav_host) as NavHostFragment

        // 네비게이션 컨트롤러
        val navController = navHostFragment.navController

        // 바텀 네비게이션 뷰 출력하는 곳과 출력하지 않는 곳 구분
        navController.addOnDestinationChangedListener { _, destination, _ ->
            if(destination.id == R.id.home_fragment || destination.id == R.id.category_fragment || destination.id == R.id.chatting_room_list_fragment
                || destination.id == R.id.my_info_fragment) {
                mBinding.myBottomNav.visibility = View.VISIBLE
                mBinding.myNavHost.setPadding(0,0,0,changeDP(50)) }
            else {
                mBinding.myBottomNav.visibility = View.GONE
                mBinding.myNavHost.setPadding(0,0,0,0)
            }
        }

        // 바텀 네비게이션 뷰와 네비게이션을 묶어준다
        NavigationUI.setupWithNavController(mBinding.myBottomNav, navController)
        supportFragmentManager.popBackStack(R.id.splashFragment, FragmentManager.POP_BACK_STACK_INCLUSIVE)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
    }

    private fun changeDP(value: Int): Int {
        var displayMetrics = resources.displayMetrics
        return Math.round(value * displayMetrics.density)
    }


}