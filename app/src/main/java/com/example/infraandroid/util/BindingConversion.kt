package com.example.infraandroid.util

import android.annotation.SuppressLint
import android.widget.ImageView
import android.widget.TextView
import androidx.core.view.isGone
import androidx.core.view.isVisible
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide
import com.example.infraandroid.R

object BindingConversions {
    @JvmStatic
    @BindingAdapter("loadImg")
    fun loadImage(imageView: ImageView, url: String){
        Glide.with(imageView.context).load(url)
            .error(R.drawable.ic_infra_logo)
            .into(imageView)
    }

    @JvmStatic
    @BindingAdapter("getStringFromInt")
    fun getStringFromInt(textView: TextView, num: Int){
        textView.text = num.toString()
    }

    @JvmStatic
    @BindingAdapter("isEmptyHashTag")
    fun isEmptyHashTag(textView: TextView, hashtag: String?){
        if(hashtag==null){
            textView.isGone = true
        }
        else{
            textView.text = hashtag
        }
    }

    @JvmStatic
    @BindingAdapter("isScrap")
    fun isScrap(imageView: ImageView, scrap: Int){
        if(scrap==1){
            imageView.setImageResource(R.drawable.ic_bookmark_yellow)
        }
    }

    @SuppressLint("ResourceAsColor")
    @JvmStatic
    @BindingAdapter("deadline")
    fun setDeadlineText(textView: TextView, comment: String){
        textView.text = comment
        when(comment){
            "마감" -> textView.setTextColor(R.color.infra_gray_v)
            "마감임박!" -> textView.setTextColor(R.color.infra_purple_a)
            "모집중!" -> textView.setTextColor(R.color.infra_skyblue_a)
        }
    }
}