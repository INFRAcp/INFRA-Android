package com.infra.infraandroid.myinfo.myinfomodify.view

import android.Manifest
import android.annotation.SuppressLint
import android.app.Activity
import android.content.Intent
import android.content.pm.PackageManager
import android.database.Cursor
import android.graphics.Bitmap
import android.graphics.ImageDecoder
import android.net.Uri
import android.os.Build
import android.os.Bundle
import android.provider.MediaStore
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.lifecycle.ViewModelProvider
import com.infra.infraandroid.databinding.PhotoMoreMenuBinding
import com.infra.infraandroid.myinfo.myinfomodify.viewmodel.MyInfoViewModel
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import okhttp3.MultipartBody
import java.io.IOException

//작성자 : 이은진
//작성일 : 2022.02.04
//내 정보 > 내 정보 > 프로필에 있는 + 버튼 클릭시 나오는 bottomsheet
class MyInfoPhotoMoreMenuBottomSheetFragment : BottomSheetDialogFragment() {
    private lateinit var viewModel : MyInfoViewModel
    private  var mBinding : PhotoMoreMenuBinding? = null
    private val OPEN_GALLERY = 100
    val PERMISSIONS_REQUEST_CODE = 101
    var REQUIRED_PERMISSIONS = arrayOf<String>( Manifest.permission.READ_EXTERNAL_STORAGE)
    private var mediaPath : String ?= null
    private var fileToUpload : MultipartBody.Part? = null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding = PhotoMoreMenuBinding.inflate(inflater, container, false)
        mBinding = binding

        activity?.run{
            viewModel = ViewModelProvider(this, ViewModelProvider.NewInstanceFactory())
                .get(MyInfoViewModel::class.java)
        }

        return mBinding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // 앨범에서 선택 눌렀을 때
        mBinding?.findAlbumConstraintLayout?.setOnClickListener{
            requestPermission()
        }

        // 프로필 사진 삭제 눌렀을 때
        mBinding?.deleteProfileConstraintLayout?.setOnClickListener {
            viewModel.deleteImg()
            Toast.makeText(requireContext(), "사진이 삭제되었습니다", Toast.LENGTH_SHORT).show()
            dismiss()
        }
    }

    private fun openGallery() {
        val intent = Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI)
        startActivityForResult(intent, OPEN_GALLERY)
    }


    private fun requestPermission(){
        var permissionCheck = ContextCompat.checkSelfPermission(requireActivity(), Manifest.permission.READ_EXTERNAL_STORAGE)
        if(permissionCheck != PackageManager.PERMISSION_GRANTED){
            //설명이 필요한지
            if(ActivityCompat.shouldShowRequestPermissionRationale(requireActivity(), Manifest.permission.READ_EXTERNAL_STORAGE)){
                //설명 필요 (사용자가 요청을 거부한 적이 있음)
                ActivityCompat.requestPermissions(requireActivity(), REQUIRED_PERMISSIONS, PERMISSIONS_REQUEST_CODE )
            }else{
                //설명 필요하지 않음
                ActivityCompat.requestPermissions(requireActivity(), REQUIRED_PERMISSIONS, PERMISSIONS_REQUEST_CODE )
            }
        }else{
            //권한 허용하여 갤러리로 이동
            openGallery()
        }
    }

    override fun onRequestPermissionsResult(
        requestCode: Int, permissions: Array<out String>, grantResults: IntArray
    ) {
        when(requestCode){
            PERMISSIONS_REQUEST_CODE -> {
                if(grantResults.isNotEmpty() && grantResults[0] == PackageManager.PERMISSION_GRANTED){
                    openGallery()
                }else{
                    //권한 거부됨
                }
                return
            }
        }
    }

    @SuppressLint("Range")
    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        if(requestCode == OPEN_GALLERY && resultCode == Activity.RESULT_OK && data!=null){
            val selectedImage = data.data
            val photoUri = data.data
            try {
                photoUri?.let {
                    var bitmap : Bitmap?= null
                    bitmap = if(Build.VERSION.SDK_INT < 28) {
                        MediaStore.Images.Media.getBitmap(
                            requireActivity().contentResolver,
                            photoUri
                        )
                    } else {
                        val source = ImageDecoder.createSource(requireActivity().contentResolver, photoUri)
                        ImageDecoder.decodeBitmap(source)
                        //imageView.setImageBitmap(bitmap)
                    }
                    viewModel.updateImgBitmap(bitmap)
                }
            }
            catch (e: IOException) {
                e.printStackTrace()
            }

            val cursor: Cursor = requireActivity().contentResolver.query(
                Uri.parse(selectedImage.toString()),
                null,
                null,
                null,
                null
            )!!
            cursor.moveToFirst()
            mediaPath = cursor.getString(cursor.getColumnIndex(MediaStore.MediaColumns.DATA))
            viewModel.updateImg(mediaPath)
            dismiss()
        }
        else{
            Toast.makeText(requireActivity(), "이미지 업로드 실패", Toast.LENGTH_SHORT).show()
            dismiss()
        }
    }
}