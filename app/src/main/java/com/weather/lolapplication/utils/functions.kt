package com.weather.lolapplication.utils

import android.text.Editable
import android.text.TextWatcher
import android.widget.ImageView
import androidx.databinding.BindingAdapter
import com.weather.lolapplication.R

fun makeTextWatcher(func:()->Unit): TextWatcher {
    return object : TextWatcher {
        override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {

        }

        override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
            func()
        }

        override fun afterTextChanged(p0: Editable?) {

        }
    }
}

@BindingAdapter("bindImage")
fun bindImage(view: ImageView, image: Int ){
    view.setImageResource(image)
}

@BindingAdapter("tierImage")
fun bindImageTier(view:ImageView, _tier:String){
    val image : Int
    when(_tier){
        "IRON"->image = R.drawable.emblem_iron
        "BRONZE"->image = R.drawable.emblem_bronze
        "SILVER"->image = R.drawable.emblem_silver
        "GOLD"->image = R.drawable.emblem_gold
        "PLATINUM"->image = R.drawable.emblem_platinum
        "DIAMOND"->image = R.drawable.emblem_diamond
        "MASTER"->image = R.drawable.emblem_master
        "GRANDMASTER"->image = R.drawable.emblem_grandmaster
        "CHALLENGER"->image = R.drawable.emblem_challenger
        else->image = R.drawable.emblem_challenger
    }
    view.setImageResource(image)
}