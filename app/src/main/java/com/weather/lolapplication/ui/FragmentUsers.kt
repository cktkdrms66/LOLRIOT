package com.weather.lolapplication.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelStoreOwner
import androidx.recyclerview.widget.GridLayoutManager
import com.google.android.material.snackbar.Snackbar
import com.weather.lolapplication.R
import com.weather.lolapplication.adapter.UserAdapter
import com.weather.lolapplication.databinding.FragmentUserBinding
import com.weather.lolapplication.viewmodel.MainViewModel
import kotlinx.android.synthetic.main.fragment_user.*

class FragmentUsers : Fragment(){
    lateinit var viewModel: MainViewModel

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding : FragmentUserBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_user, container, false)
        return binding.root
    }
    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        recyclerview_user.run{
            adapter = UserAdapter()
            layoutManager = GridLayoutManager(activity, 3)
            setHasFixedSize(true)
        }
        viewModel = ViewModelProvider(activity as ViewModelStoreOwner).get(MainViewModel::class.java)
        viewModel.snackbar.observe(this as LifecycleOwner, Observer {
            Snackbar.make(view!!, "삭제되었습니다",Snackbar.LENGTH_SHORT).show()
        })
        viewModel.getAll().observe(activity as LifecycleOwner, Observer {
            (recyclerview_user.adapter as UserAdapter).updateList(it)
        })

    }
}