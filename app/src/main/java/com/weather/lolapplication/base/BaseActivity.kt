package com.weather.lolapplication.base

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding

abstract class BaseActivity<T : ViewDataBinding, R : BaseViewModel> : AppCompatActivity(){

    lateinit var binding : T

    abstract val layoutId : Int
    abstract val viewModel : R

    abstract fun initAdapter()
    abstract fun initBind()
    abstract fun initListener()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, layoutId)
        initAdapter()
        initBind()
        initListener()
    }
}