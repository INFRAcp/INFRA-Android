package com.example.infraandroid

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.NavigationUI
import com.example.infraandroid.databinding.ActivityMainBinding

import android.view.View
import androidx.lifecycle.ViewModelProvider
import com.example.infraandroid.id.data.SharedIdViewModel
import com.google.android.material.shape.CornerFamily
import com.google.android.material.shape.MaterialShapeDrawable
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
            if(destination.id == R.id.login_fragment || destination.id == R.id.chat_fragment || destination.id == R.id.sign_up_first_fragment||
            destination.id==R.id.sign_up_second_fragment || destination.id==R.id.sign_up_third_fragment || destination.id==R.id.sign_up_fourth_fragment
                ||destination.id == R.id.categoryViewIdeaFragment || destination.id == R.id.myInfoTeamIdeaFragment || destination.id == R.id.myInfoProjectModifyFragment
                ||destination.id == R.id.createProjectFragment || destination.id == R.id.my_info_modify_fragment || destination.id==R.id.createProjectSelectCategory)
                mBinding.myBottomNav.visibility = View.GONE
            else
                mBinding.myBottomNav.visibility = View.VISIBLE
        }

        // 바텀 네비게이션 뷰와 네비게이션을 묶어준다
        NavigationUI.setupWithNavController(mBinding.myBottomNav, navController)

        val keyHash = Utility.getKeyHash(this)
        Log.d("해시키", keyHash)
    }


}