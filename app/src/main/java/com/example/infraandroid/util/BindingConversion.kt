package com.example.infraandroid.util

import android.annotation.SuppressLint
import android.content.res.ColorStateList
import android.graphics.Color
import android.widget.ImageView
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.view.isGone
import androidx.core.view.isVisible
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide
import com.example.infraandroid.R
import org.w3c.dom.Text

object BindingConversions {
    @JvmStatic
    @BindingAdapter("loadImg")
    fun loadImage(imageView: ImageView, url: String?){
        Glide.with(imageView.context).load(url)
            .error(R.drawable.ic_infra_logo)
            .into(imageView)
    }

    @JvmStatic
    @BindingAdapter("projectImg")
    fun projectImg(imageView: ImageView, url: String?){
        if(url=="https://infra-infra-bucket.s3.ap-northeast-2.amazonaws.com/pjphoto/infra_project.png"){
            imageView.isGone = true
        }
        else{
            imageView.isVisible = true
            Glide.with(imageView.context).load(url)
                .into(imageView)
        }
    }

    @JvmStatic
    @BindingAdapter("startDate", "endDate")
    fun makingTerm(textView: TextView, startDate: String?, endDate: String?){
        val startYear = startDate?.substring(0 until 4)?.toInt()
        val startMonth = startDate?.substring(5 until 7)?.toInt()
        val startDay = startDate?.substring(8 until 10)?.toInt()
        val endYear = endDate?.substring(0 until 4)?.toInt()
        val endMonth = endDate?.substring(5 until 7)?.toInt()
        val endDay = endDate?.substring(8 until 10)?.toInt()
        textView.text = "${startYear}년 ${startMonth}월 ${startDay}일-${endYear}년 ${endMonth}월 ${endDay}일"
    }

    @JvmStatic
    @BindingAdapter("loadCircleImg")
    fun loadCircleImg(imageView: ImageView, url: String?){
        Glide.with(imageView.context).load(url)
            .circleCrop()
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

    @JvmStatic
    @BindingAdapter("deadline")
    fun setDeadlineText(textView: TextView, comment: String?){
        textView.text = comment
        when(comment){
            "마감" -> textView.setTextColor(Color.parseColor("#8F8F8F"))
            "마감임박" -> textView.setTextColor(Color.parseColor("#9277F8"))
            "모집중" -> textView.setTextColor(Color.parseColor("#4B8EFF"))
        }
    }

    @JvmStatic
    @BindingAdapter("endRecruitDate", "status")
    fun setEndRecruitText(textView: TextView, endRecruitDate: String?, status: String?){
        val year = endRecruitDate?.substring(0,4)?.toInt()
        val month = endRecruitDate?.substring(5,7)?.toInt()
        val day = endRecruitDate?.substring(8,10)?.toInt()
        textView.text = "${year}년 ${month}월 ${day}일까지 모집"
        when(status){
            "마감" -> textView.setTextColor(Color.parseColor("#8F8F8F"))
            "마감임박" -> textView.setTextColor(Color.parseColor("#9277F8"))
            "모집중" -> textView.setTextColor(Color.parseColor("#4B8EFF"))
        }
    }

    @JvmStatic
    @BindingAdapter("intToString")
    fun intToString(textView: TextView, number: Int){
        textView.text = number.toString()
    }

    @JvmStatic
    @BindingAdapter("nowRecruit", "totalRecruit")
    fun setRecruitText(textView: TextView, nowRecruit: Int, totalRecruit: Int){
        textView.text = "${nowRecruit}/${totalRecruit}명"
    }

    @JvmStatic
    @BindingAdapter("nowRecruitString", "totalRecruitString")
    fun setRecruitTextString(textView: TextView, nowRecruit: String?, totalRecruit: String?){
        textView.text = "${nowRecruit}/${totalRecruit}명"
    }
}