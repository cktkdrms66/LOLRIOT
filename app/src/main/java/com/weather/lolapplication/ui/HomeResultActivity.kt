package com.weather.lolapplication.ui

import android.view.View
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.android.material.snackbar.Snackbar
import com.weather.lolapplication.R
import com.weather.lolapplication.adapter.MatchAdapter
import com.weather.lolapplication.base.BaseActivity
import com.weather.lolapplication.databinding.ActivityHomeResultBinding
import com.weather.lolapplication.viewmodel.HomeResultViewModel
import kotlinx.android.synthetic.main.activity_home_result.*
import org.koin.androidx.viewmodel.ext.android.viewModel

class HomeResultActivity :BaseActivity<ActivityHomeResultBinding, HomeResultViewModel>(){
    override val layoutId: Int
        get() = R.layout.activity_home_result

    override val viewModel: HomeResultViewModel by viewModel()


    override fun initAdapter() {
        recyclerview_match.run {
            adapter = MatchAdapter()
            layoutManager = LinearLayoutManager(this@HomeResultActivity)
            setHasFixedSize(true)
        }
    }

    override fun initBind() {
        binding.viewModel = viewModel
        binding.setLifecycleOwner(this)
        val id = intent.getStringExtra("id")
        val accountId = intent.getStringExtra("accountId")
        viewModel.setSummoner(id)

        viewModel.setList(accountId)
        viewModel.matches.observe(this, Observer {
            println("list update")
            (recyclerview_match.adapter as MatchAdapter).updateList(it)
        })
        viewModel.snackbarLiveData.observe(this, Observer {
            Snackbar.make(findViewById(android.R.id.content), "유저 정보 저장 완료", Snackbar.LENGTH_SHORT).show()
        })

    }




    override fun initListener() {
    }

}