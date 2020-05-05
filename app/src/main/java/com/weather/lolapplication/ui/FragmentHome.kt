package com.weather.lolapplication.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.FrameLayout
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelStoreOwner
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.android.material.snackbar.Snackbar
import com.weather.lolapplication.R
import com.weather.lolapplication.adapter.MatchAdapter
import com.weather.lolapplication.databinding.FragmentHomeBinding
import com.weather.lolapplication.viewmodel.MainViewModel
import kotlinx.android.synthetic.main.activity_home_result.*
import kotlinx.android.synthetic.main.fragment_home.*

class FragmentHome : Fragment(){

    lateinit var viewModel : MainViewModel

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding : FragmentHomeBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_home, container, false)
        return binding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        viewModel = ViewModelProvider(activity as ViewModelStoreOwner).get(MainViewModel::class.java)

        viewModel.snackbar.observe(this as LifecycleOwner, Observer {
            Snackbar.make((activity as AppCompatActivity).findViewById(R.id.framelayout), "소환사 이름을 입력해주세요", Snackbar.LENGTH_SHORT).show()
        })
        searchButton.setOnClickListener {
            viewModel.search(editText6.text.toString())
        }
    }




}