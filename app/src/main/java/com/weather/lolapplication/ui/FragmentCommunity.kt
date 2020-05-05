package com.weather.lolapplication.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import com.weather.lolapplication.R
import com.weather.lolapplication.databinding.FragmentCommunityBinding
import com.weather.lolapplication.databinding.FragmentUserBinding

class FragmentCommunity :Fragment(){
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding : FragmentCommunityBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_community, container, false)
        return binding.root
    }
    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
    }
}