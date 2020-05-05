package com.weather.lolapplication.ui

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.Fragment
import com.weather.lolapplication.R
import com.weather.lolapplication.base.BaseActivity
import com.weather.lolapplication.base.BaseNavigator
import com.weather.lolapplication.databinding.ActivityMainBinding
import com.weather.lolapplication.navigator.MainNavigator
import com.weather.lolapplication.viewmodel.MainViewModel
import kotlinx.android.synthetic.main.activity_main.*
import org.koin.androidx.viewmodel.ext.android.viewModel

class MainActivity : BaseActivity<ActivityMainBinding, MainViewModel>(), MainNavigator {

    override val layoutId: Int
        get() = R.layout.activity_main

    override val viewModel: MainViewModel by viewModel()

    override fun initAdapter() {
    }

    override fun initBind() {
        viewModel.navigator = this
        supportFragmentManager
            .beginTransaction()
            .replace(R.id.framelayout, FragmentHome())
            .commitAllowingStateLoss()
    }

    override fun initListener() {
        bottomNavigationView.setOnNavigationItemSelectedListener {
            val transaction = supportFragmentManager.beginTransaction()
            when(it.itemId){
                R.id.item0->{
                    transaction.replace(R.id.framelayout, FragmentHome()).commitAllowingStateLoss()
                    true
                }
                R.id.item1->{
                    transaction.replace(R.id.framelayout, FragmentUsers()).commitAllowingStateLoss()
                    true
                }
                R.id.item2->{
                    transaction.replace(R.id.framelayout, FragmentCommunity()).commitAllowingStateLoss()
                    true
                }
                else->false

            }
        }
    }

    override fun callHomeResultActivity(id:String, accountId:String) {
        val intent = Intent(applicationContext, HomeResultActivity::class.java)
        intent.putExtra("id",id)
        intent.putExtra("accountId", accountId)
        startActivity(intent)
    }


}
